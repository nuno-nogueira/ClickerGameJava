package com.clickergame.systems;

public class Building {
    private int price;
    private int quantity;
    private int production;
    private String name;
    private double multiplier;

    public Building(String name, int price, int quantity, int production, double multiplier){
        this.price = price;
        this.quantity = 0;
        this.production = production;
        this.multiplier = 1.0d;
        this.name = name;
    }

    // Getters
    public int GetPrice(){ return price; };
    public int GetQuantity(){ return quantity; };
    public int GetProduction(){ return production; };
    public String GetName(){ return name; };
    public double GetMultiplier(){ return multiplier; };

    public void incrementQuantity() {
        quantity++;

        System.out.println(name + "upgrade bought for " + price + "! :o");

        price += Math.floor(price * 1.5);
    }

    public void incrementProduction(int newValue) {
        production *= newValue;
        System.out.println(name + "production bought for " + newValue + "! :o");
    }

    public int passiveIncome() {
        return (int)(quantity * production * multiplier);
    }

    public void applyMultiplier(double value) {
        multiplier = value;
    }
}
