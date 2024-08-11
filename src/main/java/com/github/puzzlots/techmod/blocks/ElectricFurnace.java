package com.github.puzzlots.techmod.blocks;

import com.github.puzzle.core.Identifier;
import com.github.puzzle.game.block.IModBlock;
import com.github.puzzle.game.generators.BlockEventGenerator;
import com.github.puzzle.game.generators.BlockGenerator;
import com.github.puzzlots.techmod.Constants;

import java.util.List;

public class ElectricFurnace implements IModBlock {
    public static final Identifier BLOCK_ID = new Identifier(Constants.MOD_ID, "electric_furnace");

    public static final String BLOCK_NAME = "electric_furnace";

    @Override
    public BlockGenerator getBlockGenerator() {
        BlockGenerator generator = new BlockGenerator(BLOCK_ID, BLOCK_NAME);
        BlockGenerator.State state = generator.createBlockState("default", "tech-mod:model_electric_furnace_off", false, "base:block_events_default", false);
        state.stateGenerators = new String[]{"puzzle-loader:rotated"};
        state.catalogHidden = true;
        state.allowSwapping = false;
        BlockGenerator.State state2 = generator.createBlockState("on", "tech-mod:model_electric_furnace_on", false, "base:block_events_default", false);
        state2.stateGenerators = new String[]{"puzzle-loader:rotated"};
        state2.catalogHidden = true;
        state.allowSwapping = false;
        return generator;
    }

    @Override
    public List<BlockEventGenerator> getBlockEventGenerators(Identifier blockId) {
        BlockEventGenerator generator = new BlockEventGenerator(blockId, "events");
        return List.of(generator);
    }
}
