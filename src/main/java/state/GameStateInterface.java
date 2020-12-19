package state;

import game.CustomColor;
import server.Player;

/**
 * The interface Game state.
 */
public interface GameStateInterface {

    /**
     * Goes to the next state.
     *
     * @return the Game state
     */
    GameStateInterface goNext();

    /**
     * Sets next state.
     *
     * @param next state
     */
    void setNext(GameStateInterface next);

    /**
     * Gets Player.
     *
     * @return Player of this state
     */
    Player getPlayer();

    /**
     * Gets CustomColor.
     *
     * @return CustomColor of Player of this state
     */
    CustomColor getColor();

    /**
     * Sets Player of this state to null.
     */
    void clearPlayer();
}
