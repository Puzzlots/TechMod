package com.github.puzzlots.techmod.blocks;

import com.github.puzzle.core.Identifier;
import com.github.puzzle.core.resources.ResourceLocation;
import com.github.puzzle.game.block.IModBlock;
import com.github.puzzle.game.generators.BlockEventGenerator;
import com.github.puzzle.game.generators.BlockGenerator;
import com.github.puzzle.game.generators.BlockModelGenerator;
import com.github.puzzlots.techmod.Constants;

import java.util.List;
import java.util.Map;

public class ElectricFurnace implements IModBlock {
    public static final Identifier BLOCK_ID = new Identifier(Constants.MOD_ID, "electric_furnace");

    public static final ResourceLocation ALL_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/blocks/electric_furnace_off.png");

    public static final String BLOCK_NAME = "Electric Furnace";

    @Override
    public BlockGenerator getBlockGenerator() {
        BlockGenerator generator = new BlockGenerator(BLOCK_ID, BLOCK_NAME);
        return generator;
    }

    @Override
    public List<BlockModelGenerator> getBlockModelGenerators(Identifier blockId) {
        BlockModelGenerator generator = new BlockModelGenerator(blockId, "model");
        generator.createTexture("all", ALL_TEXTURE);
        generator.createCuboid(0, 0, 0, 16, 16, 16, "all");
        return List.of(generator);
    }

    @Override
    public List<BlockEventGenerator> getBlockEventGenerators(Identifier blockId) {
        BlockEventGenerator generator = new BlockEventGenerator(blockId, "events");
        return List.of(generator);
    }
}
