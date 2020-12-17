package server;

import game.color;
import game.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * Every player of the game is one thread
 */
public class player implements Runnable {

    private final Socket serverSocket;
    private Scanner socketInput;
    private PrintWriter socketOutput;
    private final color myColor;
    public game Game;

    /**
     * @param newSocket web socket to communicate to the client
     * @param playerColor color of the player
     */
    public player(Socket newSocket, color playerColor) {
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
            socketOutput = new PrintWriter(serverSocket.getOutputStream(), true);

            socketOutput.println(myColor);

            play();
            while (true) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                System.out.println("player left");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void play() {
        while(socketInput.hasNextLine()) {
            String command = socketInput.nextLine();
            System.out.println(command);
            if(command.startsWith("QUIT")){
                System.out.println(myColor + " left.");
                System.exit(2);
            } else if(command.startsWith("MOVE:")) {
                if(Game.processCommand(command, myColor)) {
                    Game.currentRound = Game.currentRound.goNext();
                    String message = command + ";ROUND:" + Game.currentRound.getColor();
                    System.out.println(message);
                    //System.out.println("is correct ");
                    Game.sendToAll(message);
                } else {
                    socketOutput.println("WRONG");
                }
            }
        }
    }

    /**
     * @return color of the player
     */
    public color getColor() {
        return myColor;
    }

    /**
     * Send message to client app
     * @param msg message
     */
    public void sendMessage(String msg) {
        socketOutput.println(msg);
    }

    /**
     * @return socket input
     */
    public Scanner getSocketInput() {
        return socketInput;
    }

    public void setGame(game newGame) {
        Game = newGame;
    }


}
