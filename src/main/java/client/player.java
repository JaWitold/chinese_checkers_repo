package client;

import borad.board;
import game.*;

public class player {
    private color myColor;
    private client myConnection;
    private color currentRound;
    private board myBoard;

    public player(color myNewColor, client client) {
        myColor = myNewColor;
        myConnection = client;
    }

    public void setBoard(board newBoard) {
        myBoard = newBoard;
    }
    public board getBoard() { return myBoard; }
    public color getColor() {
        return myColor;
    }
}
