package battleship.game;

public class Battleship extends Ship {
    public Battleship() {
        this.hit = new boolean[this.length = 4];
    }

    public String getShipType() {
        return "battleship";
    }
}
