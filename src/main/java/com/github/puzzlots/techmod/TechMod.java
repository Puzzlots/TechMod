package com.github.puzzlots.techmod;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.github.puzzle.core.PuzzleRegistries;
import com.github.puzzle.core.localization.ILanguageFile;
import com.github.puzzle.core.localization.LanguageManager;
import com.github.puzzle.core.localization.files.LanguageFileVersion1;
import com.github.puzzle.core.resources.ResourceLocation;
import com.github.puzzle.game.events.OnLoadAssetsEvent;
import com.github.puzzle.game.events.OnPreLoadAssetsEvent;
import com.github.puzzle.game.events.OnRegisterBlockEvent;
import com.github.puzzle.loader.entrypoint.interfaces.ModInitializer;
import com.github.puzzlots.techmod.UI.TechModUI;
import com.github.puzzlots.techmod.block_entities.ElectricFurnaceBlockEntity;
import com.github.puzzlots.techmod.blocks.ElectricFurnace;
import com.github.puzzlots.techmod.blocks.Furnace;
import finalforeach.cosmicreach.GameAssetLoader;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class TechMod implements ModInitializer {

    @Override
    public void onInit() {
        PuzzleRegistries.EVENT_BUS.register(this);

        Constants.LOGGER.info("Hello From INIT");

        //TechModUI.Furnace9Patch = new NinePatch(new Texture(GameAssetLoader.loadAsset("tech-mod/textures/ui/furnace.png")), 4, 4, 4, 4);
        //TechModUI.FurnaceSelected9Patch = new NinePatch(new Texture(GameAssetLoader.loadAsset("textures/ui/container-selected.png")), 4, 4, 4, 4);
        ElectricFurnaceBlockEntity.register();
    }

    @Subscribe
    public void onEvent(OnPreLoadAssetsEvent event) {
        ILanguageFile lang = null;
        try {
            lang = LanguageFileVersion1.loadLanguageFromString(new ResourceLocation(Constants.MOD_ID, "languages/en-US.json").locate().readString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LanguageManager.registerLanguageFile(lang);
    }

    @Subscribe
    public void onEvent(OnRegisterBlockEvent event) {
        event.registerBlock(ElectricFurnace::new);
        event.registerBlock(Furnace::new);
    }

}
