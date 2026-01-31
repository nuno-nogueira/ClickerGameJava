package com.clickergame.core; 

public  class GameState{
    private int clicks = 1;
    private int cursors = 0;
    private int coins = 0;
    private int upgrades = 10;
    private int cursorUpgrades = 40;

    public GameState() {
        this.clicks = clicks;
        this.cursors = cursors;
        this.coins = coins;
        this.upgrades = upgrades;
        this.cursorUpgrades = cursorUpgrades;
    }

    // Getters & Setters
    public int GetClicks(){ return clicks; };
    public void SetClicks(int clicks) { this.clicks = clicks; };

    public int GetCursors(){ return cursors; };
    public void SetCursors(int cursors) { this.cursors = cursors; };

    public int GetCoins(){ return coins; };
    public void SetCoins(int coins) { this.coins = coins; };

    public int GetUpgrades(){ return upgrades; };
    public void SetUpgrades(int upgrades) { this.upgrades = upgrades; };

    public int GetCursorUpgrades(){ return cursorUpgrades; };
    public void SetCursorUpgrades(int cursorUpgrades) { this.cursorUpgrades = cursorUpgrades; };

    public int cookieClick() {
        coins += clicks;
        return coins;
    }

    public int cursorClick() {
        coins += cursors;
        return coins;
    }

    public void upgradeClicks() {
        if (coins >= upgrades) {
            // Buy an upgrade
            coins -= upgrades;
            clicks += 1;

            System.out.println("Click Upgrade bought for " + upgrades + "! :o");

            // Increase the upgrade price everytime it's used
            upgrades *= 3;
        } else {
            System.out.println("You don't have enough coins for the next upgrade!");
        }
    }

    public void upgradeCursors() {
        if (coins >= cursorUpgrades) {
            // Buy a cursor
            coins -= cursorUpgrades;
            cursors += 1;

            System.out.println("Cursor bought for " + cursorUpgrades + "! :o");

            cursorUpgrades *= 2;
        } else {
            System.out.println("You don't have enough coins for the next upgrade!");
        }
    }
}