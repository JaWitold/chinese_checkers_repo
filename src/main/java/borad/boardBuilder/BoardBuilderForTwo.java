package borad.boardBuilder;

import game.CustomColor;

/**
 * The type Board builder for two.
 */
public class BoardBuilderForTwo extends AbstractBoardBuilder {
    /**
     * This method sets all pawns.
     */
    public void setPawns() {

        setPlayersPawns(getBoard().getFields().get(3), CustomColor.RED, 4);
        setPlayersPawns(getBoard().getFields().get(0), CustomColor.BLUE, 4);

    }
    /**
     * This method sets all triangle's colors.
     */
    public void setTriangles() {

        addToTriangle(getBoard().getFields().get(0), CustomColor.RED, 4);
        addToTriangle(getBoard().getFields().get(3), CustomColor.BLUE, 4);
    }
}
