package game;

import borad.field.FieldInterface;

import java.util.List;

/**
 * The type Pawn.
 */
public class DefaultPawn {
    /**
     * Field of the pawn.
     */
    private FieldInterface myField;
    /**
     * Color of the pawn.
     */
    private final CustomColor myColor;

    /**
     * Instantiates a new Pawn.
     *
     * @param newColor CustomColor of the DefaultPawn
     * @param newField field where DefaultPawn stands
     */
    public DefaultPawn(
            final CustomColor newColor,
            final FieldInterface newField) {
        myColor = newColor;
        myField = newField;
    }

    /**
     * Getter for field.
     *
     * @return field
     */
    public FieldInterface getField() {
        return myField;
    }

    /**
     * Setter for field.
     *
     * @param newField the my field
     */
    public void setField(final FieldInterface newField) {
        myField = newField;
    }

    /**
     * Getter for CustomColor.
     *
     * @return CustomColor CustomColor
     */
    public CustomColor getColor() {
        return myColor;
    }
    /**
     * Checks if the DefaultPawn exists in given List.
     *
     * @param defaultPawnToCheck DefaultPawn to check
     * @param pawnsList   List to check
     * @return true if DefaultPawn is on the list
     */
    public static boolean exists(
            final DefaultPawn defaultPawnToCheck,
            final List<DefaultPawn> pawnsList) {
        boolean exists = false;

        for (DefaultPawn cDefaultPawn : pawnsList) {
            if (cDefaultPawn.getColumn() == defaultPawnToCheck.getColumn()
                    && cDefaultPawn.getRow() == defaultPawnToCheck.getRow()) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * Returns DefaultPawn of the same coordinates as defaultPawnToCheck.
     *
     * @param defaultPawnToCheck DefaultPawn to check
     * @param pawnsList   List of pawns
     * @return DefaultPawn DefaultPawn
     */
    public static DefaultPawn getPawn(
            final DefaultPawn defaultPawnToCheck,
            final List<DefaultPawn> pawnsList) {
        return getPawn(
                defaultPawnToCheck.getColumn(),
                defaultPawnToCheck.getRow(),
                pawnsList);
    }

    /**
     * Returns DefaultPawn of the same coordinates as pawnToCheck.
     *
     * @param column    column
     * @param row       row
     * @param pawnsList List of pawns
     * @return DefaultPawn DefaultPawn
     */
    public static DefaultPawn getPawn(
            final int column,
            final int row,
            final List<DefaultPawn> pawnsList) {
        DefaultPawn tmp = null;
        for (DefaultPawn cDefaultPawn : pawnsList) {
            if (cDefaultPawn.getColumn() == column
                    && cDefaultPawn.getRow() == row) {
                tmp = cDefaultPawn;
                break;
            }
        }
        return tmp;
    }

    /**
     * Getter of column.
     *
     * @return column column
     */
    public int getColumn() {
        return myField.getColumn();
    }

    /**
     * Getter of row.
     *
     * @return row row
     */
    public int getRow() {
        return myField.getRow();
    }
}
