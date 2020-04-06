package battleship.player;

import battleship.Ocean;

public abstract class Player {
    private Ocean ocean;
    public abstract void addOnPlayerResponseHandler(PlayerResponseHandler handler);
}
