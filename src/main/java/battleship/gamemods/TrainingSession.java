package battleship.gamemods;

import battleship.game.Ocean;
import battleship.game.Ship;

import java.awt.*;

public class TrainingSession {
    private Ocean ocean = new Ocean();
    private int shotsFired = 0;
    private int hits = 0;
    private int shipsSunk = 0;


    public TrainingSession() {

    }

    public void reinitSession() {
        shotsFired = 0;
        hits = 0;
        shipsSunk = 0;
        ocean.clearOcean();
        ocean.placeAllShipsRandomly();
    }

    public int shotAt(int row, int column) {
        shotsFired += 1;
        int res = ocean.shotAt(row, column);
        if (res == Ocean.SUNK) {
            hits += 1;
            shipsSunk += 1;
            markSurroundings(ocean, ocean.getShipArray()[row][column]);
        }
        if (res == Ocean.HIT) {
            hits += 1;
        }
        return res;
    }

    private Rectangle markSurroundings(Ocean ocean, Ship ship) {
        Rectangle rect = new Rectangle();
        rect.x = Math.max(0, ship.getBowColumn() - 1); // 0
        rect.y = Math.max(0, ship.getBowRow() - 1); // 8

        if (ship.isHorizontal()) {
            rect.width = 1 + Math.min(9, ship.getBowColumn() + ship.getLength()) - rect.x;
            rect.height = 1 + Math.min(9, ship.getBowRow() + 1) - rect.y;
        } else {
            rect.width = 1 + Math.min(9, ship.getBowColumn() + 1) - rect.x;
            rect.height = 1 + Math.min(9, ship.getBowRow() + ship.getLength()) - rect.y;
        }

        for (int i = 0; i < rect.height; ++i) {
            for (int j = 0; j < rect.width; ++j) {
                Ship s = ocean.getShipArray()[rect.y + i][rect.x + j];
                if (s.getShipType() == Ship.EMPTY_SEA) {
                    s.shotAt(rect.y + i, rect.x + j);
                }
            }
        }
        return rect;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public int getHits() {
        return hits;
    }

    public int getShipsSunk() {
        return shipsSunk;
    }

    public Ocean getOcean() {
        return ocean;
    }

    public boolean isGameOver() {
        return shipsSunk == 10;
    }
}
