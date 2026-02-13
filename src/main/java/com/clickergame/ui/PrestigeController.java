package com.clickergame.ui;

import java.util.HashMap;
import java.util.Map;

import com.clickergame.core.GameState;
import com.clickergame.core.PrestigeSystem;
import com.clickergame.systems.Prestige;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;


public class PrestigeController {

    private GameState gamestate;
    private PrestigeSystem prestigeSystem;
    private MainController mainController;
    @FXML private TilePane cookieRequirementPane;
    @FXML private Label cookieRequirementLabel;
    @FXML private Label sugarCrystals;
    @FXML private Button buyPrestige;
    @FXML private Label upgradeName;
    @FXML private Button upgradeButton;

    @FXML private Label upName1;
    @FXML private Label upName2;
    @FXML private Label upName3;
    @FXML private Label upName4;
    @FXML private Label upName5;
    @FXML private Label upName6;
    @FXML private Label upName7;
    @FXML private Label upName8;
    @FXML private Label upName9;
    @FXML private Label upName10;
    @FXML private Label upName11;
    @FXML private Label upName12;
    @FXML private Label upName13;
    @FXML private Label upName14;

    @FXML private Label upProgress1;
    @FXML private Label upProgress2;
    @FXML private Label upProgress3;
    @FXML private Label upProgress4;
    @FXML private Label upProgress5;
    @FXML private Label upProgress6;
    @FXML private Label upProgress7;
    @FXML private Label upProgress8;
    @FXML private Label upProgress9;
    @FXML private Label upProgress10;
    @FXML private Label upProgress11;
    @FXML private Label upProgress12;
    @FXML private Label upProgress13;
    @FXML private Label upProgress14;

    @FXML private Label upQuant1;
    @FXML private Label upQuant2;
    @FXML private Label upQuant3;
    @FXML private Label upQuant4;
    @FXML private Label upQuant5;
    @FXML private Label upQuant6;
    @FXML private Label upQuant7;
    @FXML private Label upQuant8;
    @FXML private Label upQuant9;
    @FXML private Label upQuant10;
    @FXML private Label upQuant11;
    @FXML private Label upQuant12;
    @FXML private Label upQuant13;
    @FXML private Label upQuant14;

    @FXML private Button upBtn1;
    @FXML private Button upBtn2;
    @FXML private Button upBtn3;
    @FXML private Button upBtn4;
    @FXML private Button upBtn5;
    @FXML private Button upBtn6;
    @FXML private Button upBtn7;
    @FXML private Button upBtn8;
    @FXML private Button upBtn9;
    @FXML private Button upBtn10;
    @FXML private Button upBtn11;
    @FXML private Button upBtn12;
    @FXML private Button upBtn13;
    @FXML private Button upBtn14;

    private Map<Integer, Label> upNameMap = new HashMap<>();
    private Map<Integer, Label> upQuant = new HashMap<>();
    private Map<Integer, Label> upProgress = new HashMap<>();
    public Map<Button, Integer> upBtnMap = new HashMap<>();

    
    @FXML
    public void initialize() {

    }

    //*** Helper methods to update UI elements***
    public void setup(GameState gamestate) {
        this.gamestate = gamestate;
        loadUI();
    }

