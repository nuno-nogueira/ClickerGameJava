package com.clickergame.systems;

public class GoldenCookie {
    private int value; // Set the golden cookie's value
    private double lifespan; // Sets how much the cookie lasts (in seconds)
    private String text; // Set the short description for each cookie everytime it is clicked
    public String typeId; // Set the stat that will be buffed while the golden cookie is active
    public int cookieChance = 10; // Set the chance there is to spawn a golden cookie 

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
