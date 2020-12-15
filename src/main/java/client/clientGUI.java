package client;

import borad.board;
import borad.boardBuilder.BoardGUI;
import borad.boardBuilder.boardConstructor;
import game.color;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.ServerException;
import java.util.Scanner;

public class clientGUI extends client {
    public JFrame f;
    public BoardGUI panel;
    public clientGUI() {
        super();
        run();
        System.out.println("Running client");
        f = new JFrame("Chinese Checkers");
        panel = new BoardGUI();

        f.add(panel);

        f.setSize(648, 517);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        clientGUI cli = new clientGUI();

        cli.f.setTitle(String.valueOf(myPlayer.getColor()));
        cli.panel.setBoardToDraw(myPlayer.getBoard());

    }


}
