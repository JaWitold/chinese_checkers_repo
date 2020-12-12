package borad;

import borad.field.field;
import game.pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * board class
 */
public class defaultGameBoard implements board {
    protected List<field> fieldsList;
    protected List<pawn> pawnsList;

    public defaultGameBoard(){
        fieldsList = new ArrayList<>();
        pawnsList = new ArrayList<>();
    }

    /**
     * @return list of field
     */
    @Override
    public List<field> getField() {
        return fieldsList;
    }

    /**
     * @return list of pawns
     */
    @Override
    public List<pawn> getPawns() {
        return pawnsList;
    }
}
