package battleship.game;

public abstract class Ship {
    public static final int BATTLESHIP = 1;
    public static final int CRUISER = 2;
    public static final int DESTROYER = 3;
    public static final int EMPTY_SEA = 4;
    public static final int SUBMARINE = 5;

    protected int bowRow;
    protected int bowColumn;
    protected int length;
    protected boolean horizontal;
    protected boolean[] hit;

    public Ship() {
    }

    public final int getLength() {
        return this.length;
    }

    public final int getBowRow() {
        return this.bowRow;
    }

    public final int getBowColumn() {
        return this.bowColumn;
    }

    public final boolean isHorizontal() {
        return this.horizontal;
    }

    public final boolean[] getHitArray() {
        return this.hit;
    }

    public abstract int getShipType();

    public final boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        Ship[][] map = ocean.getShipArray();
        int rightBound = horizontal ? column + this.length - 1 : column;
        int lowerBound = horizontal ? row : row + this.length - 1;
        boolean isCorrectPlacement = row >= 0 && lowerBound < map.length && column >= 0 && rightBound < map[row].length;
        if (isCorrectPlacement) {
            for (int i = row - 1; i <= lowerBound + 1; ++i) {
                for (int j = column - 1; j <= rightBound + 1; ++j) {
                    if (ocean.isOccupied(i, j)) {
                        return false;
                    }
                }
            }
        }

        return isCorrectPlacement;
    }

    public final void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        Ship[][] map = ocean.getShipArray();
        this.horizontal = horizontal;
        this.bowRow = row;
        this.bowColumn = column;
        int j;
        if (this.horizontal) {
            for (j = this.bowColumn; j < this.bowColumn + this.length; ++j) {
                map[this.bowRow][j] = this;
            }
        } else {
            for (j = this.bowRow; j < this.bowRow + this.length; ++j) {
                map[j][this.bowColumn] = this;
            }
        }

    }

    public int transformCoordinates(int row, int col) {
        return horizontal ? col - bowColumn : row - bowRow;
    }

    /**
     * @return whether ship was hit. If there are multiple shots at the same
     * cell, returns true only on first shot.
     */
    public boolean shotAt(int row, int column) {
        int cell = transformCoordinates(row, column);
        boolean res = !hit[cell];
        hit[cell] = true;
        return res;
    }

    //    private boolean intersects(int row, int column) {
    //        return this.horizontal ? row == this.bowRow && column >= this.bowColumn &&
    //                column < this.bowColumn + this.length : row >= this.bowRow &&
    //                       row < this.bowRow + this.length && column == this.bowColumn;
    //    }

    public boolean isSunk() {
        for (int i = 0; i < hit.length; ++i) {
            boolean part = hit[i];
            if (!part) {
                return false;
            }
        }

        return true;
    }

    public String toString() {
        return this.isSunk() ? "x" : "S";
    }
}
