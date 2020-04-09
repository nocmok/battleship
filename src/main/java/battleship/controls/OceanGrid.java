package battleship.controls;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class OceanGrid extends GridPane {
    private static final int DEFAULT_ROWS = 10;
    private static final int DEFAULT_COLS = 10;

    private final int cols;
    private final int rows;

    public OceanGrid() {
        this(DEFAULT_ROWS, DEFAULT_COLS);
    }

    public OceanGrid(int rows, int cols) {
        super();
        this.rows = rows;
        this.cols = cols;
        makeGrid(rows, cols);
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
                ShipPane pane = new ShipPane();
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

    public int columns() {
        return cols;
    }

    public int rows() {
        return rows;
    }
}
