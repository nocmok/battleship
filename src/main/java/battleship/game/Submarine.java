package battleship.game;

public class Submarine extends Ship {
    public Submarine() {
        this.hit = new boolean[this.length = 1];
    }

    public int getShipType() {
        return SUBMARINE;
    }
}
