package battleship;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.net.URL;

public class MainScene extends Scene {
    private static final StackPane placeHolder = new StackPane(new Label("unable to access layout"));

    public MainScene() throws Exception {
        super(placeHolder);
        URL url = BattleShipApp.getMainApp().getResourceAsURL("main_scene.fxml");
        if (url == null) {
            return;
        }
        setRoot(FXMLLoader.load(url));

        Button trainingButton = (Button) lookup("#training-button");
        trainingButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onTrainingModeSelected);
    }

    private void onTrainingModeSelected(MouseEvent e) {
        BattleShipApp.getMainApp().setScene(BattleShipApp.TRAINING_GAME_SCENE);
    }
}
