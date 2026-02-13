package com.clickergame.core;
import java.util.HashMap;

import com.clickergame.persistence.SaveData;
import com.clickergame.systems.Building;
import com.clickergame.systems.GoldenCookie;
import com.clickergame.systems.Synergy;
import com.clickergame.systems.Upgrade;
import com.clickergame.systems.Prestige;

public class GameState{
    public int clicks;
    public double coins;
    public int totalBuildings;
    public double globalMultiplier;
    public int totalClicks;
    public int totalCookies;
    public double passiveIncome;
    public double passiveIncomeMultiplier;
    public int clickOverload = 200;
    public int clickOverloadCharge = 0;
    public boolean overloadActive = false;
    public int overloadLifespan = 10;
    public long overloadEndTime = 0;

    private BuildingSystem buildingSystem;
    private UpgradeSystem upgradeSystem = new UpgradeSystem();
    private CriticalSystem criticalSystem;
    public PrestigeSystem prestigeSystem;
    private GoldenCookieSystem goldenCookieSystem;
    HashMap<Integer, Synergy> globalSynergies = new HashMap<Integer, Synergy>();

    public GameState() {
        this.criticalSystem = new CriticalSystem();
        this.goldenCookieSystem = new GoldenCookieSystem(this, criticalSystem);
        this.buildingSystem = new BuildingSystem(this, criticalSystem, goldenCookieSystem);
        this.prestigeSystem = new PrestigeSystem(this, criticalSystem, goldenCookieSystem, buildingSystem);

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
    /******** CORE ********/
    public int GetClicks(){ return clicks; };
    public double GetCoins(){ return coins; };

    /******** SYSTEMS ********/
    public BuildingSystem GetbuildingSystem() { return buildingSystem; };
    public UpgradeSystem GetUpgradeSystem() { return upgradeSystem; };
    public CriticalSystem GetCriticalSystem() { return criticalSystem; };

    /******** TOTAL VALUES ********/
    public int GetTotalBuildings() { return totalBuildings; };
    public double GetGlobalMultiplier() { return globalMultiplier; };
    public int GetTotalClicks() { return totalClicks; };
    public int GetTotalCookies() { return totalCookies; };

    /******** CRITICAL SYSTEM ********/
    public double GetCriticalChance() { return criticalSystem.GetCriticalChance(); };
    public double GetCriticalPower() { return criticalSystem.GetCriticalPower(); };
    
    /******** GOLDEN COOKIE ********/
    public double GetGoldenChance() { return goldenCookieSystem.GetCookieChance(); };
    public int getGoldenClicks() { return goldenCookieSystem.GetGoldenClicks(); };

    /******** PASSIVE SYSTEM ********/
    public double getPassiveIncome() { return passiveIncome; };
    public double getPassiveIncomeMultiplier() { return passiveIncomeMultiplier; };

    /******** PRESTIGE SYSTEM ********/
    public int getSugarCrystals() { return prestigeSystem.getCrystals(); };
    public int getTotalPrestiges() { return prestigeSystem.getTotalPrestiges(); };
    public int getPrestigePrice() { return prestigeSystem.getPrice(); };
    public HashMap<Integer, Prestige> getAllPrestiges() { return prestigeSystem.getAllPrestiges(); };

    /******** FETCH SINGLE ELEMENTS ********/
    public Building getBuilding(String id) { return buildingSystem.getUpgrade(id); };
    public Upgrade getUpgrade(String id) { return upgradeSystem.getUpgrade(id); };
    public Prestige getPrestige(Integer id) { return prestigeSystem.getPrestige(id); };
    public Boolean canBuyUpgrade(String id) { return buildingSystem.canBuy(id, coins); };

    /******** CLICK OVERLOAD ********/
    public Integer getClickOverload() { return clickOverload; };
    public Integer getClickOverloadCharge() { return clickOverloadCharge; };

    // CLICK METHODS
    public double cookieClick() {
        boolean isCritical = criticalSystem.tryCritical(Math.random() * 100);
        this.totalClicks++;

        if (isCritical) {
            coins = (globalMultiplier * criticalSystem.applyCritical(coins, clicks, totalCookies));
        } else {
            coins += (globalMultiplier * clicks + (passiveIncome / 4));
            totalCookies += (globalMultiplier * clicks);
        } 

        // Apply multiplier while the click overload is active
        if(overloadActive) {
            coins += (clicks * 5);
            totalCookies += (clicks * 5);
        }
        return coins;
    }

    public void activeClickOverload() {
        // Active click overload
        overloadActive = true;
        overloadEndTime = System.currentTimeMillis() + 10000;
    }

    // BUY ITEMS
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

    public void buyPrestige() {
        if (prestigeSystem.canBuyPrestige(totalCookies)) {
            resetProgress();

            this.prestigeSystem.sugarCrystals++;
            this.prestigeSystem.totalPrestiges++;

            this.prestigeSystem.prestigePrice *= 1.2;
        
            toSaveData();
        }
    }

    public void buyPermamentUpgrade(Integer id, Integer upgradeLevel) {
        if (prestigeSystem.canBuyUpgrade(id, upgradeLevel, this.getSugarCrystals())) {
            this.prestigeSystem.sugarCrystals -= prestigeSystem.getPrestige(id).GetPrice(getPrestige(id).GetUpgradeLevel());
            this.prestigeSystem.buy(id);
            
            if (upgradeLevel != prestigeSystem.getPrestige(id).GetMultiplierList().size()) {
                prestigeSystem.getPrestige(id).upgradeLevel++;
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
                    clicks *= (int)(u.GetMultiplier());
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

            u.markAsPurchased();
        }
    }

    // PASSIVE INCOME
    public double updatePassiveIncome() {
        this.passiveIncome = (buildingSystem.totalPassiveIncome() * globalMultiplier * passiveIncomeMultiplier);
        coins += this.passiveIncome;
        totalCookies += this.passiveIncome;
        return this.passiveIncome;
    }

    // GOLDEN COOKIE
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

    // SYNERGIES
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

    // SAVE SYSTEM
    public SaveData toSaveData() {
        SaveData save = new SaveData();

        /******** CORE ********/
        save.clicks = this.GetClicks();
        save.coins = (int)this.GetCoins();
        save.totalBuildings = this.GetTotalBuildings();
        save.totalClicks = this.GetTotalClicks();
        save.totalCookies = this.GetTotalCookies();
        save.globalMultiplier = this.GetGlobalMultiplier();
        save.passiveIncome = this.getPassiveIncome();
        save.passiveIncomeMultiplier = this.getPassiveIncomeMultiplier();

        /******** BUILDINGS ********/
        buildingSystem.getAllUpgrades().forEach((id, b) -> {
            save.buildingQuantitites.put(id, b.GetQuantity());
            save.buildingPrices.put(id, b.GetPrice());
            save.buildingSynergies.put(id, b.GetSynergyValue());
        });
        

        /******** UPGRADES ********/
        upgradeSystem.getAllUpgrades().forEach((id, u) -> {
            save.purchasedUpgrades.put(id, u.isPurchased());
        });

        /******** CRITICAL SYSTEM ********/
        save.criticalChance = this.criticalSystem.GetCriticalChance();
        save.criticalPower = this.criticalSystem.GetCriticalPower();

        /******** GOLDEN COOKIE SYSTEM ********/
        save.goldenCookieChance = goldenCookieSystem.GetCookieChance();
        save.goldenCookieClicks = goldenCookieSystem.GetGoldenClicks();
        save.lifespanMultiplier = goldenCookieSystem.lifespanMultiplier;

        /******** PRESTIGE SYSTEM ********/
        getAllPrestiges().forEach((id, p) -> {
            save.prestiges.put(id, p.upgradeLevel);
            
        });
        save.sugarCrystals = getSugarCrystals();
        save.totalPrestiges = getTotalPrestiges();
        save.prestigePrice = getPrestigePrice();

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
        this.passiveIncome = save.passiveIncome;
        this.passiveIncomeMultiplier = save.passiveIncomeMultiplier;

        /******** BUILDINGS ********/
        save.buildingQuantitites.forEach((id, quantity) -> {
            Building b = buildingSystem.getUpgrade(id);
            if (b != null) b.quantity = quantity;
        });

        save.buildingPrices.forEach((id, price) -> {
            Building b = buildingSystem.getUpgrade(id);
            if (b != null) b.price = price;
        });

        save.buildingSynergies.forEach((id, value) -> {
            Building b = buildingSystem.getUpgrade(id);
            if (b != null ) b.synergyBuff = value;
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
        criticalSystem.criticalChance = save.criticalChance;
        criticalSystem.criticalPower = save.criticalPower;

        /******** GOLDEN COOKIE SYSTEM ********/
        goldenCookieSystem.cookieChance = save.goldenCookieChance;
        goldenCookieSystem.goldenCookieClicks = save.goldenCookieClicks;
        goldenCookieSystem.lifespanMultiplier = save.lifespanMultiplier;
    
        /******** PRESTIGE SYSTEM ********/
        save.prestiges.forEach((id, upgradeLevel) -> {
            Prestige p = prestigeSystem.getPrestige(id);
            if (p != null ) {
                p.upgradeLevel = upgradeLevel;
            }
        });
        prestigeSystem.totalPrestiges = save.totalPrestiges;
        prestigeSystem.prestigePrice = save.prestigePrice;
        prestigeSystem.sugarCrystals = save.sugarCrystals;
    }

    public void resetProgress() {
        // Reset stats
        this.coins = 0.0d;
        this.totalBuildings = 0;
        this.totalClicks = 0;
        this.totalCookies = 0;
        this.passiveIncome = 0;

        // Reset buildings purchases
        buildingSystem.getAllUpgrades().forEach((id, b) -> {
            // Reverse the synergy & building buffs
            if (b.GetQuantity() >= 10) {
                switch (b.GetSynergyTarget()) {
                case "global":
                    globalMultiplier -= (b.GetSynergyValue() / 100);
                    b.synergyBuff -= (b.GetSynergyValue() / 100);
                    break;
                case "critChance":
                    criticalSystem.criticalChance -= b.GetSynergyValue();
                    b.synergyBuff -= b.GetSynergyValue();
                    break;
                case "critPower":
                    criticalSystem.criticalPower -= b.GetSynergyValue();
                    b.synergyBuff -= b.GetSynergyValue();    
                    break;
                case "goldenCookie":
                    goldenCookieSystem.cookieChance -= b.GetSynergyValue();
                    b.synergyBuff -= b.GetSynergyValue();
                    break;
                default:
                    break;
                }
            }

            if (b.GetQuantity() > 0) {
                b.price = b.basePrice;
                b.quantity = 0;
                b.multiplier = 1.0d;
            }
            
        });

        // Reset upgrade purchases
        upgradeSystem.getAllUpgrades().forEach((id, u) -> {
            // Reverse the upgrade buffs
            if (u.purchased) {
                System.out.println("test");
                switch (u.getTargetID()) {
                case "click":
                    clicks /= u.GetMultiplier();
                    break;
                case "critChance":
                    criticalSystem.criticalChance -= u.GetMultiplier();
                    break;
                case "critPower":
                    criticalSystem.criticalPower -= u.GetMultiplier();
                    break;
                default:
                    break;
                }
            }
            u.purchased = false;
            
        });
    }
}
