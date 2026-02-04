package com.clickergame.core;

public class CriticalSystem {
    double criticalChance = 1.0d;
    double criticalPower = 2.0d;

    public double applyCritical(double coins, int clicks) {
        coins += (clicks * criticalPower);
        System.out.println("Oh, my! CRITICAL HIT! " + (clicks * criticalPower) + " clicks");
        return coins;
    }

    public boolean tryCritical(double chance) {
        if (chance <= criticalChance) {return true;} else {return false;}
    }
}
