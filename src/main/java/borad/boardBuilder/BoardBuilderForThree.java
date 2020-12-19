package borad.boardBuilder;

import game.CustomColor;

/**
 * The type Board builder for three.
 */
public class BoardBuilderForThree extends AbstractBoardBuilder {
    /**
     * This method sets all pawns.
     */
    public void setPawns() {

        setPlayersPawns(getBoard().getFields().get(4), CustomColor.RED, 4);
        setPlayersPawns(getBoard().getFields().get(0), CustomColor.BLUE, 4);
        setPlayersPawns(getBoard().getFields().get(2), CustomColor.YELLOW, 4);
    }
    /**
     * This method sets all triangle's colors.
     */
    public void setTriangles() {

        addToTriangle(getBoard().getFields().get(0), CustomColor.RED, 4);
        addToTriangle(getBoard().getFields().get(2), CustomColor.BLUE, 4);
        addToTriangle(getBoard().getFields().get(4), CustomColor.YELLOW, 4);

    }
}
