package battleship.game;

public abstract class Ship {
    protected int bowRow;
    protected int bowColumn;
    protected int length;
    protected boolean horizontal;
    protected boolean[] hit;

    public Ship() {
    }

    public int getLength() {
        return this.length;
    }

    public int getBowRow() {
        return this.bowRow;
    }

    public int getBowColumn() {
        return this.bowColumn;
    }

    public boolean isHorizontal() {
        return this.horizontal;
    }

    public boolean[] getHitArray() {
        return this.hit;
    }

    public abstract String getShipType();

    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        Ship[][] map = ocean.getShipArray();
        int rightBound = horizontal ? column + this.length - 1 : column;
        int lowerBound = horizontal ? row : row + this.length - 1;
        boolean isCorrectPlacement = row >= 0 && lowerBound < map.length && column >= 0 && rightBound < map[row].length;
        if (isCorrectPlacement) {
            for(int i = row - 1; i <= lowerBound + 1; ++i) {
                for(int j = column - 1; j <= rightBound + 1; ++j) {
                    if (ocean.isOccupied(i, j)) {
                        return false;
                    }
                }
            }
        }

        return isCorrectPlacement;
    }

    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        Ship[][] map = ocean.getShipArray();
        this.horizontal = horizontal;
        this.bowRow = row;
        this.bowColumn = column;
        int j;
        if (this.horizontal) {
            for(j = this.bowColumn; j < this.bowColumn + this.length; ++j) {
                map[this.bowRow][j] = this;
            }
        } else {
            for(j = this.bowRow; j < this.bowRow + this.length; ++j) {
                map[j][this.bowColumn] = this;
            }
        }

    }

    public boolean shotAt(int row, int column) {
        if (this.intersect(row, column)) {
            int indexOfPurpose = this.horizontal ? column - this.bowColumn : row - this.bowRow;
            this.hit[indexOfPurpose] = true;
        }

        return !this.isSunk() && this.intersect(row, column);
    }

    private boolean intersect(int row, int column) {
        return this.horizontal ? row == this.bowRow && column >= this.bowColumn && column < this.bowColumn + this.length : row >= this.bowRow && row < this.bowRow + this.length && column == this.bowColumn;
    }

    public boolean isSunk() {
        boolean[] var1 = this.hit;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            boolean part = var1[var3];
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
