package borad;

import borad.field.field;

/**
 * interface for a borad.board of the game
 */
public interface board {

    /**
     * generates all the borad.board
     */
    void generate();

    /**
     * adds all valid neighbors
     *
     * @param startingField is borad.field.field that algorithm starts at
     */
    void generateNeighbors(field startingField);
}