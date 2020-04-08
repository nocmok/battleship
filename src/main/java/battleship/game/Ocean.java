package battleship.game;

import java.util.Random;

public class Ocean {
    public static final int UNTOUCHED = 3;
    public static final int MISS = 0;
    public static final int HIT = 1;
    public static final int SUNK = 2;

    private Ship[][] ships = new Ship[10][10];
    private Random rng = new Random();

    public Ocean() {
        clearOcean();
    }

    public void clearOcean() {
        for (int i = 0; i < this.ships.length; ++i) {
            for (int j = 0; j < this.ships[i].length; ++j) {
                (new EmptySea()).placeShipAt(i, j, true, this);
            }
        }
    }

    public void placeAllShipsRandomly() {
        this.placeOneShip(new Battleship());
        this.placeOneShip(new Cruiser());
        this.placeOneShip(new Cruiser());
        this.placeOneShip(new Destroyer());
        this.placeOneShip(new Destroyer());
        this.placeOneShip(new Destroyer());
        this.placeOneShip(new Submarine());
        this.placeOneShip(new Submarine());
        this.placeOneShip(new Submarine());
        this.placeOneShip(new Submarine());
    }

    private void placeOneShip(Ship ship) {
        int row;
        int column;
        boolean horizontal;
        do {
            row = Math.abs(this.rng.nextInt(this.ships.length));
            column = Math.abs(this.rng.nextInt(this.ships[row].length));
            horizontal = this.rng.nextBoolean();
        } while (!ship.okToPlaceShipAt(row, column, horizontal, this) &&
                !ship.okToPlaceShipAt(row, column, horizontal = !horizontal, this));

        ship.placeShipAt(row, column, horizontal, this);
    }

    boolean isOccupied(int row, int column) {
        return row >= 0 && row < this.ships.length && column >= 0 && column < this.ships[row].length && this.ships[row][column].getShipType() != Ship.EMPTY_SEA;
    }

    public int shotAt(int row, int column) {
        Ship ship = ships[row][column];
        if (ship.shotAt(row, column)) {
            if (ship.isSunk()) {
                return SUNK;
            }
            return HIT;
        }
        return MISS;
    }

    public Ship[][] getShipArray() {
        return this.ships;
    }

    public int getCellStatus(int row, int col) {
        Ship ship = ships[row][col];
        if (ship.isSunk()) {
            return SUNK;
        }
        boolean hit = ship.getHitArray()[ship.transformCoordinates(row, col)];
        if (!hit) {
            return UNTOUCHED;
        }
        if (ship.getShipType() == Ship.EMPTY_SEA) {
            return MISS;
        }
        return HIT;
    }

    public void print() {
        System.out.println("   0  1  2  3  4  5  6  7  8  9");

        for (int i = 0; i < this.ships.length; ++i) {
            System.out.print(String.format("%d  ", i));

            for (int j = 0; j < this.ships[i].length; ++j) {
                Ship currentShip = this.ships[i][j];
                int indexOfCurrentPart =
                        currentShip.isHorizontal() ? j - currentShip.getBowColumn() : i - currentShip.getBowRow();
                System.out.print(String.format("%s  ",
                                               currentShip.getHitArray()[indexOfCurrentPart] ? currentShip.toString()
                                                                                             : "."));
            }

            System.out.println();
        }

    }
}
