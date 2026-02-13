package com.clickergame.systems;

import java.util.HashMap;

import com.clickergame.core.CriticalSystem;
import com.clickergame.core.GameState;
import com.clickergame.core.GoldenCookieSystem;

public class Synergy {
    private String targetId; // Which stat is impacted by the synergy
    private double value; // The synergy's buff value
    private HashMap<String, Integer> requirement = new HashMap<>(); // The requirements to increase the synergy's buff
    private GameState gamestate;
    private CriticalSystem criticalSystem;
    private GoldenCookieSystem goldenCookieSystem;
    public boolean isClaimed; // Verify if a synergy is claimed
    
    public Synergy(GameState gamestate, CriticalSystem criticalSystem, GoldenCookieSystem goldenCookieSystem, String targetId, double value, HashMap<String, Integer> requirement, boolean isClaimed) {
        this.gamestate = gamestate;
        this.criticalSystem = criticalSystem;
        this.goldenCookieSystem = goldenCookieSystem;
        this.targetId = targetId;
        this.value = value;
        this.requirement = requirement;
        this.isClaimed = isClaimed;
    }

    // Getters
    public String GetTargetId() { return targetId; };
    public double GetValue() { return value; };
    public HashMap<String, Integer> GetRequirement() { return requirement; };
    public boolean GetIsClaimed() { return isClaimed; };

    public void applySynergy(String target, double value) {
        switch (target) {
            case "global":
                gamestate.globalMultiplier += (value / 100);
                break;
            case "critChance":
                criticalSystem.criticalChance += value;
                break;
            case "critPower":
                criticalSystem.criticalPower += (value / 100);
                break;
            case "goldenCookie":
                goldenCookieSystem.cookieChance += value;
                break;
            default:
                break;
        }
    }

    public void claimSynergy() {
        this.isClaimed = true;
    }
}

