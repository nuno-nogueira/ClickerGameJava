package com.clickergame.systems;

public class Building {
    public int price; // Set the building's price which is later incremented by 'multiplier' attribute
    public int basePrice; // Set the building's base price, does not change 
    public int quantity; // Set the number of buildings (for each building type)
    private double production; // Set how much coins a building produces per second
    private String name; // Set the building's name
    public double multiplier; // Set how much the production can be multiplied
    private String synergyTarget; // Set the stat/system that is buffed for each synergy gained
    private double synergyValue; // Set the synergy's total buff gain
    public double synergyBuff; // Set the synergy's buff value for each synergy gained
    public double priceIncrement; // Set the price's multiplier everytime a building is bought

    public Building(String name, int price, int basePrice, int quantity, double production, double multiplier, String synergyTarget, double synergyValue, double synergyBuff, double priceIncrement){
        this.price = price;
        this.basePrice = basePrice;
        this.quantity = 0;
        this.production = production;
        this.multiplier = 1.0d;
        this.name = name;
        this.synergyTarget = synergyTarget;
        this.synergyValue = synergyValue;
        this.synergyBuff = synergyBuff;
        this.priceIncrement = priceIncrement;
    }

    // Getters
    public int GetPrice(){ return price; };
    public int GetQuantity(){ return quantity; };
    public double GetProduction(){ return production; };
    public String GetName(){ return name; };
    public double GetMultiplier(){ return multiplier; };
    public String GetSynergyTarget() { return synergyTarget; };
    public double GetSynergyValue() { return synergyValue; };
    public double GetSynergyBuff() { return synergyBuff; };

    public void incrementQuantity() {
        quantity++;
        this.price *= this.priceIncrement;
    }

    public void incrementProduction(int newValue) {
        production *= newValue;
    }

    public int passiveIncome() {
        return (int)(quantity * production * multiplier);
    }

    public void applyMultiplier(double value) {
        multiplier *= value;
    }
}
