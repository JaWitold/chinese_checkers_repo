package borad.boardBuilder;

import borad.BoardInterface;
import borad.field.DefaultField;
import game.CustomColor;
import org.junit.Test;

public class AbstractBoardBuilderTest {

    BoardInterface gameBoard;

    AbstractBoardBuilder abstractBoardBuilder;

    @Test
    public void testBuildNewBoard() throws Exception {
        abstractBoardBuilder.buildNewBoard();
    }


    @Test
    public void testAddToTriangle() throws Exception {
        abstractBoardBuilder.addToTriangle(new DefaultField(0, 0, true), CustomColor.RED, 0);
    }

    @Test
    public void testSetPlayersPawns() throws Exception {
        abstractBoardBuilder.setPlayersPawns(new DefaultField(0, 0, true), CustomColor.RED, 0);
    }
}
