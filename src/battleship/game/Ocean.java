package battleship.game;

import java.util.Random;

public class Ocean {
    private Ship[][] ships = new Ship[10][10];
    private int shotsFired = 0;
    private int hitCount = 0;
    private int shipsSunk = 0;
    private Random rng = new Random();

    public Ocean() {
        for(int i = 0; i < this.ships.length; ++i) {
            for(int j = 0; j < this.ships[i].length; ++j) {
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
        } while(!ship.okToPlaceShipAt(row, column, horizontal, this) && !ship.okToPlaceShipAt(row, column, horizontal = !horizontal, this));

        ship.placeShipAt(row, column, horizontal, this);
    }

    boolean isOccupied(int row, int column) {
        return row >= 0 && row < this.ships.length && column >= 0 && column < this.ships[row].length && !this.ships[row][column].getShipType().equals("emptysea");
    }

    public boolean shotAt(int row, int column) {
        ++this.shotsFired;
        Ship ship = this.ships[row][column];
        boolean isSunkBeforeShot = ship.isSunk();
        if (!ship.isSunk()) {
            int indexOfPurpose = ship.isHorizontal() ? column - ship.getBowColumn() : row - ship.getBowRow();
            if (!ship.getHitArray()[indexOfPurpose] && !ship.getShipType().equals("emptysea")) {
                ++this.hitCount;
            }

            ship.shotAt(row, column);
            if (ship.isSunk()) {
                ++this.shipsSunk;
                int leftBound = Math.max(0, ship.getBowColumn() - 1);
                int upperBound = Math.max(0, ship.getBowRow() - 1);
                int rightBound = ship.isHorizontal() ? ship.getBowColumn() + ship.getLength() : ship.getBowColumn() + 1;
                int lowerBound = ship.isHorizontal() ? ship.getBowRow() + 1 : ship.getBowRow() + ship.getLength();
                rightBound = Math.min(this.ships.length - 1, rightBound);
                lowerBound = Math.min(this.ships.length - 1, lowerBound);

                for(int i = upperBound; i <= lowerBound; ++i) {
                    for(int j = leftBound; j <= rightBound; ++j) {
                        if (!this.isOccupied(i, j)) {
                            this.ships[i][j].shotAt(i, j);
                        }
                    }
                }
            }
        }

        return isSunkBeforeShot && !ship.getShipType().equals("emptysea");
    }

    public int getShotsFired() {
        return this.shotsFired;
    }

    public int getHitCount() {
        return this.hitCount;
    }

    public int getShipsSunk() {
        return this.shipsSunk;
    }

    public boolean isGameOver() {
        return this.shipsSunk == 10;
    }

    public Ship[][] getShipArray() {
        return this.ships;
    }

    public void print() {
        System.out.println("   0  1  2  3  4  5  6  7  8  9");

        for(int i = 0; i < this.ships.length; ++i) {
            System.out.print(String.format("%d  ", i));

            for(int j = 0; j < this.ships[i].length; ++j) {
                Ship currentShip = this.ships[i][j];
                int indexOfCurrentPart = currentShip.isHorizontal() ? j - currentShip.getBowColumn() : i - currentShip.getBowRow();
                System.out.print(String.format("%s  ", currentShip.getHitArray()[indexOfCurrentPart] ? currentShip.toString() : "."));
            }

            System.out.println();
        }

    }
}
