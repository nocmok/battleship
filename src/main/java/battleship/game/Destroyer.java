package battleship.game;

public class Destroyer extends Ship {
    public Destroyer() {
        this.hit = new boolean[this.length = 2];
    }

    public int getShipType() {
        return DESTROYER;
    }
}
