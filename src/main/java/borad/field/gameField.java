package borad.field;

import java.util.ArrayList;
import java.util.List;

/**
 * this is one borad.field.field of the borad.board
 */
public class gameField implements field {

    protected int column;
    protected int row;
    protected boolean edge;
    protected List<field> neighbors;

    /**
     * short version of default constructor
     * @param column of this borad.field.field
     * @param row of this borad.field.field
     */
    public gameField (int column, int row)
    {
        this(column, row, false);
    }

    /**
     * Default constructor, sets values of:
     * @param column of this borad.field.field
     * @param row of this borad.field.field
     * @param edge is this borad.field.field an edge
     */
    public gameField (int column, int row, boolean edge)
    {
        setColumn(column);
        setRow(row);
        setEdge(edge);
    }

    /**
     * sets col of borad.field.field
     * @param column col
     */
    @Override
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * sets the row of borad.field.field
     * @param row row
     */
    @Override
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @param edge true if this borad.field.field is an edge
     */
    @Override
    public void setEdge(boolean edge) {
        this.edge = edge;
    }

    /**
     * Adds list of neighbors
     * @param neighbors list of neighbors
     */
    @Override
    public void setNeighbors(List<field> neighbors) {
        if(this.neighbors == null) {
            this.neighbors = new ArrayList<>();
        }

        for(field x: neighbors){
            if(this.neighbors.size() < 6) {
                this.neighbors.add(x);
            } else {
                break;
            }
        }
    }

    /**
     * Adds one neighbor to the list of neighbors
     * @param newNeighbor new neighbor
     */
    @Override
    public void addNeighbor(field newNeighbor) {
        if(this.neighbors == null) {
            this.neighbors = new ArrayList<>();
        }

        if(this.neighbors.size() < 6) {
            this.neighbors.add(newNeighbor);
        }

    }

    /**
     * @return column number of borad.field.field
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * @return row number of borad.field.field
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * @return list of neighbors of borad.field.field
     */
    @Override
    public List<field> getNeighbors() {
        return this.neighbors;
    }

    /**
     * @return true if this borad.field.field is an edge
     */
    @Override
    public boolean isEdge() {
        return edge;
    }

    /**
     * @param someField borad.field.field to check
     * @return true if this borad.field.field and someField are neighbors
     */
    @Override
    public boolean isNeighbor(field someField) {
        return field.areNeighbors(this, someField);
    }
}