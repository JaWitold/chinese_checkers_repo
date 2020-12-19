package game;

import borad.field.FieldInterface;

/**
 * The interface Rules.
 */
public interface Rules {

    /**
     * Is it possible move boolean.
     *
     * @param start   the start
     * @param finnish the finnish
     * @return the boolean
     */
    boolean isItPossibleMove(FieldInterface start, FieldInterface finnish);

    /**
     * Has won boolean.
     *
     * @param playersCustomColor the players CustomColor
     * @return the boolean
     */
    boolean hasWon(CustomColor playersCustomColor);
}
