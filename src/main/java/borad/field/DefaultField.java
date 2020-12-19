package borad.field;

import game.CustomColor;
import game.DefaultPawn;

import java.util.ArrayList;
import java.util.List;

/**
 * this is one field of the BoardInterface.
 */
public class DefaultField implements FieldInterface {

    /**
     * The Column.
     */
    private int column;
    /**
     * The Row.
     */
    private int row;
    /**
     * The Edge.
     */
    private boolean edge;
    /**
     * The Neighbors.
     */
    private final List<FieldInterface> neighbors;
    /**
     * The Players CustomColor.
     */
    private CustomColor playersCustomColor;
    /**
     * The Players DefaultPawn.
     */
    private DefaultPawn playersDefaultPawn;

    /**
     * Short version of default constructor.
     *
     * @param newColumn of this field
     * @param newRow    of this field
     */
    public DefaultField(final int newColumn, final int newRow) {
        this(newColumn, newRow, false);
    }

    /**
     * Default constructor.
     *
     * @param newColumn of this Field
     * @param newRow    of this Field
     * @param newEdge   is this Field an edge
     */
    public DefaultField(
            final int newColumn,
            final int newRow,
            final boolean newEdge) {
        setColumn(newColumn);
        setRow(newRow);
        setEdge(newEdge);
        playersCustomColor = CustomColor.NULL;
        neighbors = new ArrayList<>();
        playersDefaultPawn = null;
    }

    /**
     * sets col of field.
     * @param newColumn col
     */
    @Override
    public void setColumn(final int newColumn) {
        this.column = newColumn;
    }

    /**
     * sets the row of field.
     * @param newRow row
     */
    @Override
    public void setRow(final int newRow) {
        this.row = newRow;
    }

    /**
     * Sets edge.
     * @param newEdge true if this field is an edge
     */
    @Override
    public void setEdge(final boolean newEdge) {
        this.edge = newEdge;
    }

    /**
     * Adds list of neighbors.
     * @param fields list of neighbors
     */
    @Override
    public void setNeighbors(final List<FieldInterface> fields) {
        final int[][] vectors = {
                {2, 0}, {1, -1}, {-1, -1},
                {-2, 0}, {-1, 1}, {1, 1}
        };
        for (int i = 0; i < 6; i++) {
            FieldInterface tmp = FieldInterface.getField(
                    new DefaultField(this.getColumn() + vectors[i][0],
                            this.getRow() + vectors[i][1]), fields);
            if (tmp != null) {
                this.addNeighbor(tmp);
            }
        }
    }

    /**
     * Adds one neighbor to the list of neighbors.
     * @param newNeighbor new neighbor
     */
    @Override
    public void addNeighbor(final FieldInterface newNeighbor) {
        if (this.neighbors.size() < 6) {
            this.neighbors.add(newNeighbor);
        }
    }

    /**
     * Gets column.
     * @return column number of field
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Gets Row.
     * @return row number of field
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Gets neighbors.
     * @return list of neighbors of field
     */
    @Override
    public List<FieldInterface> getNeighbors() {
        return this.neighbors;
    }

    /**
     * Checks if it is edge.
     * @return true if this field is an edge
     */
    @Override
    public boolean isEdge() {
        return edge;
    }

    /**
     * Check if it is neighbor.
     * @param someField field to check
     * @return true if this field and someField are neighbors
     */
    @Override
    public boolean isNeighbor(final FieldInterface someField) {
        return FieldInterface.areNeighbors(this, someField);
    }

    /**
     * Setter for CustomColor.
     * @param col CustomColor
     */
    @Override
    public void setColor(final CustomColor col) {
        playersCustomColor = col;
    }

    /**
     * Get CustomColor of the field.
     * @return CustomColor of the field
     */
    @Override
    public CustomColor getColor() {
        return playersCustomColor;
    }

    /**
     * set DefaultPawn on the field.
     *
     * @param newDefaultPawn DefaultPawn
     */
    @Override
    public void setPawn(final DefaultPawn newDefaultPawn) {
        playersDefaultPawn = newDefaultPawn;
    }

    /**
     * get DefaultPawn on the field.
     */
    @Override
    public DefaultPawn getPawn() {
        return playersDefaultPawn;
    }
}
