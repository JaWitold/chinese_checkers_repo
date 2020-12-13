package client;

import borad.board;
import borad.boardBuilder.*;
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
        System.out.println("Running client");
        cli.run();

    }

    public void run() {
        try {
            socket = new Socket("localhost", 50000);
            socketInput = new Scanner(socket.getInputStream());
            socketOutput = new PrintWriter(socket.getOutputStream(), true);

            myPlayer = new player(color.valueOf(socketInput.nextLine()), this);
            int mode = socketInput.nextInt();
            System.out.println(mode);
            myPlayer.setBoard(setUpBoard(mode));
            while (true) {}

        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            try {
                if(socket == null) {
                    throw new ServerException("Cannot connect to the server");
                }
                socket.close();
                socketInput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private board setUpBoard(int mode) {
        boardConstructor constructor = new boardConstructor();
        constructor.setBoardBuilder(chooseBoardBuilder(mode));
        constructor.constructBoard();
        return constructor.getBoard();
    }

    private boardBuilder chooseBoardBuilder(int mode) {
        boardBuilder tmp = switch (mode) {
            case 6 -> new boardBuilderForSix();
            case 4 -> new boardBuilderForFour();
            case 3 -> new boardBuilderForThree();
            default -> new boardBuilderForTwo();
        };
        return tmp;
    }
}
