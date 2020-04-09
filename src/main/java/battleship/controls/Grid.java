package battleship.controls;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class Grid extends GridPane {
    private static final int DEFAULT_ROWS = 12;
    private static final int DEFAULT_COLS = 12;

    public Grid() {
        super();
        makeGrid();
    }

    private void makeGrid() {
        for (int i = 0; i < DEFAULT_ROWS; ++i) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100f / DEFAULT_ROWS);
            row.setValignment(VPos.CENTER);
            getRowConstraints().add(row);
            if (i > 0 && i < DEFAULT_ROWS - 1) {
                Label label = new Label();
                label.setTextAlignment(TextAlignment.CENTER);
                label.setText(Integer.valueOf(i - 1).toString());
                setFillWidth(label, true);
                setFillHeight(label, true);
                add(label, i, 0);
            }
        }

        for (int j = 0; j < DEFAULT_COLS; ++j) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100f / DEFAULT_COLS);
            col.setHalignment(HPos.CENTER);
            getColumnConstraints().add(col);
            if (j > 0 && j < DEFAULT_COLS - 1) {
                Label label = new Label();
                label.setTextAlignment(TextAlignment.CENTER);
                label.setText(Integer.valueOf(j - 1).toString());
                setFillWidth(label, true);
                setFillHeight(label, true);
                add(label, 0, j);
            }
        }
    }
}
