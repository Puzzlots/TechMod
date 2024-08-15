package com.github.puzzlots.techmod.block_entities;

import com.github.puzzle.core.Identifier;
import com.github.puzzle.game.blockentities.ExtendedBlockEntity;
import com.github.puzzle.game.blockentities.ITickable;
import com.github.puzzlots.techmod.UI.TechModUI;
import finalforeach.cosmicreach.blockentities.BlockEntityCreator;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.io.CRBinDeserializer;
import finalforeach.cosmicreach.io.CRBinSerializer;
import finalforeach.cosmicreach.items.containers.SlotContainer;
import finalforeach.cosmicreach.world.Zone;
import com.github.puzzlots.techmod.Constants;

public class ElectricFurnaceBlockEntity extends ExtendedBlockEntity implements ITickable {

    public SlotContainer slotContainer;

    public static Identifier id = new Identifier(Constants.MOD_ID, "electric_furnace_entity");

    public static void register() {
        BlockEntityCreator.registerBlockEntityCreator(id.toString(), (blockState, zone, x, y, z) -> {
            Block block = blockState.getBlock();
            int numSlots = getBlockEntityParamInt(block, "numSlots", 3);
            return new ElectricFurnaceBlockEntity(zone, x, y, z,new SlotContainer(numSlots));
        });
    }

    public ElectricFurnaceBlockEntity(Zone zone, int x, int y, int z,SlotContainer s) {
        super(zone, x, y, z);
        this.slotContainer = s;
    }

    @Override
    public String getBlockEntityId() {
        return id.toString();
    }

    @Override
    public void onInteract(Zone zone, BlockPosition blockPos, boolean interactJustPressed, boolean interactHeld, double timeSinceLastInteract) {
        TechModUI.OpenFurnace(this.slotContainer);

    }

    public void onRemove() {
        super.onRemove();
        this.slotContainer.dropAllItems(this.zone, (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
    }

    public void read(CRBinDeserializer crbs) {
        super.read(crbs);
        this.slotContainer = crbs.readObj("slotContainer", SlotContainer.class);
    }

    public void write(CRBinSerializer crbs) {
        super.write(crbs);
        crbs.writeObj("slotContainer", this.slotContainer);
    }
    @Override
    public void onTick(float tps) {

    }
}