package battleship.gamemods;

import battleship.game.Ocean;
import battleship.game.Ship;

public class TrainingSession {
    private Ocean ocean = new Ocean();
    private int shotsFired = 0;
    private int hits = 0;
    private int shipsSunk = 0;


    public TrainingSession() {

    }

    public void reinitSession() {
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

    private void markSurroundings(Ocean ocean, Ship ship) {
        int rectX = Math.max(0, ship.getBowColumn() - 1);
        int rectY = Math.max(0, ship.getBowRow() - 1);

        int rectW;
        int rectH;

        if (ship.isHorizontal()) {
            rectW = 1 + Math.min(9, ship.getBowColumn() + ship.getLength()) - rectX;
            rectH = 1 + Math.min(9, ship.getBowRow() + 1) - rectY;
        } else {
            rectW = 1 + Math.min(9, ship.getBowColumn() + 1) - rectX;
            rectH = 1 + Math.min(9, ship.getBowRow() + ship.getLength()) - rectY;
        }

        for (int i = 0; i < rectH; ++i) {
            for (int j = 0; j < rectW; ++j) {
                Ship s = ocean.getShipArray()[rectY + i][rectX + j];
                if (s.getShipType() == Ship.EMPTY_SEA) {
                    s.shotAt(rectY + i, rectX + j);
                }
            }
        }
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
}
