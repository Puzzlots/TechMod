package com.github.puzzlots.techmod;

import com.github.puzzle.loader.entrypoint.interfaces.PostModInitializer;
import com.github.puzzlots.techmod.block_entities.ElectricFurnaceBlockEntity;
import com.github.puzzlots.techmod.block_entities.FurnaceBlockEntity;

public class TechModPostInit implements PostModInitializer {
    @Override
    public void onPostInit() {
        Constants.LOGGER.info("Hello From onPostINIT");
        ElectricFurnaceBlockEntity.register();
        FurnaceBlockEntity.register();
    }
}
