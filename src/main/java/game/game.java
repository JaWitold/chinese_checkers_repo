package game;

import borad.board;
import borad.boardBuilder.*;

import borad.field.field;
import server.player;
import state.playersRound;
import state.gameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The type Game.
 */
public class game {
    private final int numberOfPlayers;
    private final List<player> playerLits;
    private final gameState currentRound;
    private final board gameBoard;

    /**
     * Instantiates a new Game.
     * @param list the list
     */
    public game(List<player> list) {
        playerLits = list;
        numberOfPlayers = playerLits.size();
        sendToAll(String.valueOf(numberOfPlayers));
        gameBoard = setUpBoard(numberOfPlayers);
        currentRound = setUpState();
        System.out.println("Starting the game");
        play();

        //for debugging
        //System.out.println(gameBoard.getPawns().size());
        //gameBoard.getPawns().forEach(x -> System.out.println(x.getColor() + " " + x.getField().getColumn() + " " + x.getField().getRow()));
        //System.out.println(gameBoard.movePawn(gameBoard.getPawns().get(0), field.getField(new defaultBoardField(-1, -7), gameBoard.getFields())));
        //System.out.println(processCommand("MOVE:-2,-6,-3,-5"));
    }

    /**
     * Infinitive loop that of game
     */
    private void play() {
        try {
            sendToAll("ROUND:" + currentRound.getPlayer().getColor());
            Scanner input = currentRound.getPlayer().getSocketInput();

            while (input.hasNext()) {
                String message = input.nextLine();
                if (processCommand(message)) {
                    sendToAll(message);
                    break;
                } else {
                    currentRound.getPlayer().sendMessage("WRONG");
                }
            }
            currentRound.goNext();
            play();
        } catch (Exception e) {
            System.out.println("Cannot continue the game. One player left");
            System.exit(1);
        }
    }

    /**
     * Process commands
     * @param message command to process
     * @return true if command was processed successfully
     */
    private boolean processCommand(String message) {
        if(message.startsWith("MOVE:")) {
            try {
                message = message.substring(message.indexOf(":") + 1);
                int sColumn = Integer.parseInt(message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1);
                int sRow = Integer.parseInt(message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1);
                int dColumn = Integer.parseInt(message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1);
                int dRow = Integer.parseInt(message);

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


    /**
     * Choose board builder board builder.
     *
     * @param number the number
     * @return the board builder
     */
    boardBuilder chooseBoardBuilder(int number) {
        return switch (number) {
            case 6 -> new boardBuilderForSix();
            case 4 -> new boardBuilderForFour();
            case 3 -> new boardBuilderForThree();
            default -> new boardBuilderForTwo();
        };
    }

    /**
     * Sets up board.
     *
     * @param number the number
     * @return the up board
     */
    board setUpBoard(int number) {
        boardConstructor constructor = new boardConstructor();
        constructor.setBoardBuilder(chooseBoardBuilder(number));
        constructor.constructBoard();
        return constructor.getBoard();
    }

    /**
     * Sets up state. Randomize players queue
     *
     * @return the up state
     */
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

    /**
     * Sends the same message to all players
     *
     * @param msg - message to sent
     */
    void sendToAll(String msg) {
        playerLits.forEach(player -> {player.sendMessage(msg);});
    }
}
