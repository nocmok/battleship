package battleship.controls;

import battleship.player.Bundle;
import battleship.player.Player;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;

public class OceanTable extends GridPane {
    private static final int DEFAULT_ROWS = 10;
    private static final int DEFAULT_COLS = 10;

    private Image emptyImage;

    private final int cols;
    private final int rows;

    public OceanTable() {
        this(DEFAULT_ROWS, DEFAULT_COLS);
    }

    public OceanTable(int rows, int cols) {
        super();
        this.rows = rows;
        this.cols = cols;
        this.emptyImage = new Image("empty.png", true);
        makeGrid(rows, cols);
        setGridLinesVisible(true);
    }

    private void makeGrid(int rows, int cols) {
        for (int i = 0; i < rows; ++i) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100f / rows);
            getRowConstraints().add(row);
        }

        for (int i = 0; i < cols; ++i) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100f / cols);
            getColumnConstraints().add(col);
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                ImageView imageView = new ImageView(emptyImage);

                BorderPane pane = new BorderPane();
                imageView.fitWidthProperty().bind(pane.widthProperty());
                imageView.fitHeightProperty().bind(pane.heightProperty());

                pane.setCenter(imageView);

                setFillWidth(pane, true);
                setFillHeight(pane, true);
                add(pane, i, j);
            }
        }
    }

    public <T extends Event> void addEventHandlerForAllCells(EventType<T> eventType, EventHandler<T> eventHandler) {
        for (var child : getChildren()) {
            child.addEventHandler(eventType, eventHandler);
        }
    }

    public Node getNode(int row, int column) {
        for (var child : getChildren()) {
            if (GridPane.getRowIndex(child) == row && GridPane.getColumnIndex(child) == column) {
                return child;
            }
        }
        return null;
    }

    public int columns() {
        return cols;
    }

    public int rows() {
        return rows;
    }
}
