package battleship.game;

public class Destroyer extends Ship {
    public Destroyer() {
        this.hit = new boolean[this.length = 2];
    }

    public String getShipType() {
        return "destroyer";
    }
}
