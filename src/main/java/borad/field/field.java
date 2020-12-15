package borad.field;

import game.color;
import game.pawn;

import java.util.List;

/**
 * Interface of field
 */
public interface field {

    /**
     * Sets col of field
     * @param column col
     */
    void setColumn(int column);

    /**
     * Sets the row of field
     * @param row row
     */
    void setRow(int row);

    /**
     * @param edge true if this field is an edge
     */
    void setEdge(boolean edge);

    /**
     * Adds list of neighbors
     * @param fields list of neighbors
     */
    void setNeighbors(List<field> fields);

    /**
     * Adds one neighbor to the list of neighbors
     * @param newNeighbor new neighbor
     */
    void addNeighbor(field newNeighbor);

    /**
     * @return column number of field
     */
    int getColumn();

    /**
     * @return row number of field
     */
    int getRow();

    /**
     * @return list of neighbors of field
     */
    List<field> getNeighbors();

    /**
     * @return true if this field is an edge
     */
    boolean isEdge();

    /**
     * @param someField field to check
     * @return true if this field and someField are neighbors
     */
    boolean isNeighbor(field someField);

    /**
     * Check if field is in the List
     * @param fieldToCheck field
     * @param fieldsList list of fields
     * @return true if List contains this field
     */
    static boolean exists (field fieldToCheck, List<field> fieldsList) {
        return field.exists(fieldToCheck.getColumn(), fieldToCheck.getRow(), fieldsList);
    }

    /**
     * Check if field is in the List
     * @param column column
     * @param row row
     * @param fieldsList list of fields
     * @return true if List contains this field
     */
    static boolean exists (int column, int row, List<field> fieldsList) {
        boolean exists = false;

        for(field cField: fieldsList) {
            if(cField.getColumn() == column && cField.getRow() == row) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * @param fieldToFind field
     * @param fieldsList List of fields
     * @return field of the same coordinates as fieldTofind
     */
    static field getField (field fieldToFind, List<field> fieldsList) {
        return field.getField(fieldToFind.getColumn(), fieldToFind.getRow(), fieldsList);
    }

    /**
     * @param column column
     * @param row row
     * @param fieldsList List of fields
     * @return field of given coordinates
     */
    static field getField (int column, int row, List<field> fieldsList) {
        field tmp = null;
        if (field.exists(column, row, fieldsList)){
            for (field cField : fieldsList) {
                if(cField.getColumn() == column && cField.getRow() == row) {
                    tmp = cField;
                    break;
                }
            }
        }
        return tmp;
    }

    /**
     * Checks if two fields are neighbors are neighbors.
     * Notice that field is not neighbor to itself
     * @param f1 field
     * @param f2 field
     * @return true if f1 and f2 are neighbors
     */
    static boolean areNeighbors(field f1, field f2) {
        return field.exists(f2, f1.getNeighbors());
    }

    /**
     * Set color of the field
     * @param col color
     */
    void setColor(color col);

    /**
     * Get color of the field
     * @return color of the field
     */
    color getColor();

    /**
     * Set pawn on the field
     * @param newPawn pawn
     */
    void setPawn(pawn newPawn);

    /**
     * Get pawn on the field
     */
    pawn getPawn();
}
