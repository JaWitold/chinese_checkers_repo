package state;

import game.CustomColor;
import server.Player;

/**
 * Class round.
 */
public class DefaultGameState implements GameStateInterface {
    /**
     * Player.
     */
    private Player player;
    /**
     * Next Round.
     */
    private GameStateInterface nextRound;

    /**
     * Instantiates a new Players round.
     *
     * @param newPlayer the new Player
     */
    public DefaultGameState(final Player newPlayer) {
        player = newPlayer;
    }

    /**
     * Go to next state.
     * @return next state
     */
    @Override
    public GameStateInterface goNext() {
       return nextRound;
    }

    /**
     * Sets next state.
     * @param next state
     */
    @Override
    public void setNext(final GameStateInterface next) {
        nextRound = next;
    }

    /**
     * @return Player of this state
     */
    @Override
    public Player getPlayer() {
        return player;
    }

    /**
     * @return CustomColor of Player of this state
     */
    @Override
    public CustomColor getColor() {
        return player.getColor();
    }

    /**
     * Sets Player of this state to null.
     */
    @Override
    public void clearPlayer() {
        player = null;
    }
}
