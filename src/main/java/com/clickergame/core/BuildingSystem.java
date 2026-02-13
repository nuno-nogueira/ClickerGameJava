package com.clickergame.core;

import java.util.HashMap;
import java.util.Map;

import com.clickergame.systems.Building;

public class BuildingSystem {
    HashMap<String, Building> upgrades = new HashMap<String, Building>();
    GameState gamestate;
    CriticalSystem criticalSystem;
    GoldenCookieSystem goldenCookieSystem;

    // Create 'Buildings'
    public BuildingSystem(GameState gamestate, CriticalSystem criticalSystem, GoldenCookieSystem goldenCookieSystem) {
        upgrades.put("cursor", new Building("Cursor", 15, 15, 0, 0.5, 1.0d, "critChance", 0.2d, 0, 1.15));
        upgrades.put("grandma", new Building("Grandma", 100, 100, 0, 3, 1.0d, "global", 0.5d, 0, 1.18));
        upgrades.put("farm", new Building("Farm",500, 500, 0, 12, 1.0d, "goldenCookie", 0.5d, 0, 1.20));
        upgrades.put("mine", new Building("Mine",2000, 2000, 0, 40, 1.0d, "critPower", 0.1d, 0, 1.23));
        upgrades.put("wizard", new Building("Wizard Tower",10000, 10000, 0, 120, 1.0d, "critChance", 0.3d, 0, 1.25));
        upgrades.put("bank", new Building("Bank",50000, 50000, 0, 400, 1.0d, "global", 1.0d, 0, 1.27));
        upgrades.put("temple", new Building("Temple",200000, 200000, 0, 1500, 1.0d, "global", 2.0d, 0, 1.30));

        this.gamestate = gamestate;
        this.criticalSystem = criticalSystem;
        this.goldenCookieSystem = goldenCookieSystem;
    }

    // Fetch the Building Info
    public Building getUpgrade(String id) {
        return upgrades.get(id);
    }

    public Boolean canBuy(String id, double coins) {
        return coins >= upgrades.get(id).GetPrice();
    }

    public double totalPassiveIncome() {
        double total = 0d;
        for (Building u : upgrades.values()) {
            total += u.passiveIncome();
        }
        

        return total;
    }

    public void buy(String id) {
        upgrades.get(id).incrementQuantity();
    }

    public Map<String, Building> getAllUpgrades() {
        return upgrades;
    }

    public void applyMultiplier(String id, double multiplier) {
        upgrades.get(id).applyMultiplier(multiplier);
    }

    public void applySynergy(String id) {
        Building building = upgrades.get(id);

        switch (building.GetSynergyTarget()) {
            case "global":
                gamestate.globalMultiplier += (building.GetSynergyValue() / 100);
                building.synergyBuff += (building.GetSynergyValue() / 100); 
                break;
            case "critChance":
                criticalSystem.criticalChance += building.GetSynergyValue();
                building.synergyBuff += building.GetSynergyValue();
                break;
            case "critPower":
                criticalSystem.criticalPower += building.GetSynergyValue();
                building.synergyBuff += building.GetSynergyValue();
                break;
            case "goldenCookie":
                goldenCookieSystem.cookieChance += building.GetSynergyValue();
                building.synergyBuff += building.GetSynergyValue();
                break;
            default:
                break;
        }
    }
}
