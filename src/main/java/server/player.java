package server;

import game.color;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class player implements Runnable {

    private Socket serverSocket;
    private Scanner socketInput;
    private PrintWriter socketOutput;
    private color myColor;

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
            socketInput.nextLine();

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

    public color getColor() {
        return myColor;
    }

    public void sendMessage(String msg) {
        socketOutput.println(msg);
    }
}
