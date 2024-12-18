package me.piitex.game;

import javafx.scene.paint.Color;
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
    public void createStory() {
        // Create and Map Stories
        new IntroStory("intro");
        new Chapter1Story("chap1");
    }

    @Override
    public void start() {
        // Player presses 'start'
        PLAYER.startStory("intro");
    }

    public static HeroAdventure getInstance() {
        return instance;
    }
}
