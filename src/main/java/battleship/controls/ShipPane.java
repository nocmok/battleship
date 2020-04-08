package battleship.controls;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ShipPane extends Pane {
    public static final Image emptyImage = new Image("empty.png");
    public static final Image hitImage = new Image("hit.png");
    public static final Image missImage = new Image("miss.png");
    public static final Image sunkImage = new Image("sunk.png");

    private ImageView imageView;

    public ShipPane() {
        super();
        imageView = new ImageView(emptyImage);
        getChildren().add(imageView);
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());
    }

    public void setImage(Image img){
        imageView.setImage(img);
    }
}
