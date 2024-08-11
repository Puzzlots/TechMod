package org.example.exmod;

import com.github.puzzle.core.PuzzleRegistries;
import com.github.puzzle.core.localization.ILanguageFile;
import com.github.puzzle.core.localization.LanguageManager;
import com.github.puzzle.core.localization.files.LanguageFileVersion1;
import com.github.puzzle.core.resources.ResourceLocation;
import com.github.puzzle.game.block.DataModBlock;
import com.github.puzzle.game.events.OnPreLoadAssetsEvent;
import com.github.puzzle.game.events.OnRegisterBlockEvent;
import com.github.puzzle.loader.entrypoint.interfaces.ModInitializer;
import org.example.exmod.block_entities.ExampleBlockEntity;
import org.example.exmod.blocks.Bedrock;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class ExampleMod implements ModInitializer {

    @Override
    public void onInit() {
        PuzzleRegistries.EVENT_BUS.register(this);

        Constants.LOGGER.info("Hello From INIT");
        ExampleBlockEntity.register();
    }

    @Subscribe
    public void onEvent(OnRegisterBlockEvent event) {
        event.registerBlock(() -> new DataModBlock("diamond_block", new ResourceLocation(Constants.MOD_ID, "blocks/diamond_block.json")));
        event.registerBlock(Bedrock::new);
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

}
