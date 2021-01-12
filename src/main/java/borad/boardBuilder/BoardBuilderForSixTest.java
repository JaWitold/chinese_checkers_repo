package borad.boardBuilder;

import borad.BoardInterface;
import borad.field.DefaultField;
import game.CustomColor;
import org.junit.Test;


public class BoardBuilderForSixTest {
  
    BoardInterface gameBoard;
    BoardBuilderForSix boardBuilderForSix;

    @Test
    public void testAddToTriangle() throws Exception {
        boardBuilderForSix.addToTriangle(new DefaultField(0, 0, true), CustomColor.RED, 0);
    }

    @Test
    public void testSetPlayersPawns() throws Exception {
        boardBuilderForSix.setPlayersPawns(new DefaultField(0, 0, true), CustomColor.RED, 0);
    }
}