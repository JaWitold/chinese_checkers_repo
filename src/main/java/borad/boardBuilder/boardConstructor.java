package borad.boardBuilder;

import borad.board;

/**
 * Constructor of the board
 */
public class boardConstructor {
    private boardBuilder builder;

    /**
     * Sets new board builder
     * @param newBuilder board builder
     */
    public void setBoardBuilder(boardBuilder newBuilder) {
        this.builder = newBuilder;
    }

    /**
     * @return board
     */
    public board getBoard() {
        return builder.getBoard();
    }

    /**
     * Constructs the board
     */
    public void constructBoard() {
        builder.buildNewBoard();
        builder.buildBoard();
        builder.setPawns();
        builder.setTriangles();
    }
}
