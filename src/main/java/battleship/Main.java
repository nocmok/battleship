package battleship;

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
        URL url = Thread.currentThread().getContextClassLoader().getResource("training_game_scene.fxml");
        Scene scene = new TrainingGame(this);
        primaryStage.setScene(scene);
        primaryStage.show();

//        URL url = Thread.currentThread().getContextClassLoader().getResource("hit.png");
//        Image hitImage = new Image(url.toString());
//        ImageView imageView = new ImageView(hitImage);
//        primaryStage.setScene(new Scene(new StackPane(imageView), 200, 200));
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
