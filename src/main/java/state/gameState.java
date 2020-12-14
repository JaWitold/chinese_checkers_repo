package state;

import game.color;
import server.player;

public interface gameState {
    void goNext();
    void setNext(gameState next);
    player getPlayer();
    color getColor();
    void clearPlayer();
}
