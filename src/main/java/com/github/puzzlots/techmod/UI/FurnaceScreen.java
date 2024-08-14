package com.github.puzzlots.techmod.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.github.puzzlots.techmod.Constants;
import finalforeach.cosmicreach.GameAssetLoader;
import finalforeach.cosmicreach.items.BaseItemScreen;
import finalforeach.cosmicreach.items.ISlotContainer;
import finalforeach.cosmicreach.items.ItemSlotWidget;
import finalforeach.cosmicreach.items.containers.CraftingSlotContainer;
import finalforeach.cosmicreach.ui.UI;

public class FurnaceScreen extends BaseItemScreen {

    public static NinePatch Furnace9Patch = new NinePatch(new Texture(GameAssetLoader.loadAsset("tech-mod:textures/ui/furnace.png")), 4, 4, 4, 4);
    public static NinePatch FurnaceSelected9Patch = new NinePatch(new Texture(GameAssetLoader.loadAsset("tech-mod:textures/ui/container-selected.png")), 4, 4, 4, 4);
    public ISlotContainer container;

    public FurnaceScreen(ISlotContainer container) {
        this(container, Math.floorDiv(container.getNumSlots(), 10), Math.min(container.getNumSlots(), 10));
    }

    public FurnaceScreen(ISlotContainer container, int rows, int columns) {
        this.container = container;
        int numSlots = container.getNumSlots();
        Stack stack = new Stack();
        Actor background = new Image(Furnace9Patch);//TechModUI.Furnace9Patch
        Table slotActorTable = new Table();
        this.slotWidgets = new ItemSlotWidget[numSlots];

        for(int n = 0; n < numSlots; ++n) {
            if (n > 0 && n % columns == 0) {
                slotActorTable.row();
            }

            ItemSlotWidget slotWidget = new ItemSlotWidget(container, container.getSlot(n));
            this.slotWidgets[n] = slotWidget;
            slotActorTable.add(slotWidget);
        }

        slotActorTable.row();
        slotActorTable.setWidth((float)(slotActorTable.getColumns() * 32));
        slotActorTable.setHeight((float)(slotActorTable.getRows() * 32));
        slotActorTable.setX(4.0F, 10);
        slotActorTable.setY(4.0F, 10);
        background.setWidth(slotActorTable.getWidth() + 8.0F);
        background.setHeight(slotActorTable.getHeight() + 8.0F);
        stack.add(background);
        stack.add(slotActorTable);
        stack.setBounds(background.getX(), background.getY(), background.getWidth(), background.getHeight());
        this.slotActor = stack;
        this.init();
    }
}




