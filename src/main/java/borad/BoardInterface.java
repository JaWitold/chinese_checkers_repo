package borad;

import borad.field.FieldInterface;
import game.DefaultPawn;

import java.util.List;

/**
 * Interface for a BoardInterface of the Game.
 */
public interface BoardInterface {
    /**
     * Gets fields.
     *
     * @return list of FieldInterface
     */
    List<FieldInterface> getFields();

    /**
     * Gets pawns.
     *
     * @return list of pawns
     */
    List<DefaultPawn> getPawns();

    /**
     * Moves DefaultPawn to destination FieldInterface.
     *
     * @param currentDefaultPawn DefaultPawn to move
     * @param destination FieldInterface
     */
    void movePawn(DefaultPawn currentDefaultPawn, FieldInterface destination);

    /**
     * Adds Pawn to gameboard.
     *
     * @param newDefaultPawn DefaultPawn
     */
    void addPawn(DefaultPawn newDefaultPawn);
}
