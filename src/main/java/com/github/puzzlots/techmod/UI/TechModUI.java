package com.github.puzzlots.techmod.UI;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.github.puzzlots.techmod.Constants;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.containers.SlotContainer;
import finalforeach.cosmicreach.ui.UI;

import static finalforeach.cosmicreach.ui.UI.itemCatalog;
import static finalforeach.cosmicreach.ui.UI.openContainers;

public class TechModUI {

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
