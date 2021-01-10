package server;

import game.CustomColor;
import game.Game;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Every Player of the Game is one thread.
 */
public class Player implements Runnable {
    /**
     * Server Socket.
     */
    private final Socket serverSocket;
    /**
     * Socket input.
     */
    private Scanner socketInput;
    /**
     * Socket output.
     */
    private PrintWriter socketOutput;
    /**
     * Color of the player.
     */
    private final CustomColor myColor;
    /**
     * The Game.
     */
    private static Game theGame;

    /**
     * Instantiates a new Player.
     *
     * @param newSocket   web socket to communicate to the Client
     * @param playerColor CustomColor of the Player
     */
    public Player(final Socket newSocket, final CustomColor playerColor) {
        serverSocket = newSocket;
        myColor = playerColor;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            socketInput = new Scanner(serverSocket.getInputStream());
            socketOutput = new PrintWriter(
                    serverSocket.getOutputStream(),
                    true);
            socketOutput.println(myColor);
            play();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                theGame.saveGame();
                theGame.sendToAll("QUIT");
                serverSocket.close();
                System.out.println("Player left");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main loop of the Game.
     */
    private void play() {
        while (socketInput.hasNextLine()) {
            String command = socketInput.nextLine();
            System.out.println(command);
            if (command.startsWith("QUIT")) {
                System.out.println(myColor + " left.");
                System.exit(2);
            } else if (command.startsWith("MOVE:")) {
                if (theGame.processCommand(command, myColor)) {
                    theGame.setCurrentRound(theGame.getCurrentRound().goNext());
                    String message = command
                            + ";ROUND:"
                            + theGame.getCurrentRound().getColor();

                    if (theGame.processCommand("WON:", myColor)) {
                        message = "WON:" + myColor;
                        theGame.sendToAll(message);
                        theGame.saveGame();
                        System.exit(0);
                    }

                    theGame.sendToAll(message);
                } else {
                    socketOutput.println("WRONG");
                }
            }
        }
    }

    /**
     * Gets CustomColor.
     *
     * @return CustomColor of the Player
     */
    public CustomColor getColor() {
        return myColor;
    }

    /**
     * Send message to Client app.
     *
     * @param msg message
     */
    public void sendMessage(final String msg) {
        socketOutput.println(msg);
    }

    /**
     * Sets Game.
     *
     * @param newGame the new Game
     */
    public void setTheGame(final Game newGame) {
        theGame = newGame;
    }


}
