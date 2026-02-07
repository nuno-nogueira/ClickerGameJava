package com.clickergame.ui;

import java.util.HashMap;
import java.util.Map;

import com.clickergame.core.GameState;
import com.clickergame.systems.Upgrade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class UpgradesController {
    @FXML private Button clickButton1;
    @FXML private Button clickButton2;
    @FXML private Button clickButton3;
    @FXML private Button clickButton4;
    @FXML private Button cursorButton1;
    @FXML private Button cursorButton2;
    @FXML private Button cursorButton3;
    @FXML private Button cursorButton4;
    @FXML private Button grandmaButton1;
    @FXML private Button grandmaButton2;
    @FXML private Button grandmaButton3;
    @FXML private Button grandmaButton4;
    @FXML private Button farmButton1;
    @FXML private Button farmButton2;
    @FXML private Button farmButton3;
    @FXML private Button farmButton4;
    @FXML private Button mineButton1;
    @FXML private Button mineButton2;
    @FXML private Button mineButton3;
    @FXML private Button mineButton4;
    @FXML private Button wizardButton1;
    @FXML private Button wizardButton2;
    @FXML private Button wizardButton3;
    @FXML private Button wizardButton4;
    @FXML private Button bankButton1;
    @FXML private Button bankButton2;
    @FXML private Button bankButton3;
    @FXML private Button bankButton4;
    @FXML private Button templeButton1;
    @FXML private Button templeButton2;
    @FXML private Button templeButton3;
    @FXML private Button templeButton4;
    @FXML private Button critChanceButton1;
    @FXML private Button critChanceButton2;
    @FXML private Button critChanceButton3;
    @FXML private Button critChanceButton4;
    @FXML private Button critPowerButton1;
    @FXML private Button critPowerButton2;
    @FXML private Button critPowerButton3;
    @FXML private Button critPowerButton4;


    @FXML private Label clickName1;
    @FXML private Label clickName2;
    @FXML private Label clickName3;
    @FXML private Label clickName4;
    @FXML private Label cursorName1;
    @FXML private Label cursorName2;
    @FXML private Label cursorName3;
    @FXML private Label cursorName4;
    @FXML private Label grandmaName1;
    @FXML private Label grandmaName2;
    @FXML private Label grandmaName3;
    @FXML private Label grandmaName4;
    @FXML private Label farmName1;
    @FXML private Label farmName2;
    @FXML private Label farmName3;
    @FXML private Label farmName4;
    @FXML private Label mineName1;
    @FXML private Label mineName2;
    @FXML private Label mineName3;
    @FXML private Label mineName4;
    @FXML private Label wizardName1;
    @FXML private Label wizardName2;
    @FXML private Label wizardName3;
    @FXML private Label wizardName4;
    @FXML private Label bankName1;
    @FXML private Label bankName2;
    @FXML private Label bankName3;
    @FXML private Label bankName4;
    @FXML private Label templeName1;
    @FXML private Label templeName2;
    @FXML private Label templeName3;
    @FXML private Label templeName4;
    @FXML private Label critChanceName1;
    @FXML private Label critChanceName2;
    @FXML private Label critChanceName3;
    @FXML private Label critChanceName4;
    @FXML private Label critPowerName1;
    @FXML private Label critPowerName2;
    @FXML private Label critPowerName3;
    @FXML private Label critPowerName4;


    @FXML private Label clickDesc1;
    @FXML private Label clickDesc2;
    @FXML private Label clickDesc3;
    @FXML private Label clickDesc4;
    @FXML private Label cursorDesc1;
    @FXML private Label cursorDesc2;
    @FXML private Label cursorDesc3;
    @FXML private Label cursorDesc4;
    @FXML private Label grandmaDesc1;
    @FXML private Label grandmaDesc2;
    @FXML private Label grandmaDesc3;
    @FXML private Label grandmaDesc4;
    @FXML private Label farmDesc1;
    @FXML private Label farmDesc2;
    @FXML private Label farmDesc3;
    @FXML private Label farmDesc4;
    @FXML private Label mineDesc1;
    @FXML private Label mineDesc2;
    @FXML private Label mineDesc3;
    @FXML private Label mineDesc4;
    @FXML private Label wizardDesc1;
    @FXML private Label wizardDesc2;
    @FXML private Label wizardDesc3;
    @FXML private Label wizardDesc4;
    @FXML private Label bankDesc1;
    @FXML private Label bankDesc2;
    @FXML private Label bankDesc3;
    @FXML private Label bankDesc4;
    @FXML private Label templeDesc1;
    @FXML private Label templeDesc2;
    @FXML private Label templeDesc3;
    @FXML private Label templeDesc4;
    @FXML private Label critChanceDesc1;
    @FXML private Label critChanceDesc2;
    @FXML private Label critChanceDesc3;
    @FXML private Label critChanceDesc4;
    @FXML private Label critPowerDesc1;
    @FXML private Label critPowerDesc2;
    @FXML private Label critPowerDesc3;
    @FXML private Label critPowerDesc4;


    @FXML private Label clickPrice1;
    @FXML private Label clickPrice2;
    @FXML private Label clickPrice3;
    @FXML private Label clickPrice4;
    @FXML private Label cursorPrice1;
    @FXML private Label cursorPrice2;
    @FXML private Label cursorPrice3;
    @FXML private Label cursorPrice4;
    @FXML private Label grandmaPrice1;
    @FXML private Label grandmaPrice2;
    @FXML private Label grandmaPrice3;
    @FXML private Label grandmaPrice4;
    @FXML private Label farmPrice1;
    @FXML private Label farmPrice2;
    @FXML private Label farmPrice3;
    @FXML private Label farmPrice4;
    @FXML private Label minePrice1;
    @FXML private Label minePrice2;
    @FXML private Label minePrice3;
    @FXML private Label minePrice4;
    @FXML private Label wizardPrice1;
    @FXML private Label wizardPrice2;
    @FXML private Label wizardPrice3;
    @FXML private Label wizardPrice4;
    @FXML private Label bankPrice1;
    @FXML private Label bankPrice2;
    @FXML private Label bankPrice3;
    @FXML private Label bankPrice4;
    @FXML private Label templePrice1;
    @FXML private Label templePrice2;
    @FXML private Label templePrice3;
    @FXML private Label templePrice4;
    @FXML private Label critChancePrice1;
    @FXML private Label critChancePrice2;
    @FXML private Label critChancePrice3;
    @FXML private Label critChancePrice4;
    @FXML private Label critPowerPrice1;
    @FXML private Label critPowerPrice2;
    @FXML private Label critPowerPrice3;
    @FXML private Label critPowerPrice4;

    @FXML private Label clickMultiplier1;
    @FXML private Label clickMultiplier2;
    @FXML private Label clickMultiplier3;
    @FXML private Label clickMultiplier4;
    @FXML private Label cursorMultiplier1;
    @FXML private Label cursorMultiplier2;
    @FXML private Label cursorMultiplier3;
    @FXML private Label cursorMultiplier4;
    @FXML private Label grandmaMultiplier1;
    @FXML private Label grandmaMultiplier2;
    @FXML private Label grandmaMultiplier3;
    @FXML private Label grandmaMultiplier4;
    @FXML private Label farmMultiplier1;
    @FXML private Label farmMultiplier2;
    @FXML private Label farmMultiplier3;
    @FXML private Label farmMultiplier4;
    @FXML private Label mineMultiplier1;
    @FXML private Label mineMultiplier2;
    @FXML private Label mineMultiplier3;
    @FXML private Label mineMultiplier4;
    @FXML private Label wizardMultiplier1;
    @FXML private Label wizardMultiplier2;
    @FXML private Label wizardMultiplier3;
    @FXML private Label wizardMultiplier4;
    @FXML private Label bankMultiplier1;
    @FXML private Label bankMultiplier2;
    @FXML private Label bankMultiplier3;
    @FXML private Label bankMultiplier4;
    @FXML private Label templeMultiplier1;
    @FXML private Label templeMultiplier2;
    @FXML private Label templeMultiplier3;
    @FXML private Label templeMultiplier4;
    @FXML private Label critChanceMultiplier1;
    @FXML private Label critChanceMultiplier2;
    @FXML private Label critChanceMultiplier3;
    @FXML private Label critChanceMultiplier4;
    @FXML private Label critPowerMultiplier1;
    @FXML private Label critPowerMultiplier2;
    @FXML private Label critPowerMultiplier3;
    @FXML private Label critPowerMultiplier4;

    @FXML private TilePane clickPane1;
    @FXML private TilePane clickPane2;
    @FXML private TilePane clickPane3;
    @FXML private TilePane clickPane4;
    @FXML private TilePane cursorPane1;
    @FXML private TilePane cursorPane2;
    @FXML private TilePane cursorPane3;
    @FXML private TilePane cursorPane4;
    @FXML private TilePane grandmaPane1;
    @FXML private TilePane grandmaPane2;
    @FXML private TilePane grandmaPane3;
    @FXML private TilePane grandmaPane4;
    @FXML private TilePane farmPane1;
    @FXML private TilePane farmPane2;
    @FXML private TilePane farmPane3;
    @FXML private TilePane farmPane4;
    @FXML private TilePane minePane1;
    @FXML private TilePane minePane2;
    @FXML private TilePane minePane3;
    @FXML private TilePane minePane4;
    @FXML private TilePane wizardPane1;
    @FXML private TilePane wizardPane2;
    @FXML private TilePane wizardPane3;
    @FXML private TilePane wizardPane4;
    @FXML private TilePane bankPane1;
    @FXML private TilePane bankPane2;
    @FXML private TilePane bankPane3;
    @FXML private TilePane bankPane4;
    @FXML private TilePane templePane1;
    @FXML private TilePane templePane2;
    @FXML private TilePane templePane3;
    @FXML private TilePane templePane4;
    @FXML private TilePane critChancePane1;
    @FXML private TilePane critChancePane2;
    @FXML private TilePane critChancePane3;
    @FXML private TilePane critChancePane4;
    @FXML private TilePane critPowerPane1;
    @FXML private TilePane critPowerPane2;
    @FXML private TilePane critPowerPane3;
    @FXML private TilePane critPowerPane4;


    // Map to associate buttons with upgrade IDs
    public Map<Button, String> upgradeButtonMap = new HashMap<>();
    private Map<String, Label> upgradeNameMap = new HashMap<>();
    private Map<String, Label> upgradePriceMap = new HashMap<>();
    private Map<String, Label> upgradeDescMap = new HashMap<>();
    private Map<String, Label> upgradeMultiplierMap = new HashMap<>();
    public Map<String, TilePane> upgradePaneMap = new HashMap<>();

    private GameState gamestate;

    @FXML 
    public void initialize(GameState gamestate) {
        this.gamestate = gamestate;
        addButtons();
        addNames();
        addDescriptions();
        addPrices();
        addMultiplier();
        addTiles();

        // Loop over each upgrade and set texts + actions
        for (Map.Entry<Button, String> entry : upgradeButtonMap.entrySet()) {
            Button button = entry.getKey();
            String upgradeId = entry.getValue();
            Upgrade u = gamestate.getUpgrade(upgradeId);

            Label priceLabel = upgradePriceMap.get(upgradeId);
            Label nameLabel = upgradeNameMap.get(upgradeId);
            Label descLabel = upgradeDescMap.get(upgradeId);
            Label multiplierLabel = upgradeMultiplierMap.get(upgradeId);

            // Set UI texts
            priceLabel.setText(String.valueOf(u.GetPrice()));
            nameLabel.setText(u.getName());
            descLabel.setText(u.getDescription());
            switch (u.getTargetID()) {
                case "click":
                    multiplierLabel.setText(String.valueOf((int)u.GetMultiplier()) + "x Clicks");
                    break;
                case "critChance":
                    multiplierLabel.setText("+" + String.valueOf((int)u.GetMultiplier()) + "% Critical Chance");
                    break;
                case "critPower":
                    multiplierLabel.setText(String.valueOf((int)u.GetMultiplier()) + "x Critical Power");
                    break;
                default:
                    multiplierLabel.setText(String.valueOf((int)u.GetMultiplier()) + "x Production");
                    break;
            }

            // Set action
            button.setOnAction(e -> {
                buyUpgrade(upgradeId, button);
            });
            updateButtons(upgradeId, button);
        }
    }

    private void buyUpgrade(String id, Button button) {
        gamestate.applyUpgrade(id);
        upgradePaneMap.get(id).setVisible(false);
    }

    private void updateButtons(String id, Button button) {
        Upgrade upgrade = gamestate.getUpgrade(id);

        if (gamestate.GetCoins() >= upgrade.GetPrice()) {
            button.setDisable(false);
            button.setStyle("");
        } else {
            button.setDisable(true);
            button.setStyle("-fx-text-fill: red;");
        }
    }

    // ---- Helper methods to register UI elements in maps ----
    private void addButtons() {
        upgradeButtonMap.put(clickButton1, "click1");
        upgradeButtonMap.put(cursorButton1, "cursor1");
        upgradeButtonMap.put(critChanceButton1, "critChance1");
        upgradeButtonMap.put(clickButton2, "click2");
        upgradeButtonMap.put(cursorButton2, "cursor2");
        upgradeButtonMap.put(critChanceButton2, "critChance2");

        upgradeButtonMap.put(grandmaButton1, "grandma1");
        upgradeButtonMap.put(cursorButton3, "cursor3");
        upgradeButtonMap.put(clickButton3, "click3");
        upgradeButtonMap.put(grandmaButton2, "grandma2");
        upgradeButtonMap.put(critPowerButton1, "critPower1");
        upgradeButtonMap.put(critChanceButton3, "critChance3");

        upgradeButtonMap.put(farmButton1, "farm1");
        upgradeButtonMap.put(cursorButton4, "cursor4");
        upgradeButtonMap.put(critChanceButton4, "critChance4");
        upgradeButtonMap.put(farmButton2, "farm2");
        upgradeButtonMap.put(grandmaButton3, "grandma3");
        upgradeButtonMap.put(critPowerButton2, "critPower2");

        upgradeButtonMap.put(mineButton1, "mine1");
        upgradeButtonMap.put(farmButton3, "farm3");
        upgradeButtonMap.put(mineButton2, "mine2");
        upgradeButtonMap.put(clickButton4, "click4");

        upgradeButtonMap.put(wizardButton1, "wizard1");
        upgradeButtonMap.put(grandmaButton4, "grandma4");
        upgradeButtonMap.put(wizardButton2, "wizard2");
        upgradeButtonMap.put(mineButton3, "mine3");

        upgradeButtonMap.put(bankButton1, "bank1");
        upgradeButtonMap.put(farmButton4, "farm4");
        upgradeButtonMap.put(wizardButton3, "wizard3");
        upgradeButtonMap.put(critPowerButton3, "critPower3");
        upgradeButtonMap.put(bankButton2, "bank2");

        upgradeButtonMap.put(templeButton1, "temple1");
        upgradeButtonMap.put(mineButton4, "mine4");
        upgradeButtonMap.put(bankButton3, "bank3");
        upgradeButtonMap.put(templeButton2, "temple2");

        upgradeButtonMap.put(wizardButton4, "wizard4");
        upgradeButtonMap.put(critPowerButton4, "critPower4");
        upgradeButtonMap.put(bankButton4, "bank4");
        upgradeButtonMap.put(templeButton3, "temple3");
        upgradeButtonMap.put(templeButton4, "temple4");
    }

    private void addNames() {
        upgradeNameMap.put("click1", clickName1);
        upgradeNameMap.put("cursor1", cursorName1);
        upgradeNameMap.put("critChance1", critChanceName1);
        upgradeNameMap.put("click2", clickName2);
        upgradeNameMap.put("cursor2", cursorName2);
        upgradeNameMap.put("critChance2", critChanceName2);

        upgradeNameMap.put("grandma1", grandmaName1);
        upgradeNameMap.put("cursor3", cursorName3);
        upgradeNameMap.put("click3", clickName3);
        upgradeNameMap.put("grandma2", grandmaName2);
        upgradeNameMap.put("critPower1", critPowerName1);
        upgradeNameMap.put("critChance3", critChanceName3);

        upgradeNameMap.put("farm1", farmName1);
        upgradeNameMap.put("cursor4", cursorName4);
        upgradeNameMap.put("critChance4", critChanceName4);
        upgradeNameMap.put("farm2", farmName2);
        upgradeNameMap.put("grandma3", grandmaName3);
        upgradeNameMap.put("critPower2", critPowerName2);


        upgradeNameMap.put("mine1", mineName1);
        upgradeNameMap.put("farm3", farmName3);
        upgradeNameMap.put("mine2", mineName2);
        upgradeNameMap.put("click4", clickName4);

        upgradeNameMap.put("wizard1", wizardName1);
        upgradeNameMap.put("grandma4", grandmaName4);
        upgradeNameMap.put("wizard2", wizardName2);
        upgradeNameMap.put("mine3", mineName3);

        upgradeNameMap.put("bank1", bankName1);
        upgradeNameMap.put("farm4", farmName4);
        upgradeNameMap.put("wizard3", wizardName3);
        upgradeNameMap.put("critPower3", critPowerName3);
        upgradeNameMap.put("bank2", bankName2);

        upgradeNameMap.put("temple1", templeName1);
        upgradeNameMap.put("mine4", mineName4);
        upgradeNameMap.put("bank3", bankName3);
        upgradeNameMap.put("temple2", templeName2);

        upgradeNameMap.put("wizard4", wizardName4);
        upgradeNameMap.put("critPower4", critPowerName4);
        upgradeNameMap.put("bank4", bankName4);
        upgradeNameMap.put("temple3", templeName3);
        upgradeNameMap.put("temple4", templeName4);
    }

    private void addDescriptions() {
        upgradeDescMap.put("click1", clickDesc1);
        upgradeDescMap.put("cursor1", cursorDesc1);
        upgradeDescMap.put("critChance1", critChanceDesc1);
        upgradeDescMap.put("click2", clickDesc2);
        upgradeDescMap.put("cursor2", cursorDesc2);
        upgradeDescMap.put("critChance2", critChanceDesc2);

        upgradeDescMap.put("grandma1", grandmaDesc1);
        upgradeDescMap.put("cursor3", cursorDesc3);
        upgradeDescMap.put("click3", clickDesc3);
        upgradeDescMap.put("grandma2", grandmaDesc2);
        upgradeDescMap.put("critPower1", critPowerDesc1);
        upgradeDescMap.put("critChance3", critChanceDesc3);

        upgradeDescMap.put("farm1", farmDesc1);
        upgradeDescMap.put("cursor4", cursorDesc4);
        upgradeDescMap.put("critChance4", critChanceDesc4);
        upgradeDescMap.put("farm2", farmDesc2);
        upgradeDescMap.put("grandma3", grandmaDesc3);
        upgradeDescMap.put("critPower2", critPowerDesc2);

        upgradeDescMap.put("mine1", mineDesc1);
        upgradeDescMap.put("farm3", farmDesc3);
        upgradeDescMap.put("mine2", mineDesc2);
        upgradeDescMap.put("click4", clickDesc4);

        upgradeDescMap.put("wizard1", wizardDesc1);
        upgradeDescMap.put("grandma4", grandmaDesc4);
        upgradeDescMap.put("wizard2", wizardDesc2);
        upgradeDescMap.put("mine3", mineDesc3);

        upgradeDescMap.put("bank1", bankDesc1);
        upgradeDescMap.put("farm4", farmDesc4);
        upgradeDescMap.put("wizard3", wizardDesc3);
        upgradeDescMap.put("critPower3", critPowerDesc3);
        upgradeDescMap.put("bank2", bankDesc2);

        upgradeDescMap.put("temple1", templeDesc1);
        upgradeDescMap.put("mine4", mineDesc4);
        upgradeDescMap.put("bank3", bankDesc3);
        upgradeDescMap.put("temple2", templeDesc2);

        upgradeDescMap.put("wizard4", wizardDesc4);
        upgradeDescMap.put("critPower4", critPowerDesc4);
        upgradeDescMap.put("bank4", bankDesc4);
        upgradeDescMap.put("temple3", templeDesc3);
        upgradeDescMap.put("temple4", templeDesc4);
    }

    private void addPrices() {
        upgradePriceMap.put("click1", clickPrice1);
        upgradePriceMap.put("cursor1", cursorPrice1);
        upgradePriceMap.put("critChance1", critChancePrice1);
        upgradePriceMap.put("click2", clickPrice2);
        upgradePriceMap.put("cursor2", cursorPrice2);
        upgradePriceMap.put("critChance2", critChancePrice2);

        upgradePriceMap.put("grandma1", grandmaPrice1);
        upgradePriceMap.put("cursor3", cursorPrice3);
        upgradePriceMap.put("click3", clickPrice3);
        upgradePriceMap.put("grandma2", grandmaPrice2);
        upgradePriceMap.put("critPower1", critPowerPrice1);
        upgradePriceMap.put("critChance3", critChancePrice3);

        upgradePriceMap.put("farm1", farmPrice1);
        upgradePriceMap.put("cursor4", cursorPrice4);
        upgradePriceMap.put("critChance4", critChancePrice4);
        upgradePriceMap.put("farm2", farmPrice2);
        upgradePriceMap.put("grandma3", grandmaPrice3);
        upgradePriceMap.put("critPower2", critPowerPrice2);

        upgradePriceMap.put("mine1", minePrice1);
        upgradePriceMap.put("farm3", farmPrice3);
        upgradePriceMap.put("mine2", minePrice2);
        upgradePriceMap.put("click4", clickPrice4);

        upgradePriceMap.put("wizard1", wizardPrice1);
        upgradePriceMap.put("grandma4", grandmaPrice4);
        upgradePriceMap.put("wizard2", wizardPrice2);
        upgradePriceMap.put("mine3", minePrice3);

        upgradePriceMap.put("bank1", bankPrice1);
        upgradePriceMap.put("farm4", farmPrice4);
        upgradePriceMap.put("wizard3", wizardPrice3);
        upgradePriceMap.put("critPower3", critPowerPrice3);
        upgradePriceMap.put("bank2", bankPrice2);

        upgradePriceMap.put("temple1", templePrice1);
        upgradePriceMap.put("mine4", minePrice4);
        upgradePriceMap.put("bank3", bankPrice3);
        upgradePriceMap.put("temple2", templePrice2);

        upgradePriceMap.put("wizard4", wizardPrice4);
        upgradePriceMap.put("critPower4", critPowerPrice4);
        upgradePriceMap.put("bank4", bankPrice4);
        upgradePriceMap.put("temple3", templePrice3);
        upgradePriceMap.put("temple4", templePrice4);
    }

    private void addMultiplier() {
        upgradeMultiplierMap.put("click1", clickMultiplier1);
        upgradeMultiplierMap.put("cursor1", cursorMultiplier1);
        upgradeMultiplierMap.put("critChance1", critChanceMultiplier1);
        upgradeMultiplierMap.put("click2", clickMultiplier2);
        upgradeMultiplierMap.put("cursor2", cursorMultiplier2);
        upgradeMultiplierMap.put("critChance2", critChanceMultiplier2);

        upgradeMultiplierMap.put("grandma1", grandmaMultiplier1);
        upgradeMultiplierMap.put("cursor3", cursorMultiplier3);
        upgradeMultiplierMap.put("click3", clickMultiplier3);
        upgradeMultiplierMap.put("grandma2", grandmaMultiplier2);
        upgradeMultiplierMap.put("critPower1", critPowerMultiplier1);
        upgradeMultiplierMap.put("critChance3", critChanceMultiplier3);

        upgradeMultiplierMap.put("farm1", farmMultiplier1);
        upgradeMultiplierMap.put("cursor4", cursorMultiplier4);
        upgradeMultiplierMap.put("critChance4", critChanceMultiplier4);
        upgradeMultiplierMap.put("farm2", farmMultiplier2);
        upgradeMultiplierMap.put("grandma3", grandmaMultiplier3);
        upgradeMultiplierMap.put("critPower2", critPowerMultiplier2);

        upgradeMultiplierMap.put("mine1", mineMultiplier1);
        upgradeMultiplierMap.put("farm3", farmMultiplier3);
        upgradeMultiplierMap.put("mine2", mineMultiplier2);
        upgradeMultiplierMap.put("click4", clickMultiplier4);

        upgradeMultiplierMap.put("wizard1", wizardMultiplier1);
        upgradeMultiplierMap.put("grandma4", grandmaMultiplier4);
        upgradeMultiplierMap.put("wizard2", wizardMultiplier2);
        upgradeMultiplierMap.put("mine3", mineMultiplier3);

        upgradeMultiplierMap.put("bank1", bankMultiplier1);
        upgradeMultiplierMap.put("farm4", farmMultiplier4);
        upgradeMultiplierMap.put("wizard3", wizardMultiplier3);
        upgradeMultiplierMap.put("critPower3", critPowerMultiplier3);
        upgradeMultiplierMap.put("bank2", bankMultiplier2);

        upgradeMultiplierMap.put("temple1", templeMultiplier1);
        upgradeMultiplierMap.put("mine4", mineMultiplier4);
        upgradeMultiplierMap.put("bank3", bankMultiplier3);
        upgradeMultiplierMap.put("temple2", templeMultiplier2);

        upgradeMultiplierMap.put("wizard4", wizardMultiplier4);
        upgradeMultiplierMap.put("critPower4", critPowerMultiplier4);
        upgradeMultiplierMap.put("bank4", bankMultiplier4);
        upgradeMultiplierMap.put("temple3", templeMultiplier3);
        upgradeMultiplierMap.put("temple4", templeMultiplier4);
    }

    private void addTiles() {
        upgradePaneMap.put("click1", clickPane1);
        upgradePaneMap.put("cursor1", cursorPane1);
        upgradePaneMap.put("critChance1", critChancePane1);
        upgradePaneMap.put("click2", clickPane2);
        upgradePaneMap.put("cursor2", cursorPane2);
        upgradePaneMap.put("critChance2", critChancePane2);

        upgradePaneMap.put("grandma1", grandmaPane1);
        upgradePaneMap.put("cursor3", cursorPane3);
        upgradePaneMap.put("click3", clickPane3);
        upgradePaneMap.put("grandma2", grandmaPane2);
        upgradePaneMap.put("critPower1", critPowerPane1);
        upgradePaneMap.put("critChance3", critChancePane3);

        upgradePaneMap.put("farm1", farmPane1);
        upgradePaneMap.put("cursor4", cursorPane4);
        upgradePaneMap.put("critChance4", critChancePane4);
        upgradePaneMap.put("farm2", farmPane2);
        upgradePaneMap.put("grandma3", grandmaPane3);
        upgradePaneMap.put("critPower2", critPowerPane2);

        upgradePaneMap.put("mine1", minePane1);
        upgradePaneMap.put("farm3", farmPane3);
        upgradePaneMap.put("mine2", minePane2);
        upgradePaneMap.put("click4", clickPane4);

        upgradePaneMap.put("wizard1", wizardPane1);
        upgradePaneMap.put("grandma4", grandmaPane4);
        upgradePaneMap.put("wizard2", wizardPane2);
        upgradePaneMap.put("mine3", minePane3);

        upgradePaneMap.put("bank1", bankPane1);
        upgradePaneMap.put("farm4", farmPane4);
        upgradePaneMap.put("wizard3", wizardPane3);
        upgradePaneMap.put("critPower3", critPowerPane3);
        upgradePaneMap.put("bank2", bankPane2);

        upgradePaneMap.put("temple1", templePane1);
        upgradePaneMap.put("mine4", minePane4);
        upgradePaneMap.put("bank3", bankPane3);
        upgradePaneMap.put("temple2", templePane2);

        upgradePaneMap.put("wizard4", wizardPane4);
        upgradePaneMap.put("critPower4", critPowerPane4);
        upgradePaneMap.put("bank4", bankPane4);
        upgradePaneMap.put("temple3", templePane3);
        upgradePaneMap.put("temple4", templePane4);
    }

    public void refreshAllButtons() {
        for (Map.Entry<Button, String> entry : upgradeButtonMap.entrySet()) { 
            updateButtons(entry.getValue(), entry.getKey());
        }
    }
}
