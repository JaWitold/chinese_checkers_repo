package game;

import borad.field.field;

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
}
