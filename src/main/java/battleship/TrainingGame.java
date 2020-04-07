package battleship;

import battleship.controls.OceanTable;
import battleship.gamemods.TrainingSession;
import battleship.player.Bundle;
import battleship.player.Player;
import battleship.player.WhippingPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.List;

public class TrainingGame extends Scene {
    private TrainingSession session;
    private OceanTable ocean;
    private Image hitImage = new Image("hit.png");
    private Image missImage = new Image("miss.png");
    private Image sunkImage = new Image("sunk.png");

    public TrainingGame(Application app) throws Exception {
        super(FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("training_game_scene.fxml")));
        ocean = (OceanTable) getChildById("ocean");
        ocean.addEventHandlerForAllCells(MouseEvent.MOUSE_CLICKED, this::onGridClicked);
        session = new TrainingSession();
        session.addPlayer(new WhippingPlayer());
        session.getPlayers().get(0).addOnPlayerResponseHandler(this::onPlayerResponse);
        session.initSession();
    }

    private Node getChildById(String id) {
        if (getRoot().getId().equals(id)) {
            return getRoot();
        }
        List<Node> children = getRoot().getChildrenUnmodifiable();
        for (var child : children) {
            if (child.getId().equals(id)) {
                return child;
            }
        }
        return null;
    }

    private void onPlayerResponse(Player player, Bundle bundle) {
        int row = bundle.getInteger(WhippingPlayer.ROW);
        int col = bundle.getInteger(WhippingPlayer.COLUMN);
        int status = bundle.getInteger(WhippingPlayer.STATUS);

        BorderPane pane = (BorderPane) ocean.getNode(row, col);
        ImageView imageView = (ImageView) pane.getChildren().get(0);
        imageView.setImage(getImageForStatus(status));
    }

    private Image getImageForStatus(int status) {
        switch (status) {
        case WhippingPlayer.HIT:
            return hitImage;
        case WhippingPlayer.MISS:
            return missImage;
        case WhippingPlayer.SUNK:
            return sunkImage;
        default:
            return null;
        }
    }

    private void onGridClicked(MouseEvent e) {
        if (e.getSource() instanceof BorderPane) {
            Node src = (Node) e.getSource();
            Bundle shot = new Bundle();
            shot.putInteger(WhippingPlayer.ROW, GridPane.getRowIndex(src));
            shot.putInteger(WhippingPlayer.COLUMN, GridPane.getColumnIndex(src));
            session.getPlayers().get(0).sendBundle(shot);
        }
    }
}
