package client;

import borad.boardBuilder.BoardGUI;

import javax.swing.*;

public class clientGUI {

    private final JFrame gameFrame;
    private final BoardGUI gameBoardGUI;
    private final myMouseListener gameMouseListener;
    private final client gameClient;
    public final player gamePlayer;

    public clientGUI(client newClient) {
        gameClient = newClient;
        gameClient.run(this);
        gamePlayer = gameClient.getPlayer();

        gameFrame = new JFrame("Chinese Checkers");
        updateTitle();
        gameBoardGUI = new BoardGUI();
        gameMouseListener = new myMouseListener();
    }

    public static void main(String[] args) {
        clientGUI GUI = new clientGUI(new client());
        GUI.setup();
        GUI.play();
    }

    private void setup() {
        System.out.println("Setting up GUI");

        gameMouseListener.setGUI(gameBoardGUI);
        gameMouseListener.setPlayer(gamePlayer);
        gameFrame.addMouseListener(gameMouseListener);
        gameFrame.add(gameBoardGUI);

        gameFrame.setSize(648, 517);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);

        gameBoardGUI.setBoardToDraw(gamePlayer.getBoard());
    }

    private void play() {
        gamePlayer.play();
    }

    public JPanel getGameBoardGUI() {
        return gameBoardGUI;
    }

    public void updateTitle(){
        gameFrame.setTitle(gamePlayer.getColor() + " | ROUND: " + gamePlayer.getCurrentRound());}

    public JFrame getFrame() {
        return gameFrame;
    }
    public void repaint() {
        this.gameBoardGUI.repaint();
    }

}
