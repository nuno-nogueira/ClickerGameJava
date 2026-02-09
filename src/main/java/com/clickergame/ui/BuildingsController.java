package com.clickergame.ui;

import java.util.HashMap;
import java.util.Map;

import com.clickergame.core.GameState;
import com.clickergame.systems.Building;
import com.clickergame.systems.Synergy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ProgressBar;

public class BuildingsController {
    @FXML private Button clickButton;
    @FXML private Button cursorButton;
    @FXML private Button grandmaButton;
    @FXML private Button farmButton;
    @FXML private Button mineButton;
    @FXML private Button wizardButton;
    @FXML private Button bankButton;
    @FXML private Button templeButton;

    @FXML private Label cursorLabel;
    @FXML private Label grandmaLabel;
    @FXML private Label farmLabel;
    @FXML private Label mineLabel;
    @FXML private Label wizardLabel;
    @FXML private Label bankLabel;
    @FXML private Label templeLabel;

    @FXML private Label cursorQuantity;
    @FXML private Label grandmaQuantity;
    @FXML private Label farmQuantity;
    @FXML private Label mineQuantity;
    @FXML private Label wizardQuantity;
    @FXML private Label bankQuantity;
    @FXML private Label templeQuantity;

    @FXML private Label cursorSynergy;
    @FXML private Label grandmaSynergy;
    @FXML private Label farmSynergy;
    @FXML private Label mineSynergy;
    @FXML private Label wizardSynergy;
    @FXML private Label bankSynergy;
    @FXML private Label templeSynergy;

    @FXML private ProgressBar globalBar;
    @FXML private ProgressIndicator globalIndicator;
    @FXML private Label globalSynergyLabel;

    // Map to associate buttons with building IDs
    private Map<Button, String> buildingButtonMap = new HashMap<>();
    private Map<String, Label> buildingLabelMap = new HashMap<>();
    private Map<String, Label> buildingQuantityMap = new HashMap<>();
    private Map<String, Label> buildingSynergyMap = new HashMap<>();


    @FXML private Button upgradeButton;
    private MainController mainController;
    private GameState gamestate;


    @FXML
    public void initialize() {
    }

    private void buyBuilding(String id, Button button) {
        implementBuilding(id, button);
        updateButtons(button, id);

        Label updateLabel = buildingLabelMap.get(id);
        if (gamestate.getBuilding(id).GetQuantity() == 1) {
            updateLabel.setText(gamestate.getBuilding(id).GetQuantity() + " " + id);
        } else {
            updateLabel.setText(gamestate.getBuilding(id).GetQuantity() + " " + id + "s");
        }  
        
        Label updatePrice = buildingQuantityMap.get(id);
        updatePrice.setText("" + gamestate.getBuilding(id).GetPrice());

        mainController.coinsLabel.setText(gamestate.GetCoins() + " cookies");
    }

    private void implementBuilding(String id, Button button) {
        gamestate.buyUpgrade(id);
        updateButtons(button, id);

        Label updateLabel = buildingLabelMap.get(id);
        if (gamestate.getBuilding(id).GetQuantity() == 1) {
            updateLabel.setText(gamestate.getBuilding(id).GetQuantity() + " " + id);
        } else {
            updateLabel.setText(gamestate.getBuilding(id).GetQuantity() + " " + id + "s");
        }  
        
        Label updatePrice = buildingQuantityMap.get(id);
        updatePrice.setText("" + gamestate.getBuilding(id).GetPrice());

        Label updateSynergy = buildingSynergyMap.get(id);

        gamestate.verifyGlobalSynergies();
        updateGlobalSynergyUI();
        updateGlobalSynergyLabel();
        updateSynergyLabel(updateSynergy, id);
        mainController.coinsLabel.setText(gamestate.GetCoins() + " cookies");
    }

    private void updateSynergyLabel(Label label, String id) {
        if (gamestate.getBuilding(id).GetQuantity() == 10)  {
            label.setVisible(true);
        } 

        if (gamestate.getBuilding(id).GetQuantity() % 10 == 0) {
            switch (gamestate.getBuilding(id).GetSynergyTarget()) {
                case "global":
                    label.setText("Synergy-> +" + gamestate.getBuilding(id).GetSynergyBuff() + "% Global Production");
                    break;
                case "critPower":
                    label.setText("Synergy-> +" + gamestate.getBuilding(id).GetSynergyBuff() + "% Crit Power");
                    break;
                case "critChance":
                    label.setText("Synergy-> +" + gamestate.getBuilding(id).GetSynergyBuff() + "% Crit Chance");
                    break;
                case "goldenCookie":
                    label.setText("Synergy-> +" + gamestate.getBuilding(id).GetSynergyBuff() + "% Golden Cookie Chance");
                    break;
                default:
                    break;
            }
        }
    }

    private void updateButtons(Button button, String id) {
        Building building = gamestate.getBuilding(id);
            
        if (gamestate.GetCoins() >= building.GetPrice()) {
            button.setDisable(false);
            button.setStyle("");
        } else {
            button.setDisable(true);
            button.setStyle("-fx-text-fill: red;");
        }
    }


