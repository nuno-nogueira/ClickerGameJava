package com.clickergame.core;

import java.sql.Savepoint;
import java.util.HashMap;

import com.clickergame.persistence.SaveData;
import com.clickergame.systems.Building;
import com.clickergame.systems.GoldenCookie;
import com.clickergame.systems.Synergy;
import com.clickergame.systems.Upgrade;

public  class GameState{
    private int clicks;
    public double coins;
    public int totalBuildings;
    public double globalMultiplier;
    public int totalClicks;
    public int totalCookies;

    private BuildingSystem buildingSystem;
    private UpgradeSystem upgradeSystem = new UpgradeSystem();
    private CriticalSystem criticalSystem;
    private GoldenCookieSystem goldenCookieSystem;
    HashMap<Integer, Synergy> globalSynergies = new HashMap<Integer, Synergy>();

    public GameState() {
        this.criticalSystem = new CriticalSystem();
        this.goldenCookieSystem = new GoldenCookieSystem(this, criticalSystem);
        this.buildingSystem = new BuildingSystem(this, criticalSystem, goldenCookieSystem);

        globalSynergies.put(1, new Synergy(this, criticalSystem, goldenCookieSystem, "global", 5, new HashMap<>() {{put("global", 10);}}, false));
        globalSynergies.put(2, new Synergy(this, criticalSystem, goldenCookieSystem,"global", 10, new HashMap<>() {{put("global", 25);}}, false));
        globalSynergies.put(3, new Synergy(this, criticalSystem, goldenCookieSystem,"critChance", 1, new HashMap<>() {{put("global", 50);}}, false));
        globalSynergies.put(4, new Synergy(this, criticalSystem, goldenCookieSystem,"goldenCookie", 2, new HashMap<>() {{put("global", 75);}}, false));
        globalSynergies.put(5, new Synergy(this, criticalSystem, goldenCookieSystem,"critPower", 10, new HashMap<>() {{put("global", 3);}}, false));
        globalSynergies.put(6, new Synergy(this, criticalSystem, goldenCookieSystem,"global", 2, new HashMap<>() {{put("global", 150);}}, false));
        globalSynergies.put(7, new Synergy(this, criticalSystem, goldenCookieSystem,"goldenCookie", 15, new HashMap<>() {{put("global", 200);}}, false));
        globalSynergies.put(8, new Synergy(this, criticalSystem, goldenCookieSystem,"critPower", 20, new HashMap<>() {{put("global", 300);}}, false));
        globalSynergies.put(9, new Synergy(this, criticalSystem, goldenCookieSystem,"critChance", 3, new HashMap<>() {{put("global", 400);}}, false));
        globalSynergies.put(10, new Synergy(this, criticalSystem, goldenCookieSystem,"global", 10, new HashMap<>() {{put("global", 500);}}, false));
    }

    // Getters
    public int GetClicks(){ return clicks; };
    public double GetCoins(){ return coins; };
    public BuildingSystem GetbuildingSystem() { return buildingSystem; };
    public UpgradeSystem GetUpgradeSystem() { return upgradeSystem; };
    public CriticalSystem GetCriticalSystem() { return criticalSystem; };
    public int GetTotalBuildings() { return totalBuildings; };
    public double GetGlobalMultiplier() { return globalMultiplier; };
    public int GetTotalClicks() { return totalClicks; };
    public int GetTotalCookies() { return totalCookies; };
    public double GetCriticalChance() { return criticalSystem.GetCriticalChance(); };
    public double GetCriticalPower() { return criticalSystem.GetCriticalPower(); };
    public double GetGoldenChance() { return goldenCookieSystem.GetCookieChance(); };
    public int getGoldenClicks() { return goldenCookieSystem.GetGoldenClicks(); };

    // Methods
    public double cookieClick() {
        boolean isCritical = criticalSystem.tryCritical(Math.random() * 100);
        this.totalClicks++;

        if (isCritical) {
            coins = (globalMultiplier * criticalSystem.applyCritical(coins, clicks, totalCookies));
        } else {
            coins += (globalMultiplier * clicks);
            totalCookies += (globalMultiplier * clicks);
        } 
        return coins;
    }


    public Building getBuilding(String id) {
        return buildingSystem.getUpgrade(id);
    }

    public Upgrade getUpgrade(String id) {
        return upgradeSystem.getUpgrade(id);
    }

    public Boolean canBuyUpgrade(String id) {
        return buildingSystem.canBuy(id, coins);
    }

    public void buyUpgrade(String id) {
        if (buildingSystem.canBuy(id, coins)) {
            coins -= buildingSystem.getUpgrade(id).GetPrice();
            buildingSystem.buy(id);
            this.totalBuildings++; 

            if (buildingSystem.getUpgrade(id).GetQuantity() % 10 == 0) {
                buildingSystem.applySynergy(id);
            }
        }
    }

