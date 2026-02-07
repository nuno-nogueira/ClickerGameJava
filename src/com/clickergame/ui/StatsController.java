package com.clickergame.ui;

import java.util.HashMap;
import java.util.Map;

import com.clickergame.core.GameState;
import com.clickergame.systems.Upgrade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class StatsController {
    @FXML private Label cookieProductionMultiplier;
    @FXML private Label totalClicks;
    @FXML private Label cookiesPerClick;
    @FXML private Label totalBuildings;
    @FXML private Label totalCookiesProduced;
    @FXML private Label criticalChance;
    @FXML private Label criticalPower;
    @FXML private Label goldenCookieChance;
    @FXML private Label goldenCookieClicks;

    private GameState gamestate;
    private MainController mainController;

    @FXML
    public void initialize() {
    }


    public void setup(GameState gamestate) {
        this.gamestate = gamestate;
        refreshStats();
    }

    public void refreshStats() {
       this.cookieProductionMultiplier.setText("Cookie Production Multiplier -> " + gamestate.GetGlobalMultiplier() + "x");
       this.totalClicks.setText("Total Clicks -> " + gamestate.GetTotalClicks());
       this.cookiesPerClick.setText("Cookies per click -> " + gamestate.GetClicks());
       this.totalBuildings.setText("Total Buildings -> " + gamestate.GetTotalBuildings());
       this.totalCookiesProduced.setText("Total Cookies -> " + gamestate.GetTotalCookies());
       this.criticalChance.setText("Critical Chance -> " + gamestate.GetCriticalChance() + "%");
       this.criticalPower.setText("Critical Power -> " + gamestate.GetCriticalPower() + "%");
       this.goldenCookieChance.setText("Golden Cookie Chance -> " + gamestate.GetGoldenChance() + "%");
       this.goldenCookieClicks.setText("Golden Cookie Clicks -> " + gamestate.getGoldenClicks());
       ;
    }

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }
}
