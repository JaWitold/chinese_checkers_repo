package borad;

import borad.field.field;
import game.pawn;

import java.util.List;

/**
 * Interface for a board of the game
 */
public interface board {
    /**
     * @return list of field
     */
    List<field> getFields();
    /**
     * @return list of pawns
     */
    List<pawn> getPawns();
}