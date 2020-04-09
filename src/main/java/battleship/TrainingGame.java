package battleship;

import battleship.controls.OceanGrid;
import battleship.controls.ShipPane;
import battleship.game.Ocean;
import battleship.gamemods.TrainingSession;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.awt.Rectangle;
import java.io.File;
import java.net.URL;

public class TrainingGame extends Scene {
    private TrainingSession session;
    private OceanGrid oceanGrid;

    private static final StackPane placeHolder = new StackPane(new Label("unable to access layout"));

    public TrainingGame() throws Exception {
        super(placeHolder);

        URL url = Thread.currentThread().getContextClassLoader().getResource("training_game_scene.fxml");
        if (url == null || !new File(url.getPath()).exists()) {
            return;
        }
        setRoot(FXMLLoader.load(url));

        session = new TrainingSession();
        session.reinitSession();

        oceanGrid = (OceanGrid) lookup("#ocean");
        oceanGrid.addEventHandlerForAllCells(MouseEvent.MOUSE_CLICKED, this::onGridClicked);

        Button restart = (Button) lookup("#restart");
        restart.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onRestartClicked);

        TextField console = (TextField) lookup("#console");

    }

    private Image getImageForStatus(int status, boolean isHorizontal) {
        switch (status) {
        case Ocean.HIT:
            return ShipPane.hitImage;
        case Ocean.MISS:
            return ShipPane.missImage;
        case Ocean.SUNK:
            return isHorizontal ? ShipPane.sunkHorizontalImage : ShipPane.sunkVerticalImage;
        case Ocean.UNTOUCHED:
        default:
            return null;
        }
    }

    private void onRestartClicked(MouseEvent e) {
        if (e.getSource() instanceof Button) {
            session.reinitSession();
            drawOcean(session.getOcean(), new Rectangle(0, 0, 10, 10));
        }
    }

    private void onGridClicked(MouseEvent e) {
        if (e.getSource() instanceof ShipPane) {
            Node src = (Node) e.getSource();
            Rectangle rect = session.shotAt(GridPane.getRowIndex(src), GridPane.getColumnIndex(src));
            drawOcean(session.getOcean(), rect);
        }
    }

    private void drawOcean(Ocean ocean, Rectangle rect) {
        for (var child : oceanGrid.getChildren()) {
            Integer row = GridPane.getRowIndex(child);
            Integer col = GridPane.getColumnIndex(child);
            if (row != null && col != null && rect.contains(col, row)) {
                Image img = getImageForStatus(ocean.getCellStatus(row, col), ocean.getShipArray()[row][col].isHorizontal());
                ((ShipPane) child).setImage(img);
            }
        }
    }
}
