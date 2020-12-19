package client;

import borad.BoardInterface;
import borad.boardBuilder.*;
import game.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.ServerException;
import java.util.Scanner;

/**
 * The type Client.
 */
public class Client {
    /**
     * The Socket.
     */
    private Socket socket;
    /**
     * The Socket output.
     */
    private PrintWriter socketOutput;
    /**
     * The Socket input.
     */
    private Scanner socketInput;
    /**
     * The Player.
     */
    private static Player myPlayer;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {

        Client cli = new Client();
        System.out.println("Running Client");
        cli.run(null);

    }

    /**
     * Run.
     *
     * @param newGUI the gui
     */
    public void run(final ClientGUI newGUI) {
        try {
            socket = new Socket("localhost", 50000);
            socketInput = new Scanner(socket.getInputStream());
            socketOutput = new PrintWriter(socket.getOutputStream(), true);

            myPlayer = new Player(
                    CustomColor.valueOf(socketInput.nextLine()),
                    this,
                    newGUI);

            String message = socketInput.nextLine();
            System.out.println(message);

            int mode = Integer.parseInt(message.substring(
                    0,
                    message.indexOf(":")));
            message = message.substring(2);
            //System.out.println(mode + " "+ CustomColor.valueOf(message));

            myPlayer.setBoard(setUpBoard(mode));
            myPlayer.setCurrentRound(CustomColor.valueOf(message));

            System.out.println("Board is Ready");


        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            try {
                if (socket == null) {
                    throw new ServerException("Cannot connect to the server");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private BoardInterface setUpBoard(final int mode) {
        boardConstructor constructor = new boardConstructor();
        constructor.setBoardBuilder(chooseBoardBuilder(mode));
        constructor.constructBoard();
        return constructor.getBoard();
    }

    private AbstractBoardBuilder chooseBoardBuilder(final int mode) {
        return switch (mode) {
            case 6 -> new BoardBuilderForSix();
            case 4 -> new BoardBuilderForFour();
            case 3 -> new BoardBuilderForThree();
            default -> new BoardBuilderForTwo();
        };
    }

    /**
     * Send.
     *
     * @param message the message
     */
    public void send(final String message) {
        socketOutput.println(message);
    }

    /**
     * Gets Player.
     *
     * @return the Player
     */
    public Player getPlayer() {
        return myPlayer;
    }

    /**
     * Has message boolean.
     *
     * @return the boolean
     */
    public boolean hasMessage() {
        return socketInput.hasNextLine();
    }

    /**
     * Read message string.
     *
     * @return the string
     */
    public String readMessage() {
        return socketInput.nextLine();
    }
}
