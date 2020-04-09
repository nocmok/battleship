package battleship.controls;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ShipPane extends Pane {
    public static final Image hitImage = new Image("hit.png");
    public static final Image missImage = new Image("miss.png");
    public static final Image sunkVerticalImage = new Image("sunk_vertical.png");
    public static final Image sunkHorizontalImage = new Image("sunk_horizontal.png");

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
