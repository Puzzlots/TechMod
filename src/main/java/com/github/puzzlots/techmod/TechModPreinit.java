package com.github.puzzlots.techmod;

import com.github.puzzle.loader.entrypoint.interfaces.PreModInitializer;

public class TechModPreinit implements PreModInitializer {

    @Override
    public void onPreInit() {
        Constants.LOGGER.info("Hello From PRE-INIT");
    }
}