package battleship.gamemods;

import battleship.player.Player;

import java.util.List;

public abstract class GameSession<P extends Player> {
    protected List<P> players;

    public abstract void bindPlayer(P player);
    /* for some initializations e.g:
        1) establishing remote connection
        2) preparing ocean
    */
    public abstract void initSession();
    public abstract void play();
    public abstract void stop();
}
