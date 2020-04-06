package battleship.player;

import battleship.Ocean;

import java.util.LinkedList;
import java.util.List;

public abstract class Player {
    private List<PlayerResponseHandler> handlers = new LinkedList<>();

    public final void addOnPlayerResponseHandler(PlayerResponseHandler handler) {
        handlers.add(handler);
    }

    public abstract void sendBundle(Bundle bundle);

    protected final void sendResponse(Bundle bundle) {
        for (var handler : handlers) {
            handler.handle(bundle);
        }
    }
}
