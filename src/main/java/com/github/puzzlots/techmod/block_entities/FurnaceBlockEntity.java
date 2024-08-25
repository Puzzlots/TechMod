package com.github.puzzlots.techmod.block_entities;

import com.github.puzzle.core.Identifier;
import com.github.puzzlots.techmod.Constants;
import com.github.puzzlots.techmod.ExtendedFurnaceEntity.ExtendedFurnaceEntity;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blockentities.BlockEntityCreator;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.io.CRBinDeserializer;
import finalforeach.cosmicreach.io.CRBinSerializer;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.items.containers.FurnaceSlotContainer;
import finalforeach.cosmicreach.items.containers.SlotContainer;
import finalforeach.cosmicreach.items.recipes.FurnaceRecipe;
import finalforeach.cosmicreach.world.BlockSetter;
import finalforeach.cosmicreach.world.Zone;

public class FurnaceBlockEntity extends ExtendedFurnaceEntity {
    public static Identifier id = new Identifier(Constants.MOD_ID, "furnace_entity");
    public static final String BLOCK_ENTITY_ID = "tech-mod:furnace_entity";
    public static final String FUEL_PROPERTY_NAME = "fuelTicks";
    public static final int MAX_PROGRESS_TICKS = 64;
    public FurnaceSlotContainer slotContainer;
    int progressTicks;
    int maxFuelTicks;
    int fuelTicks;
    private static BlockState lit = Block.getInstance("block_furnace").getBlockState("lit=on");
    private static BlockState unlit = Block.getInstance("block_furnace").getBlockState("lit=off");

    public static void register() {
        BlockEntityCreator.registerBlockEntityCreator(id.toString(), (block, zone, x, y, z) -> new FurnaceBlockEntity(zone, x, y, z));
    }

    public FurnaceBlockEntity(Zone zone, int globalX, int globalY, int globalZ) {
        super(zone, globalX, globalY, globalZ);
        this.slotContainer = new FurnaceSlotContainer(this);
    }

    public FurnaceBlockEntity(BlockPosition blockPos) {
        this(blockPos.getZone(), blockPos.getGlobalX(), blockPos.getGlobalY(), blockPos.getGlobalZ());
    }

    public void onRemove() {
        super.onRemove();
        this.slotContainer.dropAllItems(this.zone, (float)this.x + 0.5F, (float)this.y + 0.5F, (float)this.z + 0.5F);
    }

    public void onInteract(Player player, Zone zone) {
        super.onInteract(player, zone);
        GameSingletons.openBlockEntityScreen(player, zone, this);
    }

    public void read(CRBinDeserializer deserial) {
        super.read(deserial);
        this.slotContainer = (FurnaceSlotContainer)deserial.readObj("slotContainer", FurnaceSlotContainer.class);
        this.slotContainer.setFurnace(this);
        this.progressTicks = deserial.readInt("progressTicks", 0);
        this.fuelTicks = deserial.readInt("fuelTicks", 0);
        this.maxFuelTicks = deserial.readInt("maxFuelTicks", 0);
    }

    public void write(CRBinSerializer serial) {
        super.write(serial);
        serial.writeObj("slotContainer", this.slotContainer);
        serial.writeInt("progressTicks", this.progressTicks);
        serial.writeInt("fuelTicks", this.fuelTicks);
        serial.writeInt("maxFuelTicks", this.maxFuelTicks);
    }

    public static void registerBlockEntityCreator() {
        BlockEntityCreator.registerBlockEntityCreator("base:furnace", (blockState, zone, x, y, z) -> {
            return new finalforeach.cosmicreach.blockentities.BlockEntityFurnace(zone, x, y, z);
        });
    }

    public String getBlockEntityId() {
        return "base:furnace";
    }

    public void updateRecipe(FurnaceRecipe currentRecipe, boolean resetProgressTips) {
        this.setTicking(true);
        if (resetProgressTips) {
            this.progressTicks = 0;
        }

    }

    public void updateLitState(boolean isLit) {
        if (isLit) {
            if (this.getBlockState() == unlit) {
                BlockSetter.get().replaceBlock(this.zone, lit, this.x, this.y, this.z);
            }
        } else if (this.getBlockState() == lit) {
            BlockSetter.get().replaceBlock(this.zone, unlit, this.x, this.y, this.z);
        }

    }

    public void onTick() {
        super.onTick();
        FurnaceRecipe recipe = this.slotContainer.checkRecipe(true);
        if (this.fuelTicks > 0) {
            --this.fuelTicks;
        }

        if (recipe == null) {
            if (this.fuelTicks <= 0) {
                this.setTicking(false);
                this.updateLitState(false);
            }

            this.progressTicks = 0;
        } else {
            if (this.fuelTicks <= 0) {
                ItemSlot fuelSlot = this.slotContainer.getFuelSlot();
                ItemStack fuelStack = fuelSlot.itemStack;
                if (fuelStack == null) {
                    this.setTicking(false);
                    this.updateLitState(false);
                    this.progressTicks = 0;
                    return;
                }

                Item fuelItem = fuelStack.getItem();
                if (!fuelItem.hasIntProperty("fuelTicks")) {
                    this.setTicking(false);
                    this.updateLitState(false);
                    this.progressTicks = 0;
                    return;
                }

                this.fuelTicks = this.maxFuelTicks = fuelItem.getIntProperty("fuelTicks", this.fuelTicks);
                fuelSlot.addAmount(-1);
            }

            ++this.progressTicks;
            if (this.progressTicks == 1) {
                this.updateLitState(true);
            }

            if (this.progressTicks < 64 && this.fuelTicks <= 0) {
                this.progressTicks = 0;
            } else if (this.progressTicks >= 64) {
                this.slotContainer.onRecipeComplete();
                this.progressTicks = 0;
            }
        }

    }

    public float getProgressRatio() {
        return (float)this.progressTicks / 64.0F;
    }

    public void onLoad() {
        this.slotContainer.checkRecipe(false);
    }

    public float getRemainingFuelRatio() {
        if (this.isTicking() && this.maxFuelTicks != 0) {
            return this.maxFuelTicks <= 4 && this.fuelTicks > 0 ? 1.0F : (float)this.fuelTicks / (float)this.maxFuelTicks;
        } else {
            return 0.0F;
        }
    }
}
