package borad.field;

import game.CustomColor;
import game.DefaultPawn;

import java.util.List;

/**
 * Interface of FieldInterface.
 */
public interface FieldInterface {

    /**
     * Sets col of FieldInterface.
     *
     * @param column col
     */
    void setColumn(int column);

    /**
     * Sets the row of FieldInterface.
     *
     * @param row row
     */
    void setRow(int row);

    /**
     * Sets edge.
     *
     * @param edge true if this FieldInterface is an edge
     */
    void setEdge(boolean edge);

    /**
     * Adds list of neighbors.
     *
     * @param fields list of neighbors
     */
    void setNeighbors(List<FieldInterface> fields);

    /**
     * Adds one neighbor to the list of neighbors.
     *
     * @param newNeighbor new neighbor
     */
    void addNeighbor(FieldInterface newNeighbor);

    /**
     * Gets column.
     *
     * @return column number of FieldInterface
     */
    int getColumn();

    /**
     * Gets row.
     *
     * @return row number of FieldInterface
     */
    int getRow();

    /**
     * Gets neighbors.
     *
     * @return list of neighbors of FieldInterface
     */
    List<FieldInterface> getNeighbors();

    /**
     * Is edge boolean.
     *
     * @return true if this FieldInterface is an edge
     */
    boolean isEdge();

    /**
     * Is neighbor boolean.
     *
     * @param someField FieldInterface to check
     * @return true if this FieldInterface and someField are neighbors
     */
    boolean isNeighbor(FieldInterface someField);

    /**
     * Check if FieldInterface is in the List.
     *
     * @param fieldToCheck FieldInterface
     * @param fieldsList   list of fields
     * @return true if List contains this FieldInterface
     */
    static boolean exists(FieldInterface fieldToCheck,
                           List<FieldInterface> fieldsList) {
        return FieldInterface.exists(
                fieldToCheck.getColumn(),
                fieldToCheck.getRow(),
                fieldsList);
    }

    /**
     * Check if FieldInterface is in the List.
     *
     * @param column     column
     * @param row        row
     * @param fieldsList list of fields
     * @return true if List contains this FieldInterface
     */
    static boolean exists(
            final int column,
            final int row,
            final List<FieldInterface> fieldsList) {
        boolean exists = false;

        for (FieldInterface cField: fieldsList) {
            if (cField.getColumn() == column && cField.getRow() == row) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * Gets FieldInterface.
     *
     * @param fieldToFind FieldInterface
     * @param fieldsList  List of fields
     * @return FieldInterface of the same coordinates as fieldTofind
     */
    static FieldInterface getField(
            final FieldInterface fieldToFind,
            final List<FieldInterface> fieldsList) {
        return FieldInterface.getField(
                fieldToFind.getColumn(),
                fieldToFind.getRow(),
                fieldsList);
    }

    /**
     * Gets FieldInterface.
     *
     * @param column     column
     * @param row        row
     * @param fieldsList List of fields
     * @return FieldInterface of given coordinates
     */
    static FieldInterface getField(
            final int column,
            final int row,
            final List<FieldInterface> fieldsList) {
        FieldInterface tmp = null;
        if (FieldInterface.exists(column, row, fieldsList)) {
            for (FieldInterface cField : fieldsList) {
                if (cField.getColumn() == column && cField.getRow() == row) {
                    tmp = cField;
                    break;
                }
            }
        }
        return tmp;
    }

    /**
     * Checks if two fields are neighbors are neighbors.
     * Notice that FieldInterface is not neighbor to itself
     *
     * @param f1 FieldInterface
     * @param f2 FieldInterface
     * @return true if f1 and f2 are neighbors
     */
    static boolean areNeighbors(
            final FieldInterface f1,
            final FieldInterface f2) {
        return FieldInterface.exists(f2, f1.getNeighbors());
    }

    /**
     * Set CustomColor of the FieldInterface.
     *
     * @param col CustomColor
     */
    void setColor(CustomColor col);

    /**
     * Get CustomColor of the FieldInterface.
     *
     * @return CustomColor of the FieldInterface
     */
    CustomColor getColor();

    /**
     * Set DefaultPawn on the FieldInterface.
     *
     * @param newDefaultPawn DefaultPawn
     */
    void setPawn(DefaultPawn newDefaultPawn);

    /**
     * Get DefaultPawn on the FieldInterface.
     *
     * @return the DefaultPawn
     */
    DefaultPawn getPawn();
}
