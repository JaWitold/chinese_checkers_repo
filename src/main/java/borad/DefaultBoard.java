package borad;

import borad.field.FieldInterface;
import game.DefaultPawn;

import java.util.ArrayList;
import java.util.List;

/**
 * Board class.
 */
public class DefaultBoard implements BoardInterface {
    /**
     * The Fields list.
     */
    private final List<FieldInterface> fieldsList;
    /**
     * The Pawns list.
     */
    private final List<DefaultPawn> pawnsList;

    /**
     * Instantiates a new Default Game BoardInterface.
     */
    public DefaultBoard() {
        fieldsList = new ArrayList<>();
        pawnsList = new ArrayList<>();
    }

    /**
     * @return list of FieldInterface
     */
    @Override
    public List<FieldInterface> getFields() {
        return fieldsList;
    }

    /**
     * @return list of pawns
     */
    @Override
    public List<DefaultPawn> getPawns() {
        return pawnsList;
    }

    /**
     * Moves DefaultPawn to destination FieldInterface.
     * @param currentDefaultPawn DefaultPawn to move
     * @param destination FieldInterface
     */
    @Override
    public void movePawn(
            final DefaultPawn currentDefaultPawn,
            final FieldInterface destination) {
        currentDefaultPawn.getField().setPawn(null);
        currentDefaultPawn.setField(destination);
        currentDefaultPawn.getField().setPawn(currentDefaultPawn);
    }

    /**
     * Add new Pawn to BoardInterface.
     * @param newDefaultPawn DefaultPawn
     */
    @Override
    public void addPawn(final DefaultPawn newDefaultPawn) {
        pawnsList.add(newDefaultPawn);
    }


}
