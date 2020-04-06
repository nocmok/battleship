package battleship.gamemods;

import battleship.player.Bundle;
import battleship.player.WhippingPlayer;

public class TrainingGame extends GameSession<WhippingPlayer> {
    @Override
    public void bindPlayer(WhippingPlayer player) {
        if(players.size() > 0){
            throw new RuntimeException("attempt to add extra player to training game");
        }
        player.addOnPlayerResponseHandler((bundle) -> onPlayerResponse(bundle));
        players.add(player);
    }

    private void onPlayerResponse(Bundle bundle){

    }

    @Override
    public void initSession() {

    }

    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }
}
