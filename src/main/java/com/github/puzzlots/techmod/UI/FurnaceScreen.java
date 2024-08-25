package com.github.puzzlots.techmod.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.github.puzzlots.techmod.Constants;
import com.github.puzzlots.techmod.block_entities.ElectricFurnaceBlockEntity;
import finalforeach.cosmicreach.GameAssetLoader;
import finalforeach.cosmicreach.items.screens.BaseItemScreen;
import finalforeach.cosmicreach.items.ISlotContainer;
import finalforeach.cosmicreach.ui.UI;
import finalforeach.cosmicreach.ui.widgets.ItemSlotWidget;

public class FurnaceScreen extends BaseItemScreen {

    public static NinePatch FurnaceBackground = new NinePatch(new Texture(GameAssetLoader.loadAsset("tech-mod:textures/ui/furnace.png")), 4, 4, 4, 4);
    public static NinePatch FurnaceSelected9Patch = new NinePatch(new Texture(GameAssetLoader.loadAsset("tech-mod:textures/ui/container-selected.png")), 4, 4, 4, 4);
    public ISlotContainer container;

    public FurnaceScreen(ISlotContainer container) {
        this(container, Math.floorDiv(container.getNumSlots(), 10), Math.min(container.getNumSlots(), 10));
    }

    public FurnaceScreen(ISlotContainer container, int rows, int columns) {
        this.container = container;
        int numSlots = container.getNumSlots();

        Stack stack = new Stack();
        Actor background = new Image(FurnaceBackground);

        Table FurnaceFuel = new Table();
        Table FurnaceOutTable = new Table();
        Table FurnaceInTable = new Table();

        this.slotWidgets = new ItemSlotWidget[numSlots];
        this.slotWidgets[0] = new ItemSlotWidget(container, container.getSlot(0));
        this.slotWidgets[1] = new ItemSlotWidget(container, container.getSlot(1));
        this.slotWidgets[2] = new ItemSlotWidget(container, container.getSlot(2));

        FurnaceInTable.add(this.slotWidgets[0]).left().top().pad(5);
        FurnaceInTable.top().left().pad(5);

        FurnaceFuel.add(this.slotWidgets[1]).left().top().pad(5);
        FurnaceFuel.bottom().left().pad(5);

        FurnaceOutTable.add(this.slotWidgets[2]).left().top().pad(5);
        FurnaceOutTable.right().pad(5);


        stack.setWidth((float) 320);
        stack.setHeight((float) 300);
        ((Actor)background).setWidth(100 + 8.0F);
        ((Actor)background).setHeight(100 + 8.0F);

        stack.add(background);
        stack.add(FurnaceFuel);
        stack.add(FurnaceInTable);
        stack.add(FurnaceOutTable);
        stack.setBounds(((Actor)background).getX(), ((Actor)background).getY(), ((Actor)background).getWidth(), ((Actor)background).getHeight());

        this.slotActor = stack;
        this.init();
    }
}




