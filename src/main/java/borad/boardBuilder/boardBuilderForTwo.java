package borad.boardBuilder;

import game.color;

public class boardBuilderForTwo extends boardBuilder {
    /**
     * This method sets all pawns
     */
    public void setPawns() {
        //TODO: write this staff
        setPlayersPawns(gameBoard.getFields().get(3), color.RED, 4);
        setPlayersPawns(gameBoard.getFields().get(0), color.BLUE, 4);

    }
    /**
     * This method sets all triangle's colors
     */
    public void setTriangles() {
        //TODO: check get(i) numbers, if they are correct
        addToTriangle(gameBoard.getFields().get(0), color.RED, 4);
        addToTriangle(gameBoard.getFields().get(3), color.BLUE, 4);
    }
}
