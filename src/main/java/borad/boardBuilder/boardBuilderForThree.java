package borad.boardBuilder;

import game.color;

public class boardBuilderForThree extends boardBuilder {
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
        addToTriangle(gameBoard.getFields().get(2), color.BLUE, 4); //tested//
        addToTriangle(gameBoard.getFields().get(4), color.YELLOW, 4);

    }
}
