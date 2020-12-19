package game;

import borad.BoardInterface;
import borad.boardBuilder.*;

import borad.field.FieldInterface;
import server.Player;
import state.DefaultGameState;
import state.GameStateInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Game.
 */
public class Game {
    /**
     * Number of players.
     */
    private final int numberOfPlayers;
    /**
     * List of players.
     */
    private final List<Player> playerList;
    /**
     * The Current round.
     */
    private GameStateInterface currentRound;
    /**
     * Game board.
     */
    private final BoardInterface gameBoard;
    /**
     * Set of Rules.
     */
    private final Rules rulesSet;

    /**
     * Instantiates a new Game.
     *
     * @param list the list
     */
    public Game(final List<Player> list) {
        playerList = list;
        playerList.forEach(x -> {
            x.setTheGame(this);
        });
        numberOfPlayers = playerList.size();
        currentRound = setUpState();
        System.out.println("current: " + currentRound.getColor());
        sendToAll(numberOfPlayers + ":" + currentRound.getColor());
        gameBoard = setUpBoard(numberOfPlayers);
        rulesSet = new RulesSet(gameBoard);
        System.out.println("Starting the Game");
        while (true) { }
    }

    public GameStateInterface getCurrentRound() {
        return currentRound;
    }
    /**
     * Process commands.
     *
     * @param message      command to process
     * @param playersColor the players CustomColor
     * @return true if command was processed successfully
     */
    public synchronized boolean processCommand(
            String message,
            final CustomColor playersColor) {
        System.out.println("current round:" + currentRound.getColor());
        if (currentRound.getColor() == playersColor) {
            if (message.startsWith("MOVE:")) {
                try {
                    message = message.substring(message.indexOf(":") + 1);
                    int sColumn = Integer.parseInt(
                            message.substring(0, message.indexOf(",")));
                    message = message.substring(message.indexOf(",") + 1);
                    int sRow = Integer.parseInt(
                            message.substring(0, message.indexOf(",")));
                    message = message.substring(message.indexOf(",") + 1);
                    int dColumn = Integer.parseInt(
                            message.substring(0, message.indexOf(",")));
                    message = message.substring(message.indexOf(",") + 1);
                    int dRow = Integer.parseInt(message);

                    //Check if Player moves own DefaultPawn or if the DefaultPawn exists
                    DefaultPawn currentDefaultPawn = DefaultPawn.getPawn(
                            sColumn,
                            sRow,
                            gameBoard.getPawns());
                    if (currentDefaultPawn == null || currentRound.getColor() != currentDefaultPawn.getColor()) {
                        return false;
                    } else {
                        if (rulesSet.isItPossibleMove(
                                currentDefaultPawn.getField(),
                                FieldInterface.getField(
                                        dColumn,
                                        dRow,
                                        gameBoard.getFields()))) {
                            gameBoard.movePawn(currentDefaultPawn,
                                    FieldInterface.getField(
                                            dColumn,
                                            dRow,
                                            gameBoard.getFields()));
                            return true;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: wrong command MOVE:");
                    return false;
                }
            } else if (message.startsWith("WON:")) {
                return rulesSet.hasWon(playersColor);
            }
        }
        return false;
    }


    /**
     * Choose BoardInterface builder BoardInterface builder.
     *
     * @param number the number
     * @return the BoardInterface builder
     */
    AbstractBoardBuilder chooseBoardBuilder(final int number) {
        return switch (number) {
            case 6 -> new BoardBuilderForSix();
            case 4 -> new BoardBuilderForFour();
            case 3 -> new BoardBuilderForThree();
            default -> new BoardBuilderForTwo();
        };
    }

    /**
     * Sets up BoardInterface.
     *
     * @param number the number
     * @return the up BoardInterface
     */
    BoardInterface setUpBoard(final int number) {
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
    GameStateInterface setUpState() {

        final List<GameStateInterface> temp = new ArrayList<>();
        final List<Player> shuffledPlayersList = playerList;
        Collections.shuffle(shuffledPlayersList);

        for (Player currentPlayer : shuffledPlayersList) {
            temp.add(new DefaultGameState(currentPlayer));
        }

        for (int i = 1; i <= numberOfPlayers; i++) {
            temp.get(i - 1).setNext(temp.get(i % numberOfPlayers));
        }
        return temp.get(0);
    }

    /**
     * Sends the same message to all players.
     *
     * @param message - message to sent
     */
    public void sendToAll(final String message) {
        playerList.forEach(Player -> {
            Player.sendMessage(message);
        });
    }

    /**
     * Get number of players int.
     *
     * @return the int
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setCurrentRound(final GameStateInterface goNext) {
        currentRound = goNext;
    }
}
