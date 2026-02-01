package com.clickergame.core; 

public  class GameState{
    private int clicks = 1;
    private int coins = 0;
    private BuildingSystem upgradeSystem = new BuildingSystem();

    // Getters
    public int GetClicks(){ return clicks; };
    public int GetCoins(){ return coins; };
    public BuildingSystem GetUpgradeSystem() { return upgradeSystem; };

    // Methods
    public int cookieClick() {
        coins += clicks;
        return coins;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public Building getUpgrade(String id) {
        return upgradeSystem.getUpgrade(id);
    }

    public Boolean canBuyUpgrade(String id) {
        return upgradeSystem.canBuy(id, coins);
    }

    public void buyUpgrade(String id) {
        if (upgradeSystem.canBuy(id, coins)) {
            coins -= upgradeSystem.getUpgrade(id).GetPrice();
            upgradeSystem.buy(id);
        }
    }

    public int updatePassiveIncome() {
        int income = upgradeSystem.totalPassiveIncome();
        coins += income;
        return income;
    }

}