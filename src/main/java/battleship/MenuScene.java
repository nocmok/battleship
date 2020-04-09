package battleship;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.net.URL;

public class MenuScene extends Scene {
    private static final StackPane placeHolder = new StackPane(new Label("unable to access layout"));

    public MenuScene() throws Exception {
        super(placeHolder);

        URL url = Thread.currentThread().getContextClassLoader().getResource("main_scene.fxml");
        if (url == null || !new File(url.getPath()).exists()) {
            return;
        }
        setRoot(FXMLLoader.load(url));

        Button trainingButton = (Button) lookup("#training-button");
        trainingButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onTrainingModeSelected);
    }

    private void onTrainingModeSelected(MouseEvent e) {
        BattleShipApp app = BattleShipApp.app;
        app.primaryStage.setScene(app.trainingScene);
    }
}
