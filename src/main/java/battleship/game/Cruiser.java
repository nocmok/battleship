package battleship.game;

public class Cruiser extends Ship {
    public Cruiser() {
        this.hit = new boolean[this.length = 3];
    }

    public int getShipType() {
        return CRUISER;
    }
}