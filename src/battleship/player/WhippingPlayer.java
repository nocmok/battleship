package battleship.player;

import battleship.EmptySea;
import battleship.Ocean;
import battleship.Ship;

public class WhippingPlayer extends Player {
    public static final int RESPONSE = 1;
    public static final int ROW = 2;
    public static final int COLUMN = 3;
    public static final int MISS = 4;
    public static final int HIT = 5;
    public static final int SUNK = 6;

    private Ocean ocean = new Ocean();
    public void WhippingPlayer(){
    }
    @Override
    public void sendBundle(Bundle bundle) {
        int row = bundle.getInteger(ROW);
        int col = bundle.getInteger(COLUMN);
        int status = MISS;
        Ship ship = ocean.getShipArray()[row][col];
        ocean.shotAt(row, col);
        Bundle response = new Bundle();
        response.putInteger(RESPONSE, status);
        sendResponse(response);
    }
}
