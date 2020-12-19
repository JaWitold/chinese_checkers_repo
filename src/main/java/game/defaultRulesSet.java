package game;

import borad.board;
import borad.field.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class defaultRulesSet implements Rules {

    private board gameBoard;
    public defaultRulesSet(board newBoard) {
        gameBoard = newBoard;
    }

    @Override
    public boolean isItPossibleMove(field start, field finnish) {
        if (finnish.getPawn() != null) {
            return false;
        }

        //cannot escape own base
        if(start.getPawn().getColor() == start.getColor() && finnish.getColor() != start.getPawn().getColor()) {
            return false;
        }

        //try to move by one little step
        for (field neighbor: start.getNeighbors()) {
            if (neighbor == finnish) {
                return true;
            }
        }

        //let's try to jump as Pawel Jumper
        Queue<field> toVisit = new LinkedBlockingQueue<>();
        List<field> visited = new ArrayList<>();

        toVisit.add(start);

        while (!toVisit.isEmpty()) {
            field node = toVisit.poll();
            visited.add(node);

            for(field neighbor : node.getNeighbors()) {
                int vx = 2 * neighbor.getColumn() - node.getColumn();
                int vy = 2 * neighbor.getRow() - node.getRow();

                field jump = field.getField(vx, vy, gameBoard.getFields());
                if (jump != null && jump.getPawn() == null && !visited.contains(jump)) {
                    //System.out.println(jump.getColumn() + " " + jump.getRow());
                    if (jump == finnish) {
                        return true;
                    }
                    toVisit.add(jump);
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasWon(color playersColor) {
        List<field> fieldsToCheck = new ArrayList<>();
        for (field fld : gameBoard.getFields()) {
            if (fld.getColor() == playersColor) {
                fieldsToCheck.add(fld);
            }
        }
        boolean hasWon = true;
        for (field fld : fieldsToCheck) {
            if (fld.getColor() != fld.getPawn().getColor()) {
                hasWon = false;
                break;
            }
        }
        return hasWon;
    }
}
