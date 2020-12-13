package game;

import borad.field.field;

import java.util.List;

public class pawn {
    private field myField;
    private color myColor;

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

    public int getColumn() {
        return myField.getColumn();
    }

    public int getRow() {
        return myField.getRow();
    }
}
