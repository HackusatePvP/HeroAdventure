package me.piitex.game.characters;

import javafx.scene.paint.Color;
import me.piitex.renjava.api.characters.Character;
import me.piitex.renjava.api.saves.data.Data;
import me.piitex.renjava.api.saves.data.PersistentData;

public class YouCharacter extends Character implements PersistentData {
    @Data private int gold;

    public YouCharacter() {
        super("mc", "You", Color.WHITE);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
