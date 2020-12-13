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
    protected List<field> fieldsList;
    protected List<pawn> pawnsList;

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
            currentPawn.setField(destination);
        }
        return tmp;
    }

    @Override
    public void addPawn(pawn tmp) {
        pawnsList.add(tmp);
    }

    public boolean BFS(field start, field finnish) {
        Queue<field> queue = new LinkedBlockingQueue<>();
        List<field> visited = new ArrayList<>();

        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            field node = queue.poll();

            for (field x: node.getNeighbors()) {
                if(x == finnish) {

                    return true;
                } else if (x.getPawn() != null && !visited.contains(x)) {
                    for (field neighbors: x.getNeighbors()) {
                        if(neighbors.getPawn() == null && !visited.contains(neighbors)) {
                            queue.add(neighbors);
                        }
                    }
                }
                queue.remove(x);
            }
        }
        return false;
    }
}
