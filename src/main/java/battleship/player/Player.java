package battleship.player;

import java.util.LinkedList;
import java.util.List;

public abstract class Player {
    public static final int ROW = 2;
    public static final int COLUMN = 3;
    public static final int STATUS = 1;


    private List<PlayerResponseHandler> handlers = new LinkedList<>();

    public final void addOnPlayerResponseHandler(PlayerResponseHandler handler) {
        handlers.add(handler);
    }

    public abstract void sendBundle(Bundle bundle);

    protected final void sendResponse(Bundle bundle) {
        for (var handler : handlers) {
            handler.handle(this, bundle);
        }
    }

    @FunctionalInterface
    public interface PlayerResponseHandler {
        void handle(Player player, Bundle bundle);
    }
}
