package borad;

import borad.field.field;
import game.pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * board class
 */
public class defaultGameBoard implements board {
    /**
     * The Fields list.
     */
    protected List<field> fieldsList;
    /**
     * The Pawns list.
     */
    protected List<pawn> pawnsList;

    /**
     * Instantiates a new Default game board.
     */
    public defaultGameBoard(){
        fieldsList = new ArrayList<>();
        pawnsList = new ArrayList<>();
    }

    /**
     * @return list of field
     */
    @Override
    public List<field> getFields() {
        return fieldsList;
    }

    /**
     * @return list of pawns
     */
    @Override
    public List<pawn> getPawns() {
        return pawnsList;
    }

    /**
     * Moves pawn to destination field
     * @param currentPawn pawn to move
     * @param destination field
     * @return true if movement was successful, false if this move was wrong
     */
    @Override
    public void movePawn(pawn currentPawn, field destination) {
        currentPawn.getField().setPawn(null);
        currentPawn.setField(destination);
        currentPawn.getField().setPawn(currentPawn);
    }

    @Override
    public void addPawn(pawn tmp) {
        pawnsList.add(tmp);
    }


}
