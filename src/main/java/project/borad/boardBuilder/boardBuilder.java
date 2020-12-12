package project.borad.boardBuilder;

import project.borad.board;
import project.borad.defaultGameBoard;
import project.borad.field.field;
import project.borad.field.defaultBoardField;
import project.color;

/**
 * abstract board builder
 */
public abstract class boardBuilder {
    public board gameBoard;

    public board getBoard() {
        return gameBoard;
    }

    public void buildNewBoard() {
        gameBoard = new defaultGameBoard();
    }

    /**
     * builds empty board, firstly it generates all edges, next it fills all edges up.
     */
    public void buildBoard() {
        //build all edges
        //set vertices            {{ p5,  p6}, { p1,  p6}, { p5, p4}}
        final int[][] verticesX = {{-12, -12}, {0, -12}, {-12, 0}};
        final int[][] verticesY = {{-4, 4}, {8, 4}, {-4, -8}};
        //set vectors           {{ ax,  ay}, { bx,  by}, { fx, fy}}
        final int[][] vectors = {{2, 0}, {1, -1}, {1, 1}};

        for (int i = 0; i < 13; i++) {//for each line
            for (int j = 0; j < 3; j++) {//for each vector
                for (int k = 0; k < 2; k++) {//for each vertex
                    //vector = (vectors[j][0], vectors[j][1])
                    final int col = verticesX[j][k] + vectors[j][0] * i;
                    final int row = verticesY[j][k] + vectors[j][1] * i;

                    if (!field.exists(new defaultBoardField(col, row), gameBoard.getField()) && (i < 5 || i > 7)) {
                        gameBoard.getField().add(new defaultBoardField(col, row, true));
                    }
                }
            }
        }
        //fill the center of the board

        final field tmp = new defaultBoardField(0, 0);
        setNeighbors(tmp);

        /*for debugging
          getField().forEach(field -> System.out.println("col "+ field.getColumn() + "\trow " + field.getRow() + "\tedge " + field.isEdge()));
          System.out.println(getField().size());
        */
    }

    /**
     * Recursive method that fills up all the field of the board
     * @param startingField starting field
     */
    public void setNeighbors(field startingField) {
        final int[][] vectors = {{2, 0}, {1, -1}, {-1, -1}, {-2, 0}, {-1, 1}, {1, 1}};
        gameBoard.getField().add(startingField);
        for(int i = 0; i < 6; i++) {
            int newColumn = vectors[i][0];
            int newRow = vectors[i][1];

            if(!field.exists(new defaultBoardField(startingField.getColumn() + newColumn, startingField.getRow() + newRow), gameBoard.getField())){
                field tmp = new defaultBoardField(startingField.getColumn() + newColumn, startingField.getRow() + newRow);
                startingField.addNeighbor(tmp);
                tmp.addNeighbor(startingField);
                setNeighbors(tmp);
            }
        }
    }

    /**
     * Recursive method that colorize arm of the star
     * @param startingField - starting field
     * @param col - color of triangle
     * @param depth - max depth of recursion
     */
    public void addToTriangle(field startingField, color col, int depth) {
        startingField.setColor(col);
        if(depth > 0) {
            depth--;
            for(field x : startingField.getNeighbors()) {
                addToTriangle(x, col, depth);
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