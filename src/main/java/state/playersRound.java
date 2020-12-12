package state;

import server.player;

public class playersRound implements gameState {
    private player player;
    private playersRound nextRound;

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

    }

    @Override
    public player getPlayer() {
        return player;
    }

    @Override
    public void clearPlayer() {
        player = null;
    }
}
