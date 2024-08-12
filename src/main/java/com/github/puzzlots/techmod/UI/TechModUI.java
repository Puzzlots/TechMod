package com.github.puzzlots.techmod.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.github.puzzlots.techmod.Constants;
import finalforeach.cosmicreach.GameAssetLoader;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.Hotbar;
import finalforeach.cosmicreach.items.ItemCatalog;
import finalforeach.cosmicreach.items.ItemSlotCursor;
import finalforeach.cosmicreach.items.containers.SlotContainer;
import finalforeach.cosmicreach.ui.Crosshair;
import finalforeach.cosmicreach.ui.UI;

import static finalforeach.cosmicreach.ui.UI.itemCatalog;
import static finalforeach.cosmicreach.ui.UI.openContainers;

public class TechModUI {
    public static NinePatch Furnace9Patch;
    public static NinePatch FurnaceSelected9Patch;

    public TechModUI() {
        Furnace9Patch = new NinePatch(new Texture(GameAssetLoader.loadAsset("textures/ui/container.png")), 4, 4, 4, 4);
        FurnaceSelected9Patch = new NinePatch(new Texture(GameAssetLoader.loadAsset("textures/ui/container-selected.png")), 4, 4, 4, 4);

    }

    public static void OpenFurnace(final SlotContainer container) {
        Constants.LOGGER.info("Furnace Open");
        itemCatalog.show();
        openContainers.add(container);
        final FurnaceScreen screen = new FurnaceScreen(container);
        InGame.IN_GAME.openedMainItemScreen = screen;
        InGame.IN_GAME.addBaseItemScreen(screen);
        Action action = new Action() {
            public boolean act(float delta) {
                screen.getActor().setX(InGame.IN_GAME.newUiViewport.getWorldWidth() / 2.0F, 1);
                screen.getActor().setY(InGame.IN_GAME.newUiViewport.getWorldHeight() / 2.0F, 1);
                if (!UI.isInventoryOpen()) {
                    UI.openContainers.removeValue(container, true);
                    InGame.IN_GAME.removeBaseItemScreen(screen);
                    return true;
                }
                return false;
            }
        };
        action.act(0.0F);
        screen.getActor().addAction(action);

    }
}
