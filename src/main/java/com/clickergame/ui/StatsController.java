package com.clickergame.ui;

import com.clickergame.core.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatsController {
    @FXML private Label cookieProductionMultiplier;
    @FXML private Label passiveIncomeMultiplier;
    @FXML private Label totalClicks;
    @FXML private Label cookiesPerClick;
    @FXML private Label totalBuildings;
    @FXML private Label totalCookiesProduced;
    @FXML private Label criticalChance;
    @FXML private Label criticalPower;
    @FXML private Label goldenCookieChance;
    @FXML private Label goldenCookieClicks;
    @FXML private Label totalPrestiges;

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
       this.passiveIncomeMultiplier.setText("Passive Income Multiplier -> " + gamestate.getPassiveIncomeMultiplier() + "x");
       this.totalClicks.setText("Total Clicks -> " + gamestate.GetTotalClicks());
       this.cookiesPerClick.setText("Cookies per click -> " + gamestate.GetClicks());
       this.totalBuildings.setText("Total Buildings -> " + gamestate.GetTotalBuildings());
       this.totalCookiesProduced.setText("Total Cookies -> " + gamestate.GetTotalCookies());
       this.criticalChance.setText("Critical Chance -> " + gamestate.GetCriticalChance() + "%");
       this.criticalPower.setText("Critical Power -> " + gamestate.GetCriticalPower() + "x");
       this.goldenCookieChance.setText("Golden Cookie Chance -> " + gamestate.GetGoldenChance() + "%");
       this.goldenCookieClicks.setText("Golden Cookie Clicks -> " + gamestate.getGoldenClicks());
       this.totalPrestiges.setText("Total Prestiges -> " + gamestate.getTotalPrestiges());
       ;
    }

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }
}
