package me.piitex.game;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.piitex.game.characters.NarCharacter;
import me.piitex.game.characters.YouCharacter;
import me.piitex.game.data.GameData;
import me.piitex.game.listeners.DonateListener;
import me.piitex.game.listeners.MenuListener;
import me.piitex.game.stories.Chapter1Story;
import me.piitex.game.stories.IntroStory;
import me.piitex.renjava.RenJava;
import me.piitex.renjava.api.Game;
import me.piitex.renjava.api.loaders.FontLoader;
import me.piitex.renjava.configuration.Configuration;
import me.piitex.renjava.configuration.RenJavaConfiguration;
import me.piitex.renjava.events.defaults.ScenesEventListener;
import me.piitex.renjava.gui.Container;
import me.piitex.renjava.gui.DisplayOrder;
import me.piitex.renjava.gui.StageType;
import me.piitex.renjava.gui.Window;
import me.piitex.renjava.gui.containers.EmptyContainer;
import me.piitex.renjava.gui.layouts.VerticalLayout;
import me.piitex.renjava.gui.overlays.ButtonOverlay;
import me.piitex.renjava.gui.overlays.ImageOverlay;
import me.piitex.renjava.gui.overlays.TextOverlay;

@Game(name = "Hero Adventure", author = "piitex", version = "0.0.1")
@Configuration(title = "{name} v{version}", width = 1920, height = 1080)
public class HeroAdventure extends RenJava {
    private static HeroAdventure instance;

    @Override
    public void preEnabled() {
        instance = this;

        // Configure application
        RenJavaConfiguration configuration = getConfiguration();
        configuration.setUiFont(new FontLoader("Roboto-Black.ttf", 32));
        configuration.setDefaultFont(new FontLoader("Roboto-Regular.ttf", 28));
        configuration.setChoiceButtonFont(configuration.getDefaultFont());
        configuration.setHoverColor(Color.rgb(66, 147, 245));
        configuration.setChoiceButtonColor(Color.WHITE);

        // Register Listeners
        registerListener(new MenuListener());
        registerListener(new DonateListener());

    }

    @Override
    public void createBaseData() {
        // Create characters
        NarCharacter narCharacter = new NarCharacter();
        registerCharacter(narCharacter);

        YouCharacter youCharacter = new YouCharacter();
        registerCharacter(youCharacter);

        // Testing exceptions
        //getCharacter("asdasd"); // This should throw invalid character exception

        // Persistent Data
        // All data in here will be saved to the save file.
        GameData gameData = new GameData();
        registerData(gameData);
    }

    @Override
    public Window buildSplashScreen() {
        // Splash screen is a beginning screen. Usually a 600x400 window that represents the loading process.
        // Return null to skip it.
        return null;
    }

    @Override
    public Container buildMainMenu(boolean rightClick) {
        // Empty container behaves like the old menu system.
        // It is essentially an empty box which you add overlays to.
        Container menu = new EmptyContainer(1920, 1080);
        menu.addOverlay(new ImageOverlay("gui/main_menu.png"));

        // Basic text overlay
        TextOverlay gameText = new TextOverlay(name + ' ' + version, new FontLoader(getConfiguration().getUiFont(), 36), 1500, 975);

        // Add the overlay to the container
        menu.addOverlay(gameText);

        return menu;
    }

    @Override
    public Container buildSideMenu(boolean rightClickedMenu) {
        Container menu = new EmptyContainer(1920, 1080, DisplayOrder.HIGH);

        ImageOverlay imageOverlay = new ImageOverlay("gui/overlay/main_menu.png");
        imageOverlay.setOrder(DisplayOrder.LOW);
        menu.addOverlay(imageOverlay);

        FontLoader uiFont = RenJava.getInstance().getConfiguration().getUiFont();

        Color hoverColor = getConfiguration().getHoverColor();

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
        if (rightClickedMenu) {
            layout.addOverlays(saveButton);
        }
        layout.addOverlays(optionsButton, aboutButton, donateButton);

        // You don't have to add the button overlays just add the layout which already contains the overlays.
        menu.addLayout(layout);

        ButtonOverlay returnButton;

        if (getPlayer().getCurrentStageType() == StageType.MAIN_MENU) {
            returnButton = new ButtonOverlay("menu-quit-button", "Quit", Color.BLACK, uiFont, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor);
        } else {
            returnButton = new ButtonOverlay("menu-return-button", "Return", Color.BLACK, uiFont, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor);
        }
        returnButton.setX(25);
        returnButton.setY(1000);
        menu.addOverlay(returnButton);

        return menu;
    }

    @Override
    public void createStory() {
        // Create and Map Stories
        new IntroStory("intro");
        new Chapter1Story("chap1");
    }

    @Override
    public void start() {
        // Player presses 'start'
        getPlayer().startStory("intro");
    }

    public static HeroAdventure getInstance() {
        return instance;
    }
}
