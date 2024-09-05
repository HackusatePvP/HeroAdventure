package me.piitex.game.listeners;

import me.piitex.game.HeroAdventure;
import me.piitex.renjava.events.EventListener;
import me.piitex.renjava.events.Listener;
import me.piitex.renjava.events.types.MainMenuDispatchEvent;
import me.piitex.renjava.events.types.StoryStartEvent;

public class MenuListener implements EventListener {

    @Listener
    public void onMainMenuRender(MainMenuDispatchEvent event) {
        HeroAdventure.getInstance().getTracks().playMusic("music.mp3", true);
    }

    @Listener
    public void onStart(StoryStartEvent event) {
        if (event.getStory().getId().equalsIgnoreCase("intro")) {
            HeroAdventure.getInstance().getTracks().stop();
            // Play intro music
        }
    }
}
