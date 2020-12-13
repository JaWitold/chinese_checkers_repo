package game;

import borad.board;
import borad.boardBuilder.*;

import server.player;
import state.playersRound;
import state.gameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class game {
    private int numberOfPlayers;
    private List<player> playerLits;
    private gameState round;
    private board gameBorad;
    private boardConstructor constructor;

    public game(List<player> list) {
        playerLits = list;
        numberOfPlayers = playerLits.size();
        sendToAll(String.valueOf(numberOfPlayers));
        constructor = new boardConstructor();
        constructor.setBoardBuilder(chooseBoardBuilder());
        constructor.constructBoard();
        gameBorad = constructor.getBoard();
        setUpState();
    }


    boardBuilder chooseBoardBuilder() {
        boardBuilder tmp = switch (numberOfPlayers) {
            case 6 -> new boardBuilderForSix();
            case 4 -> new boardBuilderForFour();
            case 3 -> new boardBuilderForThree();
            default -> new boardBuilderForTwo();
        };
        return tmp;
    }

    void setUpState() {

        List<gameState> temp = new ArrayList<>();
        List<player> shuffledPlayersList = playerLits;
        Collections.shuffle(shuffledPlayersList);

        for(player currentPlayer : shuffledPlayersList) {
            temp.add(new playersRound(currentPlayer));
        }

        for(int i = 1; i <= numberOfPlayers; i++) {
            temp.get(i-1).setNext(temp.get(i%numberOfPlayers));
        }

        //temp.forEach(x -> {System.out.println(x.getPlayer().getColor());});
    }

    void sendToAll(String msg) {
        playerLits.forEach(player -> {player.sendMessage(msg);});
    }
}
