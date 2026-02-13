package com.clickergame.systems;

public class Upgrade {
    private int price; // Set the upgrade's price
    private double multiplier; // Sets how much a buff is increased
    private String targetID; // Which stat is impacted
    private String name;
    private String description;
    public Boolean purchased = false;

    public Upgrade(String name, String description, String targetID, int price, double multiplier) {
        this.price = price;
        this.multiplier = multiplier;
        this.targetID = targetID;
        this.name = name;
        this.description = description;
    }

    // Getters
    public int GetPrice() { return price; };
    public double GetMultiplier() { return multiplier; };
    public String getTargetID() { return targetID; };
    public String getName() { return name; };
    public String getDescription() { return description; };
    public Boolean isPurchased() { return purchased; };
    public void markAsPurchased() { purchased = true; };
}
