package me.piitex.game.listeners;

import me.piitex.renjava.events.EventListener;
import me.piitex.renjava.events.Listener;
import me.piitex.renjava.events.types.MainMenuDispatchEvent;
import me.piitex.renjava.events.types.StoryStartEvent;

public class MenuListener implements EventListener {

    @Listener
    public void onMainMenuRender(MainMenuDispatchEvent event) {
        // This is slashed just for testing purposes, the function below works perfectly fine (as long as you have a music called music.mp3).
        // For testing I slashed the method so I don't have to hear the menu music on repeat.
        //HeroAdventure.getInstance().getTracks().playMusic("music.mp3", true);
    }

    @Listener
    public void onStart(StoryStartEvent event) {
        if (event.getStory().getId().equalsIgnoreCase("intro")) {
            // Slashed out for the same reason above. Works fine.
            // HeroAdventure.getInstance().getTracks().stop();
            // Play intro music
        }
    }
}
