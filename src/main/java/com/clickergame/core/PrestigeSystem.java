package com.clickergame.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.clickergame.systems.Prestige;

public class PrestigeSystem {
    public HashMap<Integer, Prestige> prestiges = new HashMap<Integer, Prestige>();
    int prestigePrice;
    int sugarCrystals;
    int totalPrestiges;

    GameState gamestate;
    CriticalSystem criticalSystem;
    GoldenCookieSystem goldenCookieSystem;
    BuildingSystem buildingSystem;

    public PrestigeSystem(GameState gameState, CriticalSystem criticalSystem, GoldenCookieSystem goldenCookieSystem, BuildingSystem buildingSystem) {
        prestiges.put(1, new Prestige(new ArrayList<>(List.of(1)), "critChance", new ArrayList<>(List.of(0.5)), "Tiny Luck"));
        prestiges.put(2, new Prestige(new ArrayList<>(List.of(1)), "global", new ArrayList<>(List.of(0.5)), "Faster Oven"));
        prestiges.put(3, new Prestige(new ArrayList<>(List.of(3)), "buildingCost", new ArrayList<>(List.of(5.0)), "Cheap Tools"));

        prestiges.put(4, new Prestige(new ArrayList<>(List.of(1)), "goldenChance", new ArrayList<>(List.of(1.0)), "Golden Smell"));
        prestiges.put(5, new Prestige(new ArrayList<>(List.of(3, 6, 10)), "passive", new ArrayList<>(List.of(1.5, 3.0, 5.0)), "Sugar Momentum"));
        prestiges.put(6, new Prestige(new ArrayList<>(List.of(4)), "clicks", new ArrayList<>(List.of(1.0)), "Fast Hands"));

        prestiges.put(7, new Prestige(new ArrayList<>(List.of(5, 10, 15)), "critChance", new ArrayList<>(List.of(1.0, 2.0, 3.0)), "Lucky Core"));
        prestiges.put(8, new Prestige(new ArrayList<>(List.of(7, 14)), "critPower", new ArrayList<>(List.of(25.0, 50.0)), "Overclocked Impact"));
        
        prestiges.put(9, new Prestige(new ArrayList<>(List.of(12)), "critChance", new ArrayList<>(List.of(5.0)), "Critical Chain"));
        prestiges.put(10, new Prestige(new ArrayList<>(List.of(6, 12, 20)), "goldenChance", new ArrayList<>(List.of(0.5, 1.0, 2.0)), "Golden Aroma"));
        prestiges.put(11, new Prestige(new ArrayList<>(List.of(3, 6, 10)), "goldenDuration", new ArrayList<>(List.of(0.1, 0.2, 0.35)), "Lingering Gold"));

        prestiges.put(12, new Prestige(new ArrayList<>(List.of(8, 16)), "buildingCost", new ArrayList<>(List.of(10.0, 20.0)), "Efficient Blueprints"));
        prestiges.put(13, new Prestige(new ArrayList<>(List.of(4, 8, 12)), "global", new ArrayList<>(List.of(1.25, 1.40, 1.50)), "Ultra Ovens"));
        prestiges.put(14, new Prestige(new ArrayList<>(List.of(10, 20)), "passive", new ArrayList<>(List.of(10.0, 15.0)), "SUGAR RUSHH!"));

        this.gamestate = gameState;
        this.criticalSystem = criticalSystem;
        this.goldenCookieSystem = goldenCookieSystem;
        this.buildingSystem = buildingSystem;
    }

    // Getters
    public HashMap<Integer, Prestige> getAllPrestiges() { return prestiges; };
    public Prestige getPrestige(Integer id) { return prestiges.get(id); };
    public int getPrice() { return prestigePrice; };
    public int getCrystals() { return sugarCrystals; };
    public int getTotalPrestiges() { return totalPrestiges; };

    public Boolean canBuyPrestige(double totalCookies) {
        return totalCookies >= getPrice();
    }

    public Boolean canBuyUpgrade(Integer id, Integer upgradeLevel, Integer sugarCrystals) {
        return sugarCrystals >= prestiges.get(id).GetPrice(upgradeLevel);
    }

    public void buy(Integer id) {

        switch (getPrestige(id).GetTargetID()) {
            case "critChance":
                this.criticalSystem.criticalChance += getPrestige(id).GetMultiplier(getPrestige(id).GetUpgradeLevel());
                break;
            case "critPower":
                this.criticalSystem.criticalPower += (getPrestige(id).GetMultiplier(getPrestige(id).GetUpgradeLevel()) / 100);
                break;
            case "passive":
                this.gamestate.passiveIncomeMultiplier *= (1 + (getPrestige(id).GetMultiplier(getPrestige(id).GetUpgradeLevel()) / 100));
                break;
            case "clicks":
                this.gamestate.clicks += getPrestige(id).GetMultiplier(getPrestige(id).GetUpgradeLevel());
                break;
            case "global":
                this.gamestate.globalMultiplier += (getPrestige(id).GetMultiplier(getPrestige(id).GetUpgradeLevel()) / 100);
                break;
            case "goldenChance":
                this.goldenCookieSystem.cookieChance += getPrestige(id).GetMultiplier(getPrestige(id).GetUpgradeLevel());
            case "goldenDuration":
                this.goldenCookieSystem.lifespanMultiplier *= (1 + (getPrestige(id).GetMultiplier(getPrestige(id).GetUpgradeLevel()) / 100));
                break;
            default:
                break;
        }

    }
}
