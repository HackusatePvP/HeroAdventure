package me.piitex.game.stories;

import me.piitex.game.HeroAdventure;
import me.piitex.renjava.api.characters.Character;
import me.piitex.renjava.api.scenes.types.ImageScene;
import me.piitex.renjava.api.scenes.types.choices.Choice;
import me.piitex.renjava.api.scenes.types.choices.ChoiceScene;
import me.piitex.renjava.api.stories.Story;
import me.piitex.renjava.gui.overlay.ImageOverlay;

public class IntroStory extends Story {

    public IntroStory(String id) {
        super(id);
    }

    @Override
    public void init() {
        // Add the scenes.
        Character nar = HeroAdventure.getInstance().getCharacter("nar");
        Character mc = HeroAdventure.getInstance().getCharacter("mc");

        addScene(new ImageScene("1", nar, "It's time to wake up again.", new ImageOverlay("stories/intro/introbg.png")));
        addScene(new ImageScene("2", mc, "But I don't want to wake up. I just want to sleep here all day."));
        addScene(new ImageScene("3", mc, "I guess I have a choice to make. Lay here all day or get up.."));

        ChoiceScene choiceScene = new ChoiceScene("4");
        choiceScene.addChoice(new Choice("no", "Lay in bed all day."));
        choiceScene.addChoice(new Choice("yes", "Get up."));

        choiceScene.onChoice(event -> {
            // Handle when they select a choice.
            if (event.getChoice().getId().equalsIgnoreCase("yes")) {
                displayScene("5");
            }
            if (event.getChoice().getId().equalsIgnoreCase("no")) {
                displayScene("7");
            }
        });

        addScene(choiceScene);

        addScene(new ImageScene("5", mc, "I should do something today, it is in my best interest that I do."));
        addScene(new ImageScene("6", nar, "After some struggle and rolling out of the bed. I make my way into the town.")
                .onEnd(event -> {
                    event.setAutoPlayNextScene(false);
                    // Starts a different.
                }));
        addScene(new ImageScene("7", mc, "Too much work to get out of bed and quite frankly I'm too damn tired."));
        addScene(new ImageScene("8", nar, "It can be a different heroes turn today as I am taking the day off.").onEnd(event -> {
            displayScene(1);
        }));
    }
}
