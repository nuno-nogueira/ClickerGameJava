package com.clickergame.systems;

import java.util.ArrayList;

public class Prestige {
    private ArrayList<Integer> price; // How much a prestige costs
    private String targetID; // Which stat will be buffed when a prestige upgrade is bought
    private ArrayList<Double> multiplier; // How much the stat is buffed
    private String name; // Prestige's upgrade name
    public Integer upgradeLevel = 0; // The upgrade's level that can be between 0 and 3
    private boolean purchased = false; // Check if an upgrade is fully bought

    public Prestige(ArrayList<Integer> price, String targetID, ArrayList<Double> multiplier, String name) {
        this.price = price;
        this.targetID = targetID;
        this.multiplier = multiplier;
        this.name = name;
    }

    // Getters
    public ArrayList<Integer> GetPriceList() { return price; };
    public String GetTargetID() { return targetID; };
    public ArrayList<Double> GetMultiplierList() { return multiplier; };
    public String GetName() { return name; };
    public Integer GetUpgradeLevel() { return upgradeLevel; };
    public boolean GetPurchased() { return purchased; };
    public Integer GetPrice(Integer i) { return price.get(i); };
    public Double GetMultiplier(Integer i) { return multiplier.get(i); };

    public void increaseLevel() {
        if (this.upgradeLevel < price.size()) this.upgradeLevel++;
    }

}
