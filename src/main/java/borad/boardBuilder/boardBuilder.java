package borad.boardBuilder;

import borad.board;
import borad.defaultGameBoard;
import borad.field.defaultBoardField;
import borad.field.field;
import game.color;
import game.pawn;

/**
 * Abstract board builder
 */
public abstract class boardBuilder {
    public board gameBoard;

    /**
     * @return game board
     */
    public board getBoard() {
        return gameBoard;
    }

    /**
     * Sets new game board
     */
    public void buildNewBoard() {
        gameBoard = new defaultGameBoard();
    }

    /**
     * builds empty board, firstly it generates all edges, next it fills all edges up.
     */
    public void buildBoard() {
        //build all edges
        //set vertices          {p1, p2, p3, p4, p5, p6}
        final int[] verticesX = {0, 12, 12, 0, -12, -12};
        final int[] verticesY = {8,  4, -4, -8, -4,   4};
        //set vectors
        final int[][] vectors =   {{  1,   -1}, { -1,   -1}, {  -2,  0}, {  -1,   1}, { 1,   1}, {  2,  0}};

        for (int i = 0; i < 13; i++) {//for each line
            for (int j = 0; j < 6; j++) {//for each vector
                //vector = (vectors[j][0], vectors[j][1])
                final int col = verticesX[j] + vectors[j][0] * i;
                final int row = verticesY[j] + vectors[j][1] * i;
                if (!field.exists(new defaultBoardField(col, row), gameBoard.getFields()) && (i < 5 || i > 7)) {
                    gameBoard.getFields().add(new defaultBoardField(col, row, true));
                }
            }
        }

        //fill the center of the board
        fillInnerFields(new defaultBoardField(0, 0));
        gameBoard.getFields().forEach(field -> {field.setNeighbors(gameBoard.getFields());});

        /*for debugging*/
        //gameBoard.getFields().forEach(field -> System.out.println("col "+ field.getColumn() + "\trow " + field.getRow() + "\tedge " + field.isEdge() + "\tneighbours " + field.getNeighbors().size()));
        //System.out.println(gameBoard.getFields().size());
    }

    /**
     * Recursive method that fills up all the fields of the board
     * @param startingField starting field
     */
    public void fillInnerFields(field startingField) {
        final int[][] vectors = {{2, 0}, {1, -1}, {-1, -1}, {-2, 0}, {-1, 1}, {1, 1}};
        gameBoard.getFields().add(startingField);
        for(int i = 0; i < 6; i++) {
            int newColumn = vectors[i][0];
            int newRow = vectors[i][1];

            if(!field.exists(new defaultBoardField(startingField.getColumn() + newColumn, startingField.getRow() + newRow), gameBoard.getFields())){
                field tmp = new defaultBoardField(startingField.getColumn() + newColumn, startingField.getRow() + newRow);
                fillInnerFields(tmp);
            }
        }
    }

    /**
     * Recursive method that colorize arm of the star
     * @param startingField - starting field
     * @param col - game.color of triangle
     * @param depth - max depth of recursion
     */
    public void addToTriangle(final field startingField, final color col, int depth) {
        startingField.setColor(col);
        if(depth > 1) {
            for(field x : startingField.getNeighbors()) {
                addToTriangle(x, col, depth - 1);
            }
        }
    }

    /**
     * Sets pawns in recursive way
     * @param startingField field from algorithm starts
     * @param col color of pawns
     * @param depth depth of recursion
     */
    public void setPlayersPawns(final field startingField, final color col, int depth) {
        pawn tmp = new pawn(col, startingField);
        if(startingField.getPawn() == null) {
            startingField.setPawn(tmp);
            gameBoard.addPawn(tmp);
        }
            //System.out.println(startingField.getPawn().getColor());
        if (depth > 1) {
            //System.out.println(depth + " " + col + " " + startingField.getColumn() + " " +startingField.getRow());
            for (field x : startingField.getNeighbors()) {
                setPlayersPawns(x, col, depth - 1);
            }
        }

    }

    /**
     * This method sets all pawns
     */
    public abstract void setPawns();
    /**
     * This method sets all triangle's colors
     */
    public abstract void setTriangles();
}