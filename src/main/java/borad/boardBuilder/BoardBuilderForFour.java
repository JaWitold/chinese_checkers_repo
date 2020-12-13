package borad.boardBuilder;

import game.color;

public class boardBuilderForFour extends boardBuilder{
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
        addToTriangle(gameBoard.getFields().get(0), color.RED, 4);
        addToTriangle(gameBoard.getFields().get(1), color.BLUE, 4);
        addToTriangle(gameBoard.getFields().get(3), color.YELLOW, 4);
        addToTriangle(gameBoard.getFields().get(4), color.GREEN, 4);

    }
}
