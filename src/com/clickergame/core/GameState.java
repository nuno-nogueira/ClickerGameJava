package com.clickergame.core;

import com.clickergame.systems.Building;
import com.clickergame.systems.Upgrade;

public  class GameState{
    private int clicks = 1;
    private double coins = 10000000d;
    private BuildingSystem buildingSystem = new BuildingSystem();
    private UpgradeSystem upgradeSystem = new UpgradeSystem();
    private CriticalSystem criticalSystem = new CriticalSystem();

    // Getters
    public int GetClicks(){ return clicks; };
    public double GetCoins(){ return coins; };
    public BuildingSystem GetbuildingSystem() { return buildingSystem; };
    public UpgradeSystem GetUpgradeSystem() { return upgradeSystem; };
    public CriticalSystem GetCriticalSystem() { return criticalSystem; };

    // Methods
    public double cookieClick() {
        boolean isCritical = criticalSystem.tryCritical(Math.random() * 100);

        if (isCritical) {
            coins = criticalSystem.applyCritical(coins, clicks);
        } else {
            coins += clicks;
        } 
        return coins;
    }

    public void addCoins(int amount) {
        coins += amount;
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

            System.out.println("test1");
            switch (u.getTargetID()) {
                case "click":
                    clicks = (int)(clicks * u.GetMultiplier());
                    break;
                case "critChance":
                    criticalSystem.criticalChance += u.GetMultiplier();
                    System.out.println("Critical Chance: " + criticalSystem.criticalChance);
                    break;
                case "critPower":
                    criticalSystem.criticalPower = u.GetMultiplier();
                    System.out.println("Critical Power: " + criticalSystem.criticalPower);
                    break;
                default:
                    upgradeSystem.buy(id, u.getTargetID());
                    buildingSystem.applyMultiplier(u.getTargetID(), u.GetMultiplier());
                    break;
            }

            // if (u.getTargetID() == "click") {
            //     // Automatically multiply the click production
            //     clicks = (int)(clicks * u.GetMultiplier());
            // } else if (u.getTargetID() == "critChance") {

            // } 
            // else {
            //     upgradeSystem.buy(id, u.getTargetID());
            //     buildingSystem.applyMultiplier(u.getTargetID(), u.GetMultiplier());
            // }
        }
    }

    public double updatePassiveIncome() {
        double income = buildingSystem.totalPassiveIncome();
        coins += income;
        return income;
    }

}