package borad.field;

import game.color;

import java.util.List;

/**
 * interface of borad.field.field
 */
public interface field {

    /**
     * sets col of borad.field.field
     * @param column col
     */
    void setColumn(int column);

    /**
     * sets the row of borad.field.field
     * @param row row
     */
    void setRow(int row);

    /**
     * @param edge true if this borad.field.field is an edge
     */
    void setEdge(boolean edge);

    /**
     * Adds list of neighbors
     * @param neighbors list of neighbors
     */
    void setNeighbors(List<field> neighbors);

    /**
     * Adds one neighbor to the list of neighbors
     * @param newNeighbor new neighbor
     */
    void addNeighbor(field newNeighbor);

    /**
     * @return column number of borad.field.field
     */
    int getColumn();

    /**
     * @return row number of borad.field.field
     */
    int getRow();

    /**
     * @return list of neighbors of borad.field.field
     */
    List<field> getNeighbors();

    /**
     * @return true if this borad.field.field is an edge
     */
    boolean isEdge();

    /**
     * @param someField borad.field.field to check
     * @return true if this borad.field.field and someField are neighbors
     */
    boolean isNeighbor(field someField);

    /**
     * Check if borad.field.field is in the List
     * @param fieldToCheck borad.field.field
     * @param fieldsList list of fields
     * @return true if List contains this borad.field.field
     */
    static boolean exists (field fieldToCheck, List<field> fieldsList) {
        boolean exists = false;

        for(field cField: fieldsList) {
            if(cField.getColumn() == fieldToCheck.getColumn() && cField.getRow() == fieldToCheck.getRow()) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * Checks if two fields are neighbors are neighbors.
     * Notice that borad.field.field is not neighbor to itself
     * @param f1 borad.field.field
     * @param f2 borad.field.field
     * @return true if f1 and f2 are neighbors
     */
    static boolean areNeighbors(field f1, field f2) {
        return field.exists(f2, f1.getNeighbors());
    }

    color setColor(color col);
}
