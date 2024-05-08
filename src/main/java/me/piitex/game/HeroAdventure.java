package me.piitex.game;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.piitex.game.characters.NarCharacter;
import me.piitex.game.characters.YouCharacter;
import me.piitex.game.listeners.DonateListener;
import me.piitex.game.stories.IntroStory;
import me.piitex.renjava.RenJava;
import me.piitex.renjava.api.Game;
import me.piitex.renjava.api.loaders.FontLoader;
import me.piitex.renjava.configuration.Configuration;
import me.piitex.renjava.configuration.RenJavaConfiguration;
import me.piitex.renjava.gui.Menu;
import me.piitex.renjava.gui.layouts.impl.VerticalLayout;
import me.piitex.renjava.gui.overlay.ButtonOverlay;
import me.piitex.renjava.gui.overlay.ImageOverlay;
import me.piitex.renjava.gui.overlay.TextOverlay;

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
        configuration.setHoverColor(Color.rgb(66, 147, 245));

        // Register Listeners
        registerListener(new DonateListener());

    }

    @Override
    public void createBaseData() {
        // Create characters
        NarCharacter narCharacter = new NarCharacter();
        registerCharacter(narCharacter);

        YouCharacter youCharacter = new YouCharacter();
        registerCharacter(youCharacter);

        // Persistent Data
    }

    @Override
    public Menu buildSplashScreen() {
        return null;
    }

    @Override
    public Menu buildTitleScreen() {
        Menu menu = new Menu(1920, 1080, new ImageOverlay("gui/main_menu.png"));

        TextOverlay gameText = new TextOverlay(name + ' ' + version, new FontLoader(getConfiguration().getUiFont(), 36), 1500, 975);

        menu.addOverlay(gameText);

        return menu;
    }

    @Override
    public Menu buildSideMenu() {
        Menu menu = new Menu(1920, 1080, new ImageOverlay("gui/overlay/main_menu.png"));

        Font uiFont = RenJava.getInstance().getConfiguration().getUiFont().getFont();

        Color hoverColor = getConfiguration().getHoverColor();

        ButtonOverlay startButton = new ButtonOverlay("menu-start-button", "Start", uiFont, Color.BLACK, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor, 1, 1);
        ButtonOverlay loadButton = new ButtonOverlay("menu-load-button", "Load", uiFont, Color.BLACK, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor, 1, 1);
        ButtonOverlay saveButton = new ButtonOverlay("menu-save-button", "Save", uiFont, Color.BLACK, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor, 1, 1);
        ButtonOverlay optionsButton = new ButtonOverlay("menu-preference-button", "Preferences", uiFont, Color.BLACK, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor, 1, 1);
        ButtonOverlay aboutButton = new ButtonOverlay("menu-about-button", "About", uiFont, Color.BLACK, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor, 1, 1);
        ButtonOverlay donateButton = new ButtonOverlay("menu-donate-button", "Donate", uiFont, Color.BLACK, Color.TRANSPARENT, Color.TRANSPARENT, hoverColor, 1, 1);

        // Create vbox for the buttons. You can also do an HBox
        VerticalLayout layout = new VerticalLayout(400, 500);
        layout.setX(50);
        layout.setY(250);
        layout.setSpacing(20);
        layout.addOverlays(startButton, loadButton, saveButton, donateButton, optionsButton, aboutButton);

        // You don't have to add the button overlays just add the layout which already contains the overlays.
        menu.addLayout(layout);

        return menu;
    }

    @Override
    public void createStory() {
        // Create and Map Stories
        new IntroStory("intro");
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
