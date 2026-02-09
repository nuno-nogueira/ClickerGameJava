package com.clickergame.persistence;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SaveData implements Serializable{
    /******** CORE ********/
    public int clicks;
    public double coins;
    public int totalBuildings;
    public int totalClicks;
    public int totalCookies;
    public double globalMultiplier;

    /******** BUILDINGS ********/
    public HashMap<String, Integer> buildingQuantitites = new HashMap<>();
    public HashMap<String, Integer> buildingPrices = new HashMap<>();

    /******** UPGRADES ********/
    public HashMap<String, Boolean> purchasedUpgrades = new HashMap<>();
    
    /******** SYNERGIES ********/
    public HashMap<String, Boolean> claimedSynergies = new HashMap<>();

    /******** CRITICAL SYSTEM ********/
    public double crititcalChance;
    public double crititcalPower;

    /******** GOLDEN COOKIE SYSTEM ********/
    public double goldenCookieChance;
    public int goldenCookieClicks;

    public SaveData() {
        clicks = 1;
        coins = 1000000d;
        totalBuildings = 0;
        totalClicks = 0;
        totalCookies = 0;
        globalMultiplier = 1.0d;

        crititcalChance = 1.0d;
        crititcalPower = 2.0d;

        goldenCookieChance = 1.0d;
        goldenCookieClicks = 0;
    }
}
