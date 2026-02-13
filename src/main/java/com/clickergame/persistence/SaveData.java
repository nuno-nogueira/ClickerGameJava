package com.clickergame.persistence;
import java.io.*;
import java.util.HashMap;
import com.clickergame.core.GameState;

public class SaveData implements Serializable{
    /******** CORE ********/
    public int clicks;
    public double coins;
    public int totalBuildings;
    public int totalClicks;
    public int totalCookies;
    public double globalMultiplier;
    public double passiveIncome;
    public double passiveIncomeMultiplier;

    public GameState gameState;

    /******** BUILDINGS ********/
    public HashMap<String, Integer> buildingQuantitites = new HashMap<>();
    public HashMap<String, Integer> buildingPrices = new HashMap<>();
    public HashMap<String, Double> buildingSynergies = new HashMap<>();

    /******** UPGRADES ********/
    public HashMap<String, Boolean> purchasedUpgrades = new HashMap<>();
    
    /******** SYNERGIES ********/
    public HashMap<String, Boolean> claimedSynergies = new HashMap<>();

    /******** CRITICAL SYSTEM ********/
    public double criticalChance;
    public double criticalPower;

    /******** GOLDEN COOKIE SYSTEM ********/
    public double goldenCookieChance;
    public int goldenCookieClicks;
    public double lifespanMultiplier;

    /******** PRESTIGE SYSTEM ********/
    public int totalPrestiges;
    public int prestigePrice;
    public int sugarCrystals;
    public HashMap<Integer, Integer> prestiges = new HashMap<>();

    public SaveData() {
        clicks = 1;
        coins = 0d;
        totalBuildings = 0;
        totalClicks = 0;
        totalCookies = 0;
        globalMultiplier = 1.0d;
        passiveIncome = 0;
        passiveIncomeMultiplier = 1.0d;

        criticalChance = 1.0d;
        criticalPower = 2.0d;

        goldenCookieChance = 1.0d;
        goldenCookieClicks = 0;
        lifespanMultiplier = 1.0d;

        sugarCrystals = 0;
        totalPrestiges = 0;
        prestigePrice = 100000000;
    }
}
