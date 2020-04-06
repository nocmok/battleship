package battleship.game;

public class EmptySea extends Ship {
    public EmptySea() {
        this.hit = new boolean[this.length = 1];
    }

    public String getShipType() {
        return "emptysea";
    }

    public String toString() {
        return this.hit[0] ? "-" : ".";
    }

    public boolean isSunk() {
        return false;
    }

    public boolean shotAt(int row, int column) {
        if (!this.isSunk() && row == this.bowRow && column == this.bowColumn) {
            this.hit[0] = true;
        }

        return false;
    }
}
