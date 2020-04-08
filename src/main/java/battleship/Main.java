package battleship;

import battleship.controls.ShipPane;
import battleship.game.Ship;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new TrainingGame();
        primaryStage.setWidth(720);
        primaryStage.setHeight(720);
        primaryStage.setMinWidth(720);
        primaryStage.setMaxWidth(720);
        primaryStage.setMinHeight(480);
        primaryStage.setMaxHeight(480);
        primaryStage.setScene(scene);
        primaryStage.show();

//        ShipPane pane = new ShipPane();
//        primaryStage.setScene(new Scene(new StackPane(pane), 200, 200));
//        pane.setImage(ShipPane.hitImage);
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
