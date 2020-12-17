package client;

import borad.boardBuilder.BoardGUI;

import javax.swing.*;


public class clientGUI extends client {
    public JFrame f;
    public BoardGUI panel;
    private final myMouseListener mouse;

    public clientGUI() {
        super();
        run();
        System.out.println("Running client");
        f = new JFrame("Chinese Checkers");
        panel = new BoardGUI();
        mouse = new myMouseListener();
        mouse.setGUI(panel);
        mouse.setPlayer(myPlayer);
        f.addMouseListener(mouse);
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
        try {
            myPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public JPanel getPanel() {
        return panel;
    }


}
