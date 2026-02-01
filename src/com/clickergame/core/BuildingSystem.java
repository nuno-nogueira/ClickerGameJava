package com.clickergame.core;

import java.util.HashMap;
import java.util.Map;

public class BuildingSystem {
    HashMap<String, Building> upgrades = new HashMap<String, Building>();

    // Create 'Buildings'
    public BuildingSystem() {
        upgrades.put("cursor", new Building("Cursor", 20, 0, 1, 1.0d));
        upgrades.put("grandma", new Building("Grandma", 125, 0, 3, 1.0d));
        upgrades.put("farm", new Building("Farm", 300, 0, 5, 1.0d));
        upgrades.put("mine", new Building("Mine", 750, 0, 10, 1.0d));
        upgrades.put("factory", new Building("Factory", 2000, 0, 20, 1.0d));
        upgrades.put("bank", new Building("Bank", 5000, 0, 50, 1.0d));
        upgrades.put("temple", new Building("Temple", 15000, 0, 100, 1.0d));
    }

    // Fetch the Building Info
    public Building getUpgrade(String id) {
        return upgrades.get(id);
    }

    public Boolean canBuy(String id, int coins) {
        return coins >= upgrades.get(id).GetPrice();
    }

    public int totalPassiveIncome() {
        int total = 0;
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
}
