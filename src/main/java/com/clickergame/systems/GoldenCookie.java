package com.clickergame.systems;

public class GoldenCookie {
    private int value;
    private double lifespan;
    private String text;
    public String typeId;
    public int cookieChance = 10;

    public GoldenCookie(int value, double lifespan, String text, String typeId) {
        this.value = value;
        this.lifespan = lifespan;
        this.text = text;
        this.typeId = typeId;
    }

    // Getters
    public String GetText() { return text; };
    public int GetValue() { return value; };
    public double GetLifespan() { return lifespan; };
    public String GetTypeId() { return typeId; };
    public int GetCookieChance() { return cookieChance; };

    public void decreaseLifespan() {
        lifespan--;
    }

    public void resetLifespan(double newLifespan) {
        lifespan = newLifespan;
    }
}
