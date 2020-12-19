package borad.boardBuilder;

import borad.BoardInterface;
import borad.DefaultBoard;
import borad.field.DefaultField;
import borad.field.FieldInterface;
import game.CustomColor;
import game.DefaultPawn;

/**
 * Abstract BoardInterface builder.
 */
public abstract class AbstractBoardBuilder {
    /**
     * The Game BoardInterface.
     */
    private BoardInterface gameBoard;

    /**
     * Gets BoardInterface.
     *
     * @return Game BoardInterface
     */
    public BoardInterface getBoard() {
        return gameBoard;
    }

    /**
     * Sets new Game BoardInterface.
     */
    public void buildNewBoard() {
        gameBoard = new DefaultBoard();
    }

    /**
     * builds empty BoardInterface.
     * Firstly it generates all edges next it fills all edges up.
     */
    public void buildBoard() {
        //build all edges
        //set vertices          {p1, p2, p3, p4, p5, p6}
        final int[] verticesX = {0, 12, 12, 0, -12, -12};
        final int[] verticesY = {8,  4, -4, -8, -4,   4};
        //set vectors
        final int[][] vectors = {{1, -1}, {-1, -1}, {-2, 0},
                {-1, 1}, {1, 1}, {2, 0}};

        for (int i = 0; i < 13; i++) { //for each line
            for (int j = 0; j < 6; j++) { //for each vector
                //vector = (vectors[j][0], vectors[j][1])
                final int col = verticesX[j] + vectors[j][0] * i;
                final int row = verticesY[j] + vectors[j][1] * i;
                if (!FieldInterface.exists(
                        new DefaultField(col, row),
                        gameBoard.getFields()
                ) && (i < 5 || i > 7)) {
                    gameBoard.getFields().add(
                            new DefaultField(col, row, true)
                    );
                }
            }
        }

        //fill the center of the BoardInterface
        fillInnerFields(new DefaultField(0, 0));
        gameBoard.getFields().forEach(field -> {
            field.setNeighbors(gameBoard.getFields());
        });
    }

    /**
     * Recursive method that fills up all the fields of the BoardInterface.
     *
     * @param startingField starting FieldInterface
     */
    public void fillInnerFields(final FieldInterface startingField) {
        final int[][] vectors = {{2, 0}, {1, -1}, {-1, -1},
                {-2, 0}, {-1, 1}, {1, 1}};
        gameBoard.getFields().add(startingField);
        for (int i = 0; i < 6; i++) {
            int newColumn = startingField.getColumn() + vectors[i][0];
            int newRow = startingField.getRow() + vectors[i][1];

            FieldInterface tmp = new DefaultField(newColumn, newRow);

            if (!FieldInterface.exists(tmp, gameBoard.getFields())) {
                fillInnerFields(tmp);
            }
        }
    }

    /**
     * Recursive method that colorize arm of the star.
     *
     * @param startingField - starting FieldInterface
     * @param col           - Game.CustomColor of triangle
     * @param depth         - max depth of recursion
     */
    public void addToTriangle(
            final FieldInterface startingField,
            final CustomColor col,
            final int depth) {
        startingField.setColor(col);
        if (depth > 1) {
            for (FieldInterface x : startingField.getNeighbors()) {
                addToTriangle(x, col, depth - 1);
            }
        }
    }

    /**
     * Sets pawns in recursive way.
     *
     * @param startingField FieldInterface from algorithm starts
     * @param col           CustomColor of pawns
     * @param depth         depth of recursion
     */
    public void setPlayersPawns(
            final FieldInterface startingField,
            final CustomColor col,
            final int depth) {
        DefaultPawn tmp = new DefaultPawn(col, startingField);
        if (startingField.getPawn() == null) {
            startingField.setPawn(tmp);
            gameBoard.addPawn(tmp);
        }
        if (depth > 1) {
            for (FieldInterface x : startingField.getNeighbors()) {
                    setPlayersPawns(x, col, depth - 1);
            }
        }
    }

    /**
     * This method sets all pawns.
     */
    public abstract void setPawns();

    /**
     * This method sets all triangle's colors.
     */
    public abstract void setTriangles();
}
