package game;

import borad.BoardInterface;
import borad.field.FieldInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The type Default rules set.
 */
public class RulesSet implements Rules {
    /**
     * The board.
     */
    private final BoardInterface gameBoard;

    /**
     * Instantiates a new Default rules set.
     *
     * @param newBoard the new BoardInterface
     */
    public RulesSet(final BoardInterface newBoard) {
        gameBoard = newBoard;
    }

    /**
     * Checks if move is possible.
     * @param start   the start
     * @param finnish the finnish
     * @return
     */
    @Override
    public boolean isItPossibleMove(final FieldInterface start, final FieldInterface finnish) {
        if (finnish.getPawn() != null) {
            return false;
        }

        //cannot escape own base
        if (start.getPawn().getColor() == start.getColor()
                && finnish.getColor() != start.getPawn().getColor()) {
            return false;
        }

        //try to move by one little step
        for (FieldInterface neighbor: start.getNeighbors()) {
            if (neighbor == finnish) {
                return true;
            }
        }

        //let's try to jump as Pawel Jumper
        Queue<FieldInterface> toVisit = new LinkedBlockingQueue<>();
        List<FieldInterface> visited = new ArrayList<>();

        toVisit.add(start);

        while (!toVisit.isEmpty()) {
            FieldInterface node = toVisit.poll();
            visited.add(node);

            for (FieldInterface neighbor : node.getNeighbors()) {
                int vx = 2 * neighbor.getColumn() - node.getColumn();
                int vy = 2 * neighbor.getRow() - node.getRow();

                FieldInterface jump = FieldInterface.getField(vx, vy, gameBoard.getFields());
                if (jump != null && jump.getPawn() == null
                        && !visited.contains(jump)) {
                    if (jump == finnish) {
                        return true;
                    }
                    toVisit.add(jump);
                }
            }
        }
        return false;
    }

    /**
     * Checks if player won.
     * @param playersCustomColor the players CustomColor
     * @return true if player won.
     */
    @Override
    public boolean hasWon(final CustomColor playersCustomColor) {
        List<FieldInterface> fieldsToCheck = new ArrayList<>();
        for (FieldInterface fld : gameBoard.getFields()) {
            if (fld.getColor() == playersCustomColor) {
                fieldsToCheck.add(fld);
            }
        }
        boolean hasWon = true;
        for (FieldInterface fld : fieldsToCheck) {
            if (fld.getColor() != fld.getPawn().getColor()) {
                hasWon = false;
                break;
            }
        }
        return hasWon;
    }
}
