package client;

import borad.BoardInterface;
import borad.field.FieldInterface;
import game.*;

import javax.swing.*;

/**
 * The type Player.
 */
public class Player {
    /**
     * The Player's CustomColor.
     */
    private final CustomColor myColor;
    /**
     * The reference to Client.
     */
    private final Client myConnection;
    /**
     * GUI of app.
     */
    private final ClientGUI myGUI;
    /**
     * Current round.
     */
    private CustomColor currentRound;
    /**
     * Game BoardInterface.
     */
    private BoardInterface myBoard;

    /**
     * Instantiates a new Player.
     *
     * @param myNewColor the my new CustomColor
     * @param newClient  the new Client
     * @param newGUI     the new gui
     */
    public Player(
            final CustomColor myNewColor,
            final Client newClient,
            final ClientGUI newGUI) {
        myColor = myNewColor;
        myConnection = newClient;
        myGUI = newGUI;
    }

    /**
     * Sets BoardInterface.
     *
     * @param newBoard the new BoardInterface
     */
    public void setBoard(final BoardInterface newBoard) {
        myBoard = newBoard;
    }

    /**
     * Gets BoardInterface.
     *
     * @return the BoardInterface
     */
    public BoardInterface getBoard() {
        return myBoard;
    }

    /**
     * Gets CustomColor.
     *
     * @return the CustomColor
     */
    public CustomColor getColor() {
        return myColor;
    }

    /**
     * Send message.
     *
     * @param message the message
     */
    public void sendMessage(final String message) {
        myConnection.send(message);
    }

    /**
     * Process command.
     *
     * @param message the message
     */
    public void processCommand(String message) {
        if (message.startsWith("MOVE:")) {
            try {
                message = message.substring(message.indexOf(":") + 1);
                int sColumn = Integer.parseInt(
                        message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1);
                int sRow = Integer.parseInt(
                        message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1);
                int dColumn = Integer.parseInt(
                        message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1);
                int dRow = Integer.parseInt(
                        message.substring(0, message.indexOf(";")));
                message = message.substring(message.indexOf(":") + 1);
                currentRound = CustomColor.valueOf(message);
                myBoard.movePawn(
                        DefaultPawn.getPawn(sColumn, sRow, myBoard.getPawns()),
                        FieldInterface.getField(
                                dColumn,
                                dRow,
                                myBoard.getFields()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Play.
     */
    public void play() {
        try {
            while (myConnection.hasMessage()) {
                final String response = myConnection.readMessage();
                if (response.startsWith("MOVE:")) {
                    processCommand(response);
                    myGUI.repaint();
                    myGUI.updateTitle();
                } else if (response.startsWith("QUIT")) {
                    JOptionPane.showMessageDialog(
                            myGUI.getFrame(), "OTHER PLAYER LEFT"
                    );
                    break;
                } else if (response.startsWith("WON")) {
                    final String message = response.substring(response.indexOf(":") + 1);
                    JOptionPane.showMessageDialog(
                            myGUI.getFrame(),
                            message + " has won!"
                    );
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myConnection.send("QUIT");
            System.exit(0);
        }
    }

    /**
     * Gets current round.
     *
     * @return the current round
     */
    public CustomColor getCurrentRound() {
        return currentRound;
    }

    /**
     * Sets current round.
     *
     * @param newRound the new round
     */
    public void setCurrentRound(final CustomColor newRound) {
        currentRound = newRound;
    }
}
