package state;

import game.color;
import server.player;

public interface gameState {

    /**
     * Goes to the next state
     */
    void goNext();

    /**
     * Sets next state
     * @param next state
     */
    void setNext(gameState next);

    /**
     * @return player of this state
     */
    player getPlayer();

    /**
     * @return color of player of this state
     */
    color getColor();

    /**
     * Sets player of this state to null
     */
    void clearPlayer();
}
