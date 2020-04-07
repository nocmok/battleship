package battleship.gamemods;

import battleship.game.Ocean;
import battleship.player.WhippingPlayer;

public class TrainingSession extends GameSession<WhippingPlayer> {

    public TrainingSession() {

    }

    @Override
    public void addPlayer(WhippingPlayer player) {
        if (players.size() > 0) {
            throw new RuntimeException("attempt to add extra player to training game");
        }
        players.add(player);
    }

    @Override
    public void initSession() {
        players.get(0).getOcean().placeAllShipsRandomly();
    }

    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }
}
