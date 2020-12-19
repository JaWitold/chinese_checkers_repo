package game;

import borad.board;
import borad.field.field;

public interface Rules {

    boolean isItPossibleMove(field start, field finnish);
    boolean hasWon(color playersColor);
}
