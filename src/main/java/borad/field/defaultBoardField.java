package borad.field;

import game.color;
import game.pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * this is one borad.field.field of the borad.board
 */
public class defaultBoardField implements field {

    protected int column;
    protected int row;
    protected boolean edge;
    protected List<field> neighbors;
    protected color playersColor;
    protected pawn playersPawn;

    /**
     * short version of default constructor
     * @param column of this borad.field.field
     * @param row of this borad.field.field
     */
    public defaultBoardField(int column, int row)
    {
        this(column, row, false);
    }

    /**
     * Default constructor, sets values of:
     * @param column of this borad.field.field
     * @param row of this borad.field.field
     * @param edge is this borad.field.field an edge
     */
    public defaultBoardField(int column, int row, boolean edge)
    {
        setColumn(column);
        setRow(row);
        setEdge(edge);
        playersColor = color.NULL;
        neighbors = new ArrayList<>();

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
     * @param fields list of neighbors
     */
    @Override
    public void setNeighbors(List<field> fields) {
        final int[][] vectors = {{2, 0}, {1, -1}, {-1, -1}, {-2, 0}, {-1, 1}, {1, 1}};
        for(int i = 0; i < 6; i++) {
            field tmp = field.getField(new defaultBoardField(this.getColumn() + vectors[i][0], this.getRow() + vectors[i][1]), fields);
            if(tmp != null) {
                this.addNeighbor(tmp);
            }
        }
    }

    /**
     * Adds one neighbor to the list of neighbors
     * @param newNeighbor new neighbor
     */
    @Override
    public void addNeighbor(field newNeighbor) {
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

    @Override
    public void setColor(color col) {
        playersColor = col;
    }

    /**
     * get color of the field
     *
     * @return color of the field
     */
    @Override
    public color getColor() {
        return playersColor;
    }

    /**
     * set pawn on the field
     *
     * @param newPawn pawn
     */
    @Override
    public void setPawn(pawn newPawn) {
        playersPawn = newPawn;
    }

    /**
     * get pawn on the field
     */
    @Override
    public pawn getPawn() {
        return playersPawn;
    }
}