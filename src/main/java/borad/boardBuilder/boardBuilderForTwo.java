package borad.boardBuilder;

import game.color;

public class boardBuilderForTwo extends boardBuilder {
    /**
     * This method sets all pawns
     */
    public void setPawns() {
        //TODO: write this staff
    }
    /**
     * This method sets all triangle's colors
     */
    public void setTriangles() {
        //TODO: check get(i) numbers, if they are correct
        addToTriangle(gameBoard.getField().get(0), color.RED, 4);
        addToTriangle(gameBoard.getField().get(1), color.BLUE, 4);
    }
}