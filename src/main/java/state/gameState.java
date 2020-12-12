package state;

import server.player;

public interface gameState {
    void goNext();
    void setNext(gameState next);
    player getPlayer();
    void clearPlayer();
}
