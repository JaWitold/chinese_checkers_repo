package state;

import game.color;
import server.player;

/**
 * Class round
 */
public class playersRound implements gameState {
    private player player;
    private gameState nextRound;

    public playersRound(player newPlayer) {
        player = newPlayer;
    }

    /**
     * Goes to the next state
     */
    @Override
    public void goNext() {
        if(nextRound.getPlayer() != null) {
            nextRound.goNext();
        }
    }

    /**
     * Sets next state
     * @param next state
     */
    @Override
    public void setNext(gameState next) {
        nextRound = next;
    }

    /**
     * @return player of this state
     */
    @Override
    public player getPlayer() {
        return player;
    }

    /**
     * @return color of player of this state
     */
    @Override
    public color getColor() {
        return player.getColor();
    }

    /**
     * Sets player of this state to null
     */
    @Override
    public void clearPlayer() {
        player = null;
    }
}
