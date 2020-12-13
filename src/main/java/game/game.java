package game;

import borad.board;
import borad.boardBuilder.*;

import server.player;
import state.playersRound;
import state.gameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class game {
    private int numberOfPlayers;
    private List<player> playerLits;
    private gameState currentRound;
    private board gameBoard;

    public game(List<player> list) {
        playerLits = list;
        numberOfPlayers = playerLits.size();
        sendToAll(String.valueOf(numberOfPlayers));
        gameBoard = setUpBoard();
        currentRound = setUpState();
        System.out.println("Starting the game");
        //play();

        //for debugging
        System.out.println(gameBoard.getPawns().size());
        gameBoard.getPawns().forEach(x -> System.out.println(x.getColor() + " " + x.getField().getColumn() + " " + x.getField().getRow()));
    }

    private void play() {
        sendToAll("ROUND:" + currentRound.getPlayer().getColor());
        while (true) {
            Scanner input = currentRound.getPlayer().getSocketInput();
            String message = input.nextLine();
            if(processCommand(message)) {
                break;
            }
            currentRound.goNext();
        }
    }

    private boolean processCommand(String message) {
        //TODO: process movement
        return true;
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

    board setUpBoard() {
        boardConstructor constructor = new boardConstructor();
        constructor.setBoardBuilder(chooseBoardBuilder());
        constructor.constructBoard();
        return constructor.getBoard();
    }

    gameState setUpState() {

        List<gameState> temp = new ArrayList<>();
        List<player> shuffledPlayersList = playerLits;
        Collections.shuffle(shuffledPlayersList);

        for(player currentPlayer : shuffledPlayersList) {
            temp.add(new playersRound(currentPlayer));
        }

        for(int i = 1; i <= numberOfPlayers; i++) {
            temp.get(i-1).setNext(temp.get(i%numberOfPlayers));
        }
        return temp.get(0);
        //temp.forEach(x -> {System.out.println(x.getPlayer().getColor());});
    }

    void sendToAll(String msg) {
        playerLits.forEach(player -> {player.sendMessage(msg);});
    }
}
