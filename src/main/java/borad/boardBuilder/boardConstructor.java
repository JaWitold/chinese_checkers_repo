package borad.boardBuilder;

import borad.BoardInterface;

/**
 * Constructor of the BoardInterface
 */
public class boardConstructor {
    private AbstractBoardBuilder builder;

    /**
     * Sets new BoardInterface builder
     *
     * @param newBuilder BoardInterface builder
     */
    public void setBoardBuilder(AbstractBoardBuilder newBuilder) {
        this.builder = newBuilder;
    }

    /**
     * Gets BoardInterface.
     *
     * @return BoardInterface BoardInterface
     */
    public BoardInterface getBoard() {
        return builder.getBoard();
    }

    /**
     * Constructs the BoardInterface
     */
    public void constructBoard() {
        builder.buildNewBoard();
        builder.buildBoard();
        builder.setPawns();
        builder.setTriangles();
    }
}
