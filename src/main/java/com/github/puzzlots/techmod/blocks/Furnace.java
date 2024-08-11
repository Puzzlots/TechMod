package com.github.puzzlots.techmod.blocks;

import com.github.puzzle.core.Identifier;
import com.github.puzzle.game.block.IModBlock;
import com.github.puzzle.game.generators.BlockEventGenerator;
import com.github.puzzle.game.generators.BlockGenerator;
import com.github.puzzlots.techmod.Constants;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.ui.UI;
import finalforeach.cosmicreach.world.Zone;

import java.util.List;
import java.util.Map;

public class Furnace implements IModBlock {

    public static final Identifier BLOCK_ID = new Identifier(Constants.MOD_ID, "Furnace");
    public static final String BLOCK_NAME = "furnace";


    @Override
    public BlockGenerator getBlockGenerator() {
        BlockGenerator generator = new BlockGenerator(BLOCK_ID, BLOCK_NAME);
        BlockGenerator.State state = generator.createBlockState("default", "tech-mod:model_electric_furnace_off", false, "events", true);
        state.stateGenerators = new String[]{"puzzle-loader:rotated"};
        state.catalogHidden = true;
        state.allowSwapping = false;
        BlockGenerator.State state2 = generator.createBlockState("on", "tech-mod:model_electric_furnace_on", false, "events", true);
        state2.stateGenerators = new String[]{"puzzle-loader:rotated"};
        state2.catalogHidden = true;
        state.allowSwapping = false;
        generator.addBlockEntity(Constants.MOD_ID+":example_entity", Map.of());
        return generator;
    }

    @Override
    public List<BlockEventGenerator> getBlockEventGenerators(Identifier blockId) {
        BlockEventGenerator generator = new BlockEventGenerator(blockId, "events");
        return List.of(generator);
    }
}