    public void applyUpgrade(String id) {
        /**
         * Apply an upgrade to an user's account
         */

        // Verify if there are enough coins 
        if (upgradeSystem.canBuy(id, coins)) {
            Upgrade u = upgradeSystem.getUpgrade(id);
            coins -= u.GetPrice();

            switch (u.getTargetID()) {
                case "click":
                    clicks = (int)(clicks * u.GetMultiplier());
                    break;
                case "critChance":
                    criticalSystem.criticalChance += u.GetMultiplier();
                    break;
                case "critPower":
                    criticalSystem.criticalPower = u.GetMultiplier();
                    break;
                default:
                    upgradeSystem.buy(id, u.getTargetID());
                    buildingSystem.applyMultiplier(u.getTargetID(), u.GetMultiplier());
                    break;
            }
        }
    }

    public double updatePassiveIncome() {
        double income = (buildingSystem.totalPassiveIncome() * globalMultiplier);
        coins += income;
        totalCookies += income;
        return income;
    }

    public void goldenCookieChance() {
        if (goldenCookieSystem.activeCookie == null) {
            boolean isThereCookie = Math.random() * 100 < goldenCookieSystem.GetCookieChance() ? true : false;

            if (isThereCookie) {
                GoldenCookie goldenCookie = goldenCookieSystem.goldenCookieList.values().stream().skip((int)(Math.random() * goldenCookieSystem.goldenCookieList.size())).findFirst().orElse(null);
                goldenCookieSystem.activateCookie(goldenCookie);
                goldenCookieSystem.updateGoldenCookies();
            }
        }
    }

    public void verifyGlobalSynergies() {
        for (Synergy synergy : globalSynergies.values()) {
            HashMap<String, Integer> requirement = synergy.GetRequirement();

            if (!synergy.GetIsClaimed() && this.totalBuildings >= requirement.get("global")) {
                synergy.applySynergy(synergy.GetTargetId(), synergy.GetValue());
                synergy.claimSynergy();
            }
        }
    }

    public Synergy getNextGlobalSynergy() {
        for (int i = 1; i < globalSynergies.size(); i++) {
            Synergy s = globalSynergies.get(i);
            if (!s.GetIsClaimed()) {
                return s;
            }
        }
        return null;
    }

    public double getGlobalSynergyProgress() {
        Synergy next = getNextGlobalSynergy();

        if (next == null) {
            return 1.0;
        }

        int required = next.GetRequirement().get("global");
        return Math.min(1.0, (double) totalBuildings / required);
    }

    public SaveData toSaveData() {
        SaveData save = new SaveData();

        /******** CORE ********/
        save.clicks = this.GetClicks();
        save.coins = (int)this.GetCoins();
        save.totalBuildings = this.GetTotalBuildings();
        save.totalClicks = this.GetTotalClicks();
        save.totalCookies = this.GetTotalCookies();
        save.globalMultiplier = this.GetGlobalMultiplier();

        /******** BUILDINGS ********/
        buildingSystem.getAllUpgrades().forEach((id, b) -> {
            save.buildingQuantitites.put(id, b.GetQuantity());
            save.buildingPrices.put(id, b.GetPrice());
        });

        /******** UPGRADES ********/
        upgradeSystem.getAllUpgrades().forEach((id, u) -> {
            save.purchasedUpgrades.put(id, u.isPurchased());
        });

        /******** CRITICAL SYSTEM ********/
        save.crititcalChance = this.criticalSystem.GetCriticalChance();
        save.crititcalPower = this.criticalSystem.GetCriticalPower();

        /******** GOLDEN COOKIE SYSTEM ********/
        save.goldenCookieChance = goldenCookieSystem.GetCookieChance();
        save.goldenCookieClicks = goldenCookieSystem.GetGoldenClicks();

        return save;
    }

    public void loadData(SaveData save) {
        /******** CORE ********/
        this.clicks = save.clicks;
        this.coins = save.coins;
        this.totalBuildings = save.totalBuildings;
        this.totalClicks = save.totalClicks;
        this.totalCookies = save.totalCookies;
        this.globalMultiplier = save.globalMultiplier;

        /******** BUILDINGS ********/
        save.buildingQuantitites.forEach((id, quantity) -> {
            Building b = buildingSystem.getUpgrade(id);
            if (b != null) b.quantity = quantity;
        });

        save.buildingPrices.forEach((id, price) -> {
            Building b = buildingSystem.getUpgrade(id);
            if (b != null) b.price = price;
        });

        /******** UPGRADES ********/
        save.purchasedUpgrades.forEach((id, purchased) -> {
            Upgrade u = upgradeSystem.getUpgrade(id);
            if (u != null ) u.purchased = purchased;
        });

        /******** SYNERGIES ********/
        save.claimedSynergies.forEach((id, claimed) -> {
            int key = Integer.parseInt(id);
            Synergy s = globalSynergies.get(key);
            if (s != null) s.isClaimed = claimed;
        });

        /******** CRITICAL SYSTEM ********/
        criticalSystem.criticalChance = save.crititcalChance;
        criticalSystem.criticalPower = save.crititcalPower;

        /******** GOLDEN COOKIE SYSTEM ********/
        goldenCookieSystem.cookieChance = save.goldenCookieChance;
        goldenCookieSystem.goldenCookieClicks = save.goldenCookieClicks;
    }
}
