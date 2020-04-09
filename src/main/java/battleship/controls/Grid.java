package battleship.controls;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class Grid extends GridPane {
    private static final int DEFAULT_ROWS = 10;
    private static final int DEFAULT_COLS = 10;

    public Grid() {
        super();
        makeGrid();
        setGridLinesVisible(true);
    }

    private void makeGrid() {
        for (int i = 0; i < DEFAULT_ROWS; ++i) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100f / DEFAULT_ROWS);
            getRowConstraints().add(row);
        }

        for (int j = 0; j < DEFAULT_COLS; ++j) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100f / DEFAULT_COLS);
            getColumnConstraints().add(col);
        }
    }
}