    public void loadUI() {
        // Load UI elements       
        addButtons();  
        addQuantities(); 
        addProgress();
        addNames();

        sugarCrystals.setText("You have " + gamestate.getSugarCrystals() + " crystals! :)");

        // Update prestige list
        for (Map.Entry<Button, Integer> entry : upBtnMap.entrySet()) {
            Button button = entry.getKey();
            Integer upgradeID = entry.getValue();
            Prestige p = gamestate.getPrestige(upgradeID);

            Label nameLabel = upNameMap.get(upgradeID);
            Label quantityLabel = upQuant.get(upgradeID);
            Label progressLabel = upProgress.get(upgradeID);

            if (p.GetUpgradeLevel() == p.GetMultiplierList().size()) {
                nameLabel.setVisible(false);
                button.setVisible(false);
            } else {    
                // Set Label texts
                nameLabel.setText(p.GetName());
                progressLabel.setText(p.GetUpgradeLevel() + "/" + p.GetMultiplierList().size());

                String buffValue = String.valueOf(p.GetMultiplier(p.GetUpgradeLevel()));
                switch (p.GetTargetID()) {
                    case "critChance":
                        quantityLabel.setText("+" + buffValue + "% Crit Chance");
                        break;  
                    case "critPower":
                        quantityLabel.setText(buffValue + "% Crit Power");
                        break;   
                    case "click":
                        quantityLabel.setText(buffValue + "x Click(s)");
                        break;
                    case "buildingCost":
                        quantityLabel.setText("-" + buffValue + "% Building Cost");
                        break;
                    case "global":
                        quantityLabel.setText(buffValue + "x Production");
                        break;
                    case "passive":
                        quantityLabel.setText(buffValue + "% Passive");
                        break;
                    case "goldenChance":
                        quantityLabel.setText(buffValue + "% Golden Chance");
                        break;
                    case "goldenDuration":
                        quantityLabel.setText(buffValue + "x Golden Duration");
                        break;
                    default:
                        quantityLabel.setText(buffValue + "x Production");
                        break;
                }

                // Set action
                button.setOnAction(e -> {
                    button.setText("Buy for " + p.GetPrice(p.GetUpgradeLevel()) + " crystals");
                    gamestate.buyPermamentUpgrade(upgradeID, p.GetUpgradeLevel());
                });
            }
        }
    
        // Make the 'Prestige' button visible whether the user has enough total coins, or not
        if (verifyCookiesAmount()) {
            this.buyPrestige.setVisible(true);
            this.cookieRequirementLabel.setVisible(false);
        } else {
            this.buyPrestige.setVisible(false);
            this.cookieRequirementLabel.setVisible(true);
            this.cookieRequirementLabel.setText("You need " + (int)(gamestate.getPrestigePrice() - gamestate.GetTotalClicks()) + " more cookies to unlock prestige!");
        }
    }

    //*** Helper methods to update prestige elements***

    public boolean verifyCookiesAmount() {
        boolean isAvailable = gamestate.GetTotalClicks() >= gamestate.getPrestigePrice() ? true : false;
        return isAvailable;
    }
 
    public void buyPrestige() {
        gamestate.buyPrestige();
        loadUI();
    }


    //*** Helper methods to register UI elements in maps ***
    private void addButtons() {
        upBtnMap.put(upBtn1, 1);
        upBtnMap.put(upBtn2, 2);
        upBtnMap.put(upBtn3, 3);
        upBtnMap.put(upBtn4, 4);
        upBtnMap.put(upBtn5, 5);
        upBtnMap.put(upBtn6, 6);
        upBtnMap.put(upBtn7, 7);
        upBtnMap.put(upBtn8, 8);
        upBtnMap.put(upBtn9, 9);
        upBtnMap.put(upBtn10, 10);
        upBtnMap.put(upBtn11, 11);
        upBtnMap.put(upBtn12, 12);
        upBtnMap.put(upBtn13, 13);
        upBtnMap.put(upBtn14, 14);
    }

    private void addQuantities() {
        upQuant.put(1, upQuant1);
        upQuant.put(2, upQuant2);
        upQuant.put(3, upQuant3);
        upQuant.put(4, upQuant4);
        upQuant.put(5, upQuant5);
        upQuant.put(6, upQuant6);
        upQuant.put(7, upQuant7);
        upQuant.put(8, upQuant8);
        upQuant.put(9, upQuant9);
        upQuant.put(10, upQuant10);
        upQuant.put(11, upQuant11);
        upQuant.put(12, upQuant12);
        upQuant.put(13, upQuant13);
        upQuant.put(14, upQuant14);
    }

    private void addProgress() {
        upProgress.put(1, upProgress1);
        upProgress.put(2, upProgress2);
        upProgress.put(3, upProgress3);
        upProgress.put(4, upProgress4);
        upProgress.put(5, upProgress5);
        upProgress.put(6, upProgress6);
        upProgress.put(7, upProgress7);
        upProgress.put(8, upProgress8);
        upProgress.put(9, upProgress9);
        upProgress.put(10, upProgress10);
        upProgress.put(11, upProgress11);
        upProgress.put(12, upProgress12);
        upProgress.put(13, upProgress13);
        upProgress.put(14, upProgress14);
    }   

    private void addNames() {
        upNameMap.put(1, upName1);
        upNameMap.put(2, upName2);
        upNameMap.put(3, upName3);
        upNameMap.put(4, upName4);
        upNameMap.put(5, upName5);
        upNameMap.put(6, upName6);
        upNameMap.put(7, upName7);
        upNameMap.put(8, upName8);
        upNameMap.put(9, upName9);
        upNameMap.put(10, upName10);
        upNameMap.put(11, upName11);
        upNameMap.put(12, upName12);
        upNameMap.put(13, upName13);
        upNameMap.put(14, upName14);
    }

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }
}
