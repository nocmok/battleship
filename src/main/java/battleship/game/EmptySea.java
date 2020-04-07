package battleship.game;

public class EmptySea extends Ship {
    public EmptySea() {
        this.hit = new boolean[this.length = 1];
    }

    public int getShipType() {
        return EMPTY_SEA;
    }

    public String toString() {
        return this.hit[0] ? "-" : ".";
    }

    public boolean isSunk() {
        return false;
    }

    public boolean shotAt(int row, int column) {
        this.hit[0] = true;
        return false;
    }
}
