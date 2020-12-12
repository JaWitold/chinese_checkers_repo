package project.borad;

import project.borad.field.field;
import project.pawn;

import java.util.List;

/**
 * Interface for a board of the game
 */
public interface board {
    /**
     * @return list of field
     */
    List<field> getField();
    /**
     * @return list of pawns
     */
    List<pawn> getPawns();
}