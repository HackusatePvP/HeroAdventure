package me.piitex.game.stories;

import javafx.scene.paint.Color;
import me.piitex.game.HeroAdventure;
import me.piitex.renjava.api.characters.Character;
import me.piitex.renjava.api.scenes.transitions.types.FadingTransition;
import me.piitex.renjava.api.scenes.types.ImageScene;
import me.piitex.renjava.api.scenes.types.choices.Choice;
import me.piitex.renjava.api.scenes.types.choices.ChoiceScene;
import me.piitex.renjava.api.stories.Story;
import me.piitex.renjava.gui.overlays.ImageOverlay;

public class IntroStory extends Story {

    public IntroStory(String id) {
        super(id);
    }

    @Override
    public void init() {
        // Fetch the characters
        Character nar = HeroAdventure.getInstance().getCharacter("nar");
        Character mc = HeroAdventure.getInstance().getCharacter("mc");

        // The first scene must contain an image. You can pass null to render a black screen.
        // Additionally, you can pass null to character parameter to skip rendering the textbox.
        addScene(new ImageScene("1", nar, "It's time to wake up again.", new ImageOverlay("stories/intro/introbg.png")).setBeginningTransition(new FadingTransition(0,1,3, Color.BLACK)));
        addScene(new ImageScene("2", mc, "But I don't want to wake up. I just want to sleep here all day."));
        addScene(new ImageScene("3", mc, "I guess I have a choice to make. Lay here all day or get up.."));


        ChoiceScene choiceScene = new ChoiceScene("4");
        choiceScene.addChoice(new Choice("no", "Lay in bed all day."));
        choiceScene.addChoice(new Choice("yes", "Get up."));

        // Logic in this method is a little weird...
        // This is an initialization method not a rendering method.
        // Logic must be applied to the rendering process.

        // The onChoiceEvent is called during the rendering process so we can pass logic here.
        // All events are called during the rendering.
        choiceScene.onChoice(event -> {
            // Handle when they select a choice.
            if (event.getChoice().getId().equalsIgnoreCase("yes")) {
                // Instead of calling addScene(); We have to use the displayScene function first.
                displayScene("5");
            }
            if (event.getChoice().getId().equalsIgnoreCase("no")) {
                displayScene("7");
            }
        });

        addScene(choiceScene); // Don't forget to add scene

        addScene(new ImageScene("5", mc, "I should do something today, it is in my best interest that I do."));
        addScene(new ImageScene("6", nar, "After some struggle and rolling out of the bed. I make my way into the town.")
                .onEnd(event -> {
                    // Prevents the engine from advancing to the next scene.
                    event.setAutoPlayNextScene(false);

                    Chapter1Story chap1 = (Chapter1Story) HeroAdventure.getInstance().getPlayer().getStory("chap1");
                    chap1.start();
                    // Start next story.
                }));
        addScene(new ImageScene("7", mc, "Too much work to get out of bed and quite frankly I'm too damn tired."));
        addScene(new ImageScene("8", nar, "It can be a different heroes turn today as I am taking the day off.").onEnd(event -> {
            // A little confusing, but indexes start at 0. The first scene will have the index of 0. The second scene will be 1.
            displayScene(0);
            // Re-loops the current story.
        }));
    }
}
