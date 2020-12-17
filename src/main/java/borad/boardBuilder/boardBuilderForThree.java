package borad.boardBuilder;

import game.color;

public class boardBuilderForThree extends boardBuilder {
    /**
     * This method sets all pawns
     */
    public void setPawns() {

        setPlayersPawns(gameBoard.getFields().get(4), color.RED, 4);
        setPlayersPawns(gameBoard.getFields().get(0), color.BLUE, 4);
        setPlayersPawns(gameBoard.getFields().get(2), color.YELLOW, 4);
    }
    /**
     * This method sets all triangle's colors
     */
    public void setTriangles() {

        addToTriangle(gameBoard.getFields().get(0), color.RED, 4);
        addToTriangle(gameBoard.getFields().get(2), color.BLUE, 4);
        addToTriangle(gameBoard.getFields().get(4), color.YELLOW, 4);

    }
}
