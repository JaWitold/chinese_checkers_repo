package project.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class player implements Runnable {

    private Socket serverSocket;
    private Scanner socketInput;
    private PrintWriter socketOutput;

    public player(Socket newSocket) {
        serverSocket = newSocket;

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


            while(socketInput.hasNextLine()) {
                String msg = socketInput.nextLine();
                System.out.println(msg);

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
}
