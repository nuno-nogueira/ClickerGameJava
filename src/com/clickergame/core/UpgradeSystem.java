package com.clickergame.core;

import java.util.HashMap;
import java.util.Map;

import com.clickergame.systems.Upgrade;

public class UpgradeSystem {
    HashMap<String, Upgrade> upgradesList = new HashMap<String, Upgrade>();

    // Create Upgrades
    public UpgradeSystem() {
        upgradesList.put("click1", new Upgrade("Reinforced Finger", "Each click is now double!", "click", 100, 2.0));
        upgradesList.put("click2", new Upgrade("Titanium Finger", "Even more efficient clicks!", "click", 500, 2.0));
        upgradesList.put("click3", new Upgrade("Diamond Finger", "Even MORE powerful clicks!", "click", 2000, 3.0));
        upgradesList.put("click4", new Upgrade("Quantum Finger", "Clicks so powerful they break reality!", "click", 10000, 5.0));
        
        upgradesList.put("cursor1", new Upgrade("Plastic Mouse", "Double the production, double the fun!", "cursor", 150, 2.0));
        upgradesList.put("cursor2", new Upgrade("Ergonomic Mouse", "Even more efficient cursors!", "cursor", 600, 2.0));
        upgradesList.put("cursor3", new Upgrade("Gaming Mouse", "Professional precision!!", "cursor", 2500, 3.0));
        upgradesList.put("cursor4", new Upgrade("AI Cursor", "Auto-optimized cursors!!!", "cursor", 12000, 5.0));
    
        upgradesList.put("grandma1", new Upgrade("Baking Lessons", "The perfect grandma recipe, but even better!", "grandma", 300, 2.0));
        upgradesList.put("grandma2", new Upgrade("Secret Recipes", "Forbidden recipes...", "grandma", 1200, 2.0));
        upgradesList.put("grandma3", new Upgrade("Industrial Ovens", "To enhance grandma's recipe even more!", "grandma", 5000, 3.0));
        upgradesList.put("grandma4", new Upgrade("Automated Kitchens", "Grandma now uses industrial tech to maximize production!", "grandma", 25000, 5.0));

        upgradesList.put("farm1", new Upgrade("Fertilized Soil", "Productive fields!", "farm", 800, 2.0));
        upgradesList.put("farm2", new Upgrade("GMO Seeds", "A faster growth to our delicious cookies!", "farm", 3000, 2.0));
        upgradesList.put("farm3", new Upgrade("Cookie Irrigation", "Non-stop production! Cookies for everyone!", "farm", 12000, 3.0));
        upgradesList.put("farm4", new Upgrade("Mega Farm", "There isn't a better farm than this, literally!", "farm", 60000, 5.0));

        upgradesList.put("mine1", new Upgrade("Steel Pickaxes", "Eficient mining!", "mine", 2000, 2.0));
        upgradesList.put("mine2", new Upgrade("Explosives", "Aggresive extraction, completely legal...", "mine", 7500, 2.0));
        upgradesList.put("mine3", new Upgrade("Deep Drilling", "It even reaches the earth's core... just kidding!", "mine", 30000, 3.0));
        upgradesList.put("mine4", new Upgrade("Quantum Mining", "Scientists say it will even collapse reality!", "mine", 150000, 5.0));
        
        upgradesList.put("wizard1", new Upgrade("Magic Scrolls", "The wizard has suddently learned new spells!", "wizard", 5000, 2.0));
        upgradesList.put("wizard2", new Upgrade("Mana Crystals", "Amplified magic! WOW!", "wizard", 20000, 2.0));
        upgradesList.put("wizard3", new Upgrade("Arcane Rituals", "Pure arcan power! Mwuahaha!", "wizard", 80000, 3.0));
        upgradesList.put("wizard4", new Upgrade("Time Manipulation", "Outside of Reality!", "wizard", 400000, 5.0));

        upgradesList.put("bank1", new Upgrade("Cookie Loans", "Well, isn't this economy aggresive!", "bank", 15000, 2.0));
        upgradesList.put("bank2", new Upgrade("High Interest", "Obscene judges! Oh my. ", "bank", 60000, 2.0));
        upgradesList.put("bank3", new Upgrade("Cookie Investments", "Cookies now give more profit than money.. a lot more!", "bank", 250000, 3.0));
        upgradesList.put("bank4", new Upgrade("Central Cookie Bank", "Financial Domain!", "bank", 1200000, 5.0));

        upgradesList.put("temple1", new Upgrade("Cookie Prayers", "Sweet, sweet blessings!", "temple", 40000, 2.0));
        upgradesList.put("temple2", new Upgrade("Ancient Relics", "Old relics have recently been found... ancient power!", "temple", 150000, 2.0));
        upgradesList.put("temple3", new Upgrade("Divine Offerings", "Divine production!", "temple", 600000, 3.0));
        upgradesList.put("temple4", new Upgrade("Cookie Deity", "The God of Sugar is now between us!", "temple", 3000000, 5.0));

    }

    // Fetch Upgrade Info!
    public Upgrade getUpgrade(String id) {
        return upgradesList.get(id);
    }

    public Boolean canBuy(String id, int coins) {
        return coins >= upgradesList.get(id).GetPrice();
    }

    public void buy(String id, String target) {
        Upgrade upgrade = upgradesList.get(id);

        if (upgrade != null && !upgrade.isPurchased()) {
            upgrade.markAsPurchased();
        }
    }

    public Map<String, Upgrade> getAllUpgrades() {
        return upgradesList;
    }
}
