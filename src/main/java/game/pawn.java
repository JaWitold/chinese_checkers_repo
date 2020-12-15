package game;

import borad.field.field;

import java.util.List;

public class pawn {
    private field myField;
    private color myColor;

    /**
     * @param newColor color of the pawn
     * @param newField field where pawn stands
     */
    public pawn (color newColor, field newField) {
        myColor = newColor;
        myField = newField;
    }

    /**
     * Getter for field
     * @return field
     */
    public field getField() {
        return myField;
    }

    /**
     * Setter for field
     */
    public void setField(field myField) {
        this.myField = myField;
    }

    /**
     * Getter for color
     * @return color
     */
    public color getColor() {
        return myColor;
    }

    /**
     * Setter for color
     */
    public void setColor(color myColor) {
        this.myColor = myColor;
    }

    /**
     * Checks if the pawn exists in given List
     * @param pawnToCheck pawn to check
     * @param pawnsList List to check
     * @return true if pawn is on the list
     */
    public static boolean exists(pawn pawnToCheck, List<pawn> pawnsList) {
        boolean exists = false;

        for(pawn cPawn: pawnsList) {
            if(cPawn.getColumn() == pawnToCheck.getColumn() && cPawn.getRow() == pawnToCheck.getRow()) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * Returns pawn of the same coordinates as pawnToCheck
     * @param pawnToCheck pawn to check
     * @param pawnsList List of pawns
     * @return
     */
    public static pawn getPawn(pawn pawnToCheck, List<pawn> pawnsList) {
        return getPawn(pawnToCheck.getColumn(), pawnToCheck.getRow(), pawnsList);
    }
    /**
     * Returns pawn of the same coordinates as pawnToCheck
     * @param column column
     * @param row row
     * @param pawnsList List of pawns
     * @return
     */
    public static pawn getPawn(int column, int row, List<pawn> pawnsList) {
        pawn tmp = null;
        for(pawn cPawn: pawnsList) {
            if(cPawn.getColumn() == column && cPawn.getRow() == row) {
                tmp = cPawn;
                break;
            }
        }
        return tmp;
    }

    /**
     * Getter of column
     * @return column
     */
    public int getColumn() {
        return myField.getColumn();
    }

    /**
     * Getter of row
     * @return row
     */
    public int getRow() {
        return myField.getRow();
    }
}
