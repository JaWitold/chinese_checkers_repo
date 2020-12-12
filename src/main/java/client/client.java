package client;

import game.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.ServerException;
import java.util.Scanner;

public class client {
    Socket socket;
    PrintWriter socketOutput;
    Scanner socketInput;
    private player myPlayer;

    public static void main(String[] args) {

        client cli = new client();
        cli.run();

    }

    public void run() {
        try {
            socket = new Socket("localhost", 50000);
            socketInput = new Scanner(socket.getInputStream());
            socketOutput = new PrintWriter(socket.getOutputStream(), true);

            myPlayer = new player(color.valueOf(socketInput.nextLine()), this);
            while (true) {}

        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            try {
                if(socket == null) {
                    throw new ServerException("Nie udało sie polaczyc z serwerem");
                }
                socket.close();
                socketInput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
