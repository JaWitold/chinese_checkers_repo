package state;

import game.color;
import server.player;

/**
 * class round
 */
public class playersRound implements gameState {
    private player player;
    private gameState nextRound;

    public playersRound(player newPlayer) {
        player = newPlayer;
    }
    @Override
    public void goNext() {
        if(nextRound.getPlayer() == null) {
            nextRound.goNext();
        }
    }

    @Override
    public void setNext(gameState next) {
        nextRound = next;
    }

    @Override
    public player getPlayer() {
        return player;
    }

    @Override
    public color getColor() {
        return player.getColor();
    }

    @Override
    public void clearPlayer() {
        player = null;
    }
}
