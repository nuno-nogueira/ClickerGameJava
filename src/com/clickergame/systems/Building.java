package com.clickergame.systems;

public class Building {
    private int price;
    private int quantity;
    private double production;
    private String name;
    private double multiplier;

    public Building(String name, int price, int quantity, double production, double multiplier){
        this.price = price;
        this.quantity = 0;
        this.production = production;
        this.multiplier = 1.0d;
        this.name = name;
    }

    // Getters
    public int GetPrice(){ return price; };
    public int GetQuantity(){ return quantity; };
    public double GetProduction(){ return production; };
    public String GetName(){ return name; };
    public double GetMultiplier(){ return multiplier; };

    public void incrementQuantity() {
        quantity++;

        System.out.println(name + "upgrade bought for " + price + "! :o");

        price += price + Math.pow(1.16, quantity);
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
