package me.piitex.game.data;

import me.piitex.renjava.api.saves.data.Data;
import me.piitex.renjava.api.saves.data.PersistentData;

public class GameData implements PersistentData {
    // All fields tagged with @Data are saved to the save file.
    // All fields that are tagged with @Data must be generic types. (int, string, double...)
    // These fields cannot be final.
    @Data private int day;
    @Data private int totalDays;

    public int getDay() {
        return day;
    }
    // 0 - 6
    public void incrementDay() {
        day++;
        if (day > 6) {
            day = 0;
        }
        totalDays++;
    }

    public int getTotalDays() {
        return totalDays;
    }
}
