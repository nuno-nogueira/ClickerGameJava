package com.clickergame.systems;

import java.util.ArrayList;

public class Prestige {
    private ArrayList<Integer> price;
    private String targetID;
    private ArrayList<Double> multiplier;
    private String name;
    public Integer upgradeLevel = 0;
    private boolean purchased = false;

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
