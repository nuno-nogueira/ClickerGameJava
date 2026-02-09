package com.clickergame.app;

import com.clickergame.persistence.SaveData;
import com.clickergame.persistence.SaveManager;
import com.clickergame.ui.MainController;
import com.clickergame.persistence.SaveData;
import com.clickergame.core.GameState;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/MainView.fxml")
        );
        Parent root = loader.load();
        MainController controller = loader.getController();
        GameState gamestate = controller.getGameState();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        //test
        primaryStage.setOnCloseRequest(e -> {
            SaveData save = gamestate.toSaveData();
            new SaveManager().save(save);
            controller.stopGameLoop();
        });
    }
}
