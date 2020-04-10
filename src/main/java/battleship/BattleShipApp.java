package battleship;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

public class BattleShipApp extends Application {
    private static BattleShipApp app;

    public static final int MAIN_SCENE = 0;
    public static final int TRAINING_GAME_SCENE = 1;

    private Stage primaryStage;
    private HashMap<Integer, Scene> scenes = new HashMap<>();

    private Image hitImage;
    private Image missImage;
    private Image sunkVerticalImage;
    private Image sunkHorizontalImage;

    public static final int HIT_IMAGE = 0;
    public static final int MISS_IMAGE = 1;
    public static final int SUNK_VERTICAL_IMAGE = 2;
    public static final int SUNK_HORIZONTAL_IMAGE = 3;

    private HashMap<Integer, Image> images = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        app = this;
        this.primaryStage = primaryStage;

        loadImages();

        scenes.put(MAIN_SCENE, new MainScene());
        scenes.put(TRAINING_GAME_SCENE, new TrainingGameScene());

        primaryStage.setResizable(false);
        setScene(MAIN_SCENE);
        primaryStage.show();
    }

    public void setScene(int scene) {
        if (scenes.containsKey(scene)) {
            primaryStage.setScene(scenes.get(scene));
        }
    }

    public URL getResourceAsURL(String id) {
        return getClass().getResource("/" + id);
    }

    public InputStream getResourceAsStream(String id) {
        return getClass().getResourceAsStream("/" + id);
    }

    public static BattleShipApp getMainApp() {
        return app;
    }

    private void loadImages() {
        hitImage = new Image(getResourceAsStream("hit.png"));
        missImage = new Image(getResourceAsStream("miss.png"));
        sunkVerticalImage = new Image(getResourceAsStream("sunk_vertical.png"));
        sunkHorizontalImage = new Image(getResourceAsStream("sunk_horizontal.png"));

        images.put(HIT_IMAGE, hitImage);
        images.put(MISS_IMAGE, missImage);
        images.put(SUNK_VERTICAL_IMAGE, sunkVerticalImage);
        images.put(SUNK_HORIZONTAL_IMAGE, sunkHorizontalImage);
    }

    public Image getImageById(int id) {
        if (images.containsKey(id)) {
            return images.get(id);
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
