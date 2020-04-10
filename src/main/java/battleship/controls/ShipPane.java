package battleship.controls;

import battleship.BattleShipApp;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ShipPane extends Pane {
    private ImageView imageView;

    public ShipPane() {
        super();
        imageView = new ImageView();
        imageView.setImage(null);
        imageView.setOpacity(0.5);
        getChildren().add(imageView);
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());
    }

    public void setImage(Image img) {
        imageView.setImage(img);
    }
}