    // ---- Helper methods to register UI elements in maps ----
    private void addButtons() {
        buildingButtonMap.put(cursorButton, "cursor");
        buildingButtonMap.put(grandmaButton, "grandma");
        buildingButtonMap.put(farmButton, "farm");
        buildingButtonMap.put(mineButton, "mine");
        buildingButtonMap.put(wizardButton, "wizard");
        buildingButtonMap.put(bankButton, "bank");
        buildingButtonMap.put(templeButton, "temple");
    }

    private void addLabels() {
        buildingLabelMap.put("cursor", cursorLabel);
        buildingLabelMap.put("grandma", grandmaLabel);
        buildingLabelMap.put("farm", farmLabel);
        buildingLabelMap.put("mine", mineLabel);
        buildingLabelMap.put("wizard", wizardLabel);
        buildingLabelMap.put("bank", bankLabel);
        buildingLabelMap.put("temple", templeLabel);
    }

    private void addQuantities() {
        buildingQuantityMap.put("cursor", cursorQuantity);
        buildingQuantityMap.put("grandma", grandmaQuantity);
        buildingQuantityMap.put("farm", farmQuantity);
        buildingQuantityMap.put("mine", mineQuantity);
        buildingQuantityMap.put("wizard", wizardQuantity);
        buildingQuantityMap.put("bank", bankQuantity);
        buildingQuantityMap.put("temple", templeQuantity);
    }

    private void addSynergies() {
        buildingSynergyMap.put("cursor", cursorSynergy);
        buildingSynergyMap.put("grandma", grandmaSynergy);
        buildingSynergyMap.put("farm", farmSynergy);
        buildingSynergyMap.put("mine", mineSynergy);
        buildingSynergyMap.put("wizard", wizardSynergy);
        buildingSynergyMap.put("bank", bankSynergy);
        buildingSynergyMap.put("temple", templeSynergy);
    }

    public void refreshAllButtons() {
        for (Map.Entry<Button, String> entry : buildingButtonMap.entrySet()) { 
            updateButtons(entry.getKey(), entry.getValue());
        }
    }

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    public void setup(GameState gamestate) {
        this.gamestate = gamestate;
        addButtons();
        addLabels();
        addQuantities();
        addSynergies();

        // Assign lambda handlers to all upgrade buttons
        for(Map.Entry<Button, String> entry : buildingButtonMap.entrySet()) {
            Button button = entry.getKey();
            String buildingId = entry.getValue();

            button.setOnAction(e -> buyBuilding(buildingId, button));
            updateButtons(button, buildingId);
        }

        // Assign lambda handlers to all upgrade labels
        for(Map.Entry<String, Label> entry : buildingLabelMap.entrySet()) {
            Label label = entry.getValue();
            String buildingId = entry.getKey();
            Building building = gamestate.getBuilding(buildingId);

            label.setText(building.GetQuantity() + " " + buildingId + "s");
        }

        // Assign lambda handlers to all upgrade labels
        for (Map.Entry<String, Label> entry : buildingQuantityMap.entrySet()) {
            Label label = entry.getValue();
            String buildingId = entry.getKey();
            Building building = gamestate.getBuilding(buildingId);
            label.setText("" + building.GetPrice());
        }

        for (Map.Entry<String, Label> entry : buildingSynergyMap.entrySet()) {
            Label label = entry.getValue();
            String buildingId = entry.getKey();
            Building building = gamestate.getBuilding(buildingId);
            label.setText("Synergy Buff -> " + building.GetSynergyBuff());
            label.setVisible(false);
        }
    }

    private void updateGlobalSynergyUI() {
        double progress = gamestate.getGlobalSynergyProgress();
        globalBar.setProgress(progress);
        globalIndicator.setProgress(progress);
    }

    private void updateGlobalSynergyLabel() {
        Synergy next = gamestate.getNextGlobalSynergy();

        if (next == null) {
            globalSynergyLabel.setText("All synergies unlocked");
            return;
        }

        int required = next.GetRequirement().get("global");

        switch(next.GetTargetId()) {
            case "global":
                globalSynergyLabel.setText(gamestate.GetTotalBuildings() + " / " + required + " buildingsss: " + (next.GetValue() / 100) + "% Global Production");
                break;
            case "critPower":
                globalSynergyLabel.setText(gamestate.GetTotalBuildings() + " / " + required + " buildings: " + (next.GetValue() / 100) + "% Critical Power");
                break;
            case "critChance":
                globalSynergyLabel.setText(gamestate.GetTotalBuildings() + " / " + required + " buildings: " + next.GetValue() + "% Critical Chance");
                break;
            case "goldenCookie":
                globalSynergyLabel.setText(gamestate.GetTotalBuildings() + " / " + required + " buildings: " + next.GetValue() + "% Golden Cookie Chance");
                break;
            default:
                break;
        }

    }
}
