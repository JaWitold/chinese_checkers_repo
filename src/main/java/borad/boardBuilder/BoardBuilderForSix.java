package borad.boardBuilder;

import game.color;

public class boardBuilderForSix extends boardBuilder {
    /**
     * This method sets all pawns
     */
    public void setPawns() {
        //TODO: write this staff
        setPlayersPawns(gameBoard.getFields().get(0), color.RED, 4);
        setPlayersPawns(gameBoard.getFields().get(1), color.BLUE, 4);
        setPlayersPawns(gameBoard.getFields().get(2), color.YELLOW, 4);
        setPlayersPawns(gameBoard.getFields().get(3), color.GREEN, 4);
        setPlayersPawns(gameBoard.getFields().get(4), color.PURPLE, 4);
        setPlayersPawns(gameBoard.getFields().get(5), color.ORANGE, 4);

    }
    /**
     * This method sets all triangle's colors
     */
    public void setTriangles() {
        //TODO: check get(i) numbers, if they are correct
        addToTriangle(gameBoard.getFields().get(3), color.RED, 4);
        addToTriangle(gameBoard.getFields().get(4), color.BLUE, 4);
        addToTriangle(gameBoard.getFields().get(5), color.YELLOW, 4);
        addToTriangle(gameBoard.getFields().get(0), color.GREEN, 4);
        addToTriangle(gameBoard.getFields().get(1), color.PURPLE, 4);
        addToTriangle(gameBoard.getFields().get(2), color.ORANGE, 4);

    }
}