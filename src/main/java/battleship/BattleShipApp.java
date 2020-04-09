package battleship;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BattleShipApp extends Application {
    public static BattleShipApp app;

    public Stage primaryStage;
    public Scene menuScene;
    public Scene trainingScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        app = this;
        this.primaryStage = primaryStage;

        menuScene = new MenuScene();
        trainingScene = new TrainingGameScene();

        primaryStage.setResizable(false);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
