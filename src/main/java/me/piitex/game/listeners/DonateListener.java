package me.piitex.game.listeners;

import me.piitex.game.HeroAdventure;
import me.piitex.renjava.RenJava;
import me.piitex.renjava.events.EventListener;
import me.piitex.renjava.events.Listener;
import me.piitex.renjava.events.types.ButtonClickEvent;
import me.piitex.renjava.gui.Container;
import me.piitex.renjava.gui.Window;

public class DonateListener implements EventListener {

    @Listener
    public void onButtonClick(ButtonClickEvent event) {
        // Handle button click event
        if (event.getButton().getId().equalsIgnoreCase("menu-donate-button")) {
            // Open link
            HeroAdventure.getInstance().getHost().showDocument("https://google.com/");
        }
    }
}
