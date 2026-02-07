package com.clickergame.systems;

public class Building {
    private int price;
    private int quantity;
    private double production;
    private String name;
    private double multiplier;
    private String synergyTarget;
    private double synergyValue;
    public double synergyBuff;
    public double priceIncrement;

    public Building(String name, int price, int quantity, double production, double multiplier, String synergyTarget, double synergyValue, double synergyBuff, double priceIncrement){
        this.price = price;
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

        System.out.println(name + "upgrade bought for " + this.price + "! :o");
        //price += price + Math.pow(1.01, quantity)
        this.price *= this.priceIncrement;
    }

    public void incrementProduction(int newValue) {
        production *= newValue;
        System.out.println(name + "production bought for " + newValue + "! :o");
    }

    public int passiveIncome() {
        return (int)(quantity * production * multiplier);
    }

    public void applyMultiplier(double value) {
        multiplier *= value;
    }
}
