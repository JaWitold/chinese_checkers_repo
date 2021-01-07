package client;

import borad.boardBuilder.BoardGUI;

import javax.swing.*;

/**
 * The type Client gui.
 */
public class ClientGUI {
    /**
     * Frame of the app.
     */
    private final JFrame gameFrame;
    /**
     * Board panel.
     */
    private final BoardGUI gameBoardGUI;
    /**
     * Mouse Listener.
     */
    private final MyMouseListener gameMouseListener;
    /**
     * The Game Player.
     */
    private final Player gamePlayer;

    /**
     * Instantiates a new Client gui.
     *
     * @param newClient the new Client
     */
    public ClientGUI(final Client newClient) {
        Client gameClient = newClient;
        gameClient.run(this);
        gamePlayer = gameClient.getPlayer();
        gameFrame = new JFrame("Chinese Checkers");
        updateTitle();
        gameBoardGUI = new BoardGUI();
        gameMouseListener = new MyMouseListener();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        ClientGUI appGUI = new ClientGUI(new Client());
        appGUI.setup();
        appGUI.play();
    }

    private void setup() {
        System.out.println("Setting up GUI");

        gameMouseListener.setBoardGUI(gameBoardGUI);
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

    /**
     * Gets Game BoardInterface gui.
     *
     * @return the Game BoardInterface gui
     */
    public JPanel getGameBoardGUI() {
        return gameBoardGUI;
    }

    /**
     * Update title.
     */
    public void updateTitle() {
        gameFrame.setTitle(gamePlayer.getColor()
                + " | ROUND: "
                + gamePlayer.getCurrentRound()); }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return gameFrame;
    }

    /**
     * Repaint.
     */
    public void repaint() {
        this.gameBoardGUI.repaint();
    }

}
