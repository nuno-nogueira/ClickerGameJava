package com.clickergame.systems;

public class GoldenCookie {
    private int value;
    private int lifespan;
    private String text;
    public String typeId;

    public GoldenCookie(int value, int lifespan, String text, String typeId) {
        this.value = value;
        this.lifespan = lifespan;
        this.text = text;
        this.typeId = typeId;
    }

    // Getters
    public String GetText() { return text; };
    public int GetValue() { return value; };
    public int GetLifespan() { return lifespan; };
    public String GetTypeId() { return typeId; };

    public void decreaseLifespan() {
        lifespan--;
    }

    public void resetLifespan(int newLifespan) {
        lifespan = newLifespan;
    }
}
