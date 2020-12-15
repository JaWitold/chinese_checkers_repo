package borad;

import borad.field.field;
import game.pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * board class
 */
public class defaultGameBoard implements board {
    /**
     * The Fields list.
     */
    protected List<field> fieldsList;
    /**
     * The Pawns list.
     */
    protected List<pawn> pawnsList;

    /**
     * Instantiates a new Default game board.
     */
    public defaultGameBoard(){
        fieldsList = new ArrayList<>();
        pawnsList = new ArrayList<>();
    }

    /**
     * @return list of field
     */
    @Override
    public List<field> getFields() {
        return fieldsList;
    }

    /**
     * @return list of pawns
     */
    @Override
    public List<pawn> getPawns() {
        return pawnsList;
    }

    /**
     * Moves pawn to destination field
     * @param currentPawn pawn to move
     * @param destination field
     * @return true if movement was successful, false if this move was wrong
     */
    @Override
    public boolean movePawn(pawn currentPawn, field destination) {
        boolean tmp = BFS(currentPawn.getField(), destination);
        if(tmp) {
            currentPawn.getField().setPawn(null);
            currentPawn.setField(destination);
            currentPawn.getField().setPawn(currentPawn);
        }
        return tmp;
    }

    @Override
    public void addPawn(pawn tmp) {
        pawnsList.add(tmp);
    }

    /**
     * Function checks if move is possible. It uses modified BFS graph algorithm.
     * TODO: THIS FUNCTION SHOULD BE IN CLASS RULES. but right now its here
     * @param start   the start
     * @param finnish the finnish
     * @return the boolean
     */
    public boolean BFS(field start, field finnish) {
        if(finnish.getPawn() != null) {
            return false;
        }

        Queue<field> toVisit = new LinkedBlockingQueue<>();
        List<field> visited = new ArrayList<>();

        toVisit.add(start);
        visited.add(start);
        while (!toVisit.isEmpty()) {
            field node = toVisit.poll();

            for (field x: node.getNeighbors()) {
                if(x == finnish) {
                    return true;
                } else if (x.getPawn() != null && !visited.contains(x)) {
                    for (field neighbors: x.getNeighbors()) {
                        if(neighbors.getPawn() == null && !visited.contains(neighbors)) {
                            toVisit.add(neighbors);
                        }
                    }
                }
                toVisit.remove(x);
            }
        }
        return false;
    }
}
