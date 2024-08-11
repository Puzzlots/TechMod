package com.github.puzzlots.techmod.block_entities;

import com.badlogic.gdx.graphics.Camera;
import com.github.puzzle.core.Identifier;
import com.github.puzzle.game.blockentities.ExtendedBlockEntity;
import com.github.puzzle.game.blockentities.IRenderable;
import com.github.puzzle.game.blockentities.ITickable;
import com.github.puzzlots.techmod.FurnaceScreen;
import finalforeach.cosmicreach.blockentities.BlockEntityCreator;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.items.BaseItemScreen;
import finalforeach.cosmicreach.items.ItemCatalog;
import finalforeach.cosmicreach.world.Zone;
import com.github.puzzlots.techmod.Constants;

public class ExampleBlockEntity extends ExtendedBlockEntity implements IRenderable, ITickable {
    private static final String BLOCK_ENTITY_ID = "base:container";
    static Identifier id = new Identifier(Constants.MOD_ID, "example_entity");

    public static void register() {
        BlockEntityCreator.registerBlockEntityCreator(id.toString(), (block, zone, x, y, z) -> new ExampleBlockEntity(zone, x, y, z));
    }

    public ExampleBlockEntity(Zone zone, int x, int y, int z) {
        super(zone, x, y, z);
    }

    @Override
    public String getBlockEntityId() {
        return id.toString();
    }

    @Override
    public void onTick(float tps) {

    }

    @Override
    public void onInteract(Zone zone, BlockPosition blockPos, boolean interactJustPressed, boolean interactHeld, double timeSinceLastInteract) {
        //FurnaceScreen screen = new FurnaceScreen();
        Constants.LOGGER.info("onInteract");
    }

    @Override
    public void onRender(Camera camera) {
        // add custom rendering logic here

    }
}