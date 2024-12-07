package me.piitex.game.stories;

import me.piitex.game.HeroAdventure;
import me.piitex.game.characters.YouCharacter;
import me.piitex.renjava.RenJava;
import me.piitex.renjava.api.characters.Character;
import me.piitex.renjava.api.scenes.types.ImageScene;
import me.piitex.renjava.api.scenes.types.input.InputScene;
import me.piitex.renjava.api.stories.Story;
import me.piitex.renjava.gui.overlays.ImageOverlay;

public class Chapter1Story extends Story {
    /**
     * Creates a base story line. This can also be referred to character events or even chapters.
     *
     * @param id Used to get the scene later.
     */
    public Chapter1Story(String id) {
        super(id);
        onEnd(endEvent -> {
            HeroAdventure.PLAYER.startStory("intro");
        });
    }

    @Override
    public void init() {
        Character nar = RenJava.getInstance().getCharacter("nar");
        Character you = RenJava.getInstance().getCharacter("mc");

        InputScene inputScene = new InputScene("1", "Please enter your name: ", new ImageOverlay("stories/intro/introbg.png"));
        inputScene.setDefaultInput("Astro");

        inputScene.onSet(event -> {
            YouCharacter youCharacter = (YouCharacter) RenJava.getInstance().getCharacter("mc");
            youCharacter.setName(event.getInput());

            // If you update var information you need to refresh
            this.refresh();
        });
        addScene(inputScene);

        addScene(new ImageScene("2", nar, "Welcome, " + you.getName() + ", to Hero Adventure"));


    }
}
