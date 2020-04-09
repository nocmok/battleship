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
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.awt.Rectangle;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class TrainingGameScene extends Scene {
    private static final StackPane placeHolder = new StackPane(new Label("unable to access layout"));

    private TrainingSession session;
    private OceanGrid oceanGrid;
    private Label info;
    private TextArea log;

    public TrainingGameScene() throws Exception {
        super(placeHolder);

        URL url = Thread.currentThread().getContextClassLoader().getResource("training_game_scene.fxml");
        if (url == null || !new File(url.getPath()).exists()) {
            return;
        }
        setRoot(FXMLLoader.load(url));

        session = new TrainingSession();

        oceanGrid = (OceanGrid) lookup("#ocean");
        oceanGrid.addEventHandlerForAllCells(MouseEvent.MOUSE_CLICKED, this::onGridClicked);

        Button restart = (Button) lookup("#restart");
        restart.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onRestartClicked);

        Button back = (Button) lookup("#back");
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onBackButtonClicked);

        TextField console = (TextField) lookup("#console");
        console.addEventHandler(KeyEvent.KEY_PRESSED, this::onKeyPressed);

        info = (Label) lookup("#info");

        log = (TextArea) lookup("#log");

        restartGame();
    }

    private void onBackButtonClicked(MouseEvent e) {
        BattleShipApp app = BattleShipApp.app;
        app.primaryStage.setScene(app.menuScene);
    }

    private boolean validateInteger(String num) {
        return num.matches("\\d");
    }

    private List<Integer> parseIntegers(String input) {
        String delimiters = "[ ,:]+";
        String[] strs = input.split(delimiters);
        List<Integer> nums = new LinkedList<>();
        for (var str : strs) {
            if (validateInteger(str)) {
                nums.add(Integer.parseInt(str));
            }
        }
        return nums;
    }

    private void onKeyPressed(KeyEvent e) {
        if (!(e.getSource() instanceof TextField)) {
            return;
        }
        if (e.getCode().equals(KeyCode.ENTER)) {
            TextField console = (TextField) e.getSource();
            String input = console.getText();
            List<Integer> coordinates = parseIntegers(input);
            if (coordinates.size() < 2) {
                // report
            } else {
                performShot(coordinates.get(0), coordinates.get(1));
            }
        }
    }

    private void restartGame() {
        session.reinitSession();
        log.clear();
        info.setText(getInfo());
        drawOcean(session.getOcean());
    }

    private void onRestartClicked(MouseEvent e) {
        if (e.getSource() instanceof Button) {
            restartGame();
        }
    }

    private String getInfo() {
        return String.format("total shots: %d%n" +
                                     "total hits : %d%n" +
                                     "ships sunk : %d",
                             session.getShotsFired(), session.getHits(), session.getShipsSunk());
    }

    private void congratulate() {
        String congrats = String.format("===YOU WIN!===%nTOTAL RESULTS :%n");
        info.setText(congrats + getInfo());
    }

    private String shotStatusToString(int status) {
        switch (status) {
        case Ocean.MISS:
            return "miss";
        case Ocean.HIT:
            return "hit";
        case Ocean.SUNK:
            return "sunk";
        default:
            return "wtf";
        }
    }

    private void logShot(int row, int col, int status) {
        log.appendText(String.format("[%d, %d] -> %s%n", row, col, shotStatusToString(status)));
    }

    private void performShot(int row, int col) {
        if (!session.isGameOver()) {
            logShot(row, col, session.shotAt(row, col));
            if (session.isGameOver()) {
                congratulate();
            } else {
                info.setText(getInfo());
            }
            drawOcean(session.getOcean());
        }
    }

    private void onGridClicked(MouseEvent e) {
        if (e.getSource() instanceof ShipPane) {
            Node src = (Node) e.getSource();
            performShot(GridPane.getRowIndex(src), GridPane.getColumnIndex(src));
        }
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

    private void drawOcean(Ocean ocean) {
        for (var child : oceanGrid.getChildren()) {
            Integer row = GridPane.getRowIndex(child);
            Integer col = GridPane.getColumnIndex(child);
            if (row != null && col != null) {
                Image img = getImageForStatus(ocean.getCellStatus(row, col), ocean.getShipArray()[row][col].isHorizontal());
                ((ShipPane) child).setImage(img);
            }
        }
    }
}
