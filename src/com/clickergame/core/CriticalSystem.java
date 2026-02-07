package com.clickergame.core;

public class CriticalSystem {
    public double criticalChance = 1.0d;
    public double criticalPower = 2.0d;

    // Getters
    public double GetCriticalChance() { return criticalChance; };
    public double GetCriticalPower() { return criticalPower; };


    public double applyCritical(double coins, int clicks, int totalCookies) {
        coins += (clicks * criticalPower);
        totalCookies += (clicks * criticalPower);
        System.out.println("Oh, my! CRITICAL HIT! " + (clicks * criticalPower) + " clicks");
        return coins;
    }

    public boolean tryCritical(double chance) {
        if (chance <= criticalChance) {return true;} else {return false;}
    }
}
