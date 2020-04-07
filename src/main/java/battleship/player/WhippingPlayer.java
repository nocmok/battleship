package battleship.player;

import battleship.game.Ocean;
import battleship.game.Ship;

public class WhippingPlayer extends Player {
    public static final int ROW = 2;
    public static final int COLUMN = 3;

    public static final int STATUS = 1;
    public static final int MISS = 4;
    public static final int HIT = 5;
    public static final int SUNK = 6;

    private Ocean ocean = new Ocean();

    public void WhippingPlayer() {
        ocean.placeAllShipsRandomly();
    }

    @Override
    public void sendBundle(Bundle bundle) {
        int row = bundle.getInteger(ROW);
        int col = bundle.getInteger(COLUMN);
        Ship ship = ocean.getShipArray()[row][col];
        boolean success = ocean.shotAt(row, col);
        int status = MISS;
        if (success && ship.isSunk()) {
            status = SUNK;
        } else if (success) {
            status = HIT;
        }
        Bundle resp = new Bundle();
        resp.putInteger(ROW, row);
        resp.putInteger(COLUMN, col);
        resp.putInteger(STATUS, status);
        sendResponse(resp);
    }

    public Ocean getOcean(){
        return ocean;
    }
}
