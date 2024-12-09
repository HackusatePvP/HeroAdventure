package me.piitex.game.gui;

import javafx.scene.paint.Color;
import me.piitex.game.HeroAdventure;

import me.piitex.renjava.api.loaders.FontLoader;
import me.piitex.renjava.api.saves.Save;
import me.piitex.renjava.gui.Container;
import me.piitex.renjava.gui.DisplayOrder;
import me.piitex.renjava.gui.StageType;
import me.piitex.renjava.gui.containers.EmptyContainer;
import me.piitex.renjava.gui.layouts.VerticalLayout;
import me.piitex.renjava.gui.menus.DefaultMainMenu;
import me.piitex.renjava.gui.menus.MainMenu;
import me.piitex.renjava.gui.overlays.ButtonOverlay;
import me.piitex.renjava.gui.overlays.ImageOverlay;
import me.piitex.renjava.gui.overlays.TextOverlay;

public class HeroMainMenu implements MainMenu {
    private static final HeroAdventure instance = HeroAdventure.getInstance();

    @Override
    public Container mainMenu(boolean rightClick) {
        // Empty container behaves like the old menu system.
        // It is essentially an empty box which you add overlays to.
        Container menu = new EmptyContainer(1920, 1080);
        menu.addOverlay(new ImageOverlay("gui/main_menu.png"));

        // Basic text overlay
        TextOverlay gameText = new TextOverlay(instance.getName() + ' ' + instance.getVersion(), new FontLoader(HeroAdventure.CONFIGURATION.getUiFont(), 36), 1500, 975);

        // Add the overlay to the container
        menu.addOverlay(gameText);

        return menu;
    }

    @Override
    public Container sideMenu(boolean rightClick) {
        Container menu = new EmptyContainer(1920, 1080, DisplayOrder.HIGH);

        ImageOverlay imageOverlay = new ImageOverlay("gui/overlay/main_menu.png");
        imageOverlay.setOrder(DisplayOrder.LOW);
        menu.addOverlay(imageOverlay);

        FontLoader uiFont = instance.getConfiguration().getUiFont();

        Color hoverColor = HeroAdventure.CONFIGURATION.getHoverColor();

        ButtonOverlay startButton = new ButtonOverlay("menu-start-button", "Start", Color.BLACK, uiFont, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor);
        ButtonOverlay loadButton = new ButtonOverlay("menu-load-button", "Load", Color.BLACK, uiFont, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor);
        ButtonOverlay saveButton = new ButtonOverlay("menu-save-button", "Save", Color.BLACK, uiFont, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor);
        ButtonOverlay optionsButton = new ButtonOverlay("menu-preference-button", "Preferences", Color.BLACK, uiFont, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor);
        ButtonOverlay aboutButton = new ButtonOverlay("menu-about-button", "About", Color.BLACK, uiFont, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor);
        ButtonOverlay donateButton = new ButtonOverlay("menu-donate-button", "Donate", Color.BLACK, uiFont, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor);

        // Create vbox for the buttons. You can also do an HBox
        VerticalLayout layout = new VerticalLayout(400, 500);
        layout.setOrder(DisplayOrder.HIGH);
        layout.setX(50);
        layout.setY(250);
        layout.setSpacing(20);
        layout.addOverlays(startButton, loadButton);
        if (rightClick) {
            layout.addOverlays(saveButton);
        }
        layout.addOverlays(optionsButton, aboutButton, donateButton);

        // You don't have to add the button overlays just add the layout which already contains the overlays.
        menu.addLayout(layout);

        ButtonOverlay returnButton;

        if (HeroAdventure.PLAYER.getCurrentStageType() == StageType.MAIN_MENU) {
            returnButton = new ButtonOverlay("menu-quit-button", "Quit", Color.BLACK, uiFont, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor);
        } else {
            returnButton = new ButtonOverlay("menu-return-button", "Return", Color.BLACK, uiFont, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor);
        }
        returnButton.setX(25);
        returnButton.setY(980);
        menu.addOverlay(returnButton);

        return menu;
    }

    @Override
    public Container loadMenu(boolean rightClick, int page, boolean loadMenu) {
        return new DefaultMainMenu().loadMenu(rightClick, page, loadMenu);
    }

    @Override
    public Container settingMenu(boolean rightClick) {
        return new DefaultMainMenu().settingMenu(rightClick);
    }

    @Override
    public Container aboutMenu(boolean rightClick) {
        return new DefaultMainMenu().aboutMenu(rightClick);
    }

    @Override
    public ButtonOverlay savePreview(Save save, int page, int index) {
        return new DefaultMainMenu().savePreview(save, page, index);
    }
}
