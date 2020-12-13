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

    /**
     * Moves pawn to destination field
     * @param currentPawn pawn to move
     * @param destination field
     * @return true if movement was successful, false if this move was wrong
     */
    public boolean movePawn(pawn currentPawn, field destination);

    void addPawn(pawn tmp);
}