package game;

import borad.board;
import borad.boardBuilder.*;

import borad.field.defaultBoardField;
import borad.field.field;
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
        play();

        System.out.println(processCommand("MOVE:-3,-5,-4,-4"));
        System.out.println(processCommand("MOVE:0,-8,-1,-7"));
        System.out.println(processCommand("MOVE:0,0,-1,-7"));
        //for debugging
        //System.out.println(gameBoard.getPawns().size());
        //gameBoard.getPawns().forEach(x -> System.out.println(x.getColor() + " " + x.getField().getColumn() + " " + x.getField().getRow()));
        //System.out.println(gameBoard.movePawn(gameBoard.getPawns().get(0), field.getField(new defaultBoardField(-1, -7), gameBoard.getFields())));

    }

    private void play() {
        sendToAll("ROUND:" + currentRound.getPlayer().getColor());
        Scanner input = currentRound.getPlayer().getSocketInput();

        while (input.hasNext()) {
            String message = input.nextLine();
            if(processCommand(message)) {
                sendToAll(message);
                break;
            } else {
                currentRound.getPlayer().sendMessage("WRONG");
            }
        }
        currentRound.goNext();
        play();
    }

    private boolean processCommand(String message) {
        if(message.startsWith("MOVE:")) {
            try {
                message = message.substring(message.indexOf(":") + 1, message.length());
                int sColumn = Integer.parseInt(message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1, message.length());
                int sRow = Integer.parseInt(message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1, message.length());
                int dColumn = Integer.parseInt(message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1, message.length());
                int dRow = Integer.parseInt(message.substring(0, message.length()));

                //Check if player moves own pawn or if the pawn exists
                pawn currentPawn = pawn.getPawn(sColumn, sRow, gameBoard.getPawns());
                if(currentPawn == null || currentRound.getColor() != currentPawn.getColor()) {
                    return false;
                } else {
                 return gameBoard.movePawn(currentPawn, field.getField(dColumn, dRow, gameBoard.getFields()));
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: wrong command MOVE:");
                return false;
            }
        }
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
