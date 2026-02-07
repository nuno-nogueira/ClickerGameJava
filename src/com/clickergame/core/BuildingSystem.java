package com.clickergame.core;

import java.util.HashMap;
import java.util.Map;

import com.clickergame.systems.Building;

public class BuildingSystem {
    HashMap<String, Building> upgrades = new HashMap<String, Building>();

    // Create 'Buildings'
    public BuildingSystem() {
        upgrades.put("cursor", new Building("Cursor", 15, 0, 0.5, 1.0d));
        upgrades.put("grandma", new Building("Grandma", 100, 0, 3, 1.0d));
        upgrades.put("farm", new Building("Farm", 500, 0, 12, 1.0d));
        upgrades.put("mine", new Building("Mine", 2000, 0, 40, 1.0d));
        upgrades.put("wizard", new Building("Wizard Tower", 10000, 0, 120, 1.0d));
        upgrades.put("bank", new Building("Bank", 50000, 0, 400, 1.0d));
        upgrades.put("temple", new Building("Temple", 200000, 0, 1500, 1.0d));
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
}
