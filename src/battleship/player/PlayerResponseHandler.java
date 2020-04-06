package battleship.player;

@FunctionalInterface
public interface PlayerResponseHandler {
    void handle(Bundle bundle);
}
