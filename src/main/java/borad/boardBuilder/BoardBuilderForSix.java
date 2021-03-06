package borad.boardBuilder;

import game.CustomColor;

/**
 * The type Board builder for six.
 */
public class BoardBuilderForSix extends AbstractBoardBuilder {
    /**
     * This method sets all pawns.
     */
    public void setPawns() {

        setPlayersPawns(getBoard().getFields().get(0), CustomColor.RED, 4);
        setPlayersPawns(getBoard().getFields().get(1), CustomColor.BLUE, 4);
        setPlayersPawns(getBoard().getFields().get(2), CustomColor.YELLOW, 4);
        setPlayersPawns(getBoard().getFields().get(3), CustomColor.GREEN, 4);
        setPlayersPawns(getBoard().getFields().get(4), CustomColor.PURPLE, 4);
        setPlayersPawns(getBoard().getFields().get(5), CustomColor.ORANGE, 4);

    }
    /**
     * This method sets all triangle's colors.
     */
    public void setTriangles() {

        addToTriangle(getBoard().getFields().get(3), CustomColor.RED, 4);
        addToTriangle(getBoard().getFields().get(4), CustomColor.BLUE, 4);
        addToTriangle(getBoard().getFields().get(5), CustomColor.YELLOW, 4);
        addToTriangle(getBoard().getFields().get(0), CustomColor.GREEN, 4);
        addToTriangle(getBoard().getFields().get(1), CustomColor.PURPLE, 4);
        addToTriangle(getBoard().getFields().get(2), CustomColor.ORANGE, 4);

    }
}
