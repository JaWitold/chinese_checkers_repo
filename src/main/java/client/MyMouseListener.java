package client;

import borad.boardBuilder.BoardGUI;
import borad.field.FieldInterface;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The type My mouse listener.
 */
public class MyMouseListener implements MouseListener {
    /**
     * Board panel.
     */
    private BoardGUI boardGUI;
    /**
     * Player.
     */
    private Player player;
    /**
     * The highlighted FieldInterface.
     */
    private FieldInterface highlighted;

    /**
     * Sets gui.
     *
     * @param newGUI the gui
     */
    public void setBoardGUI(final BoardGUI newGUI) {
        this.boardGUI = newGUI;
    }

    /**
     * Sets Player.
     *
     * @param newPlayer the Player
     */
    public void setPlayer(final Player newPlayer) {
        this.player = newPlayer;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
        if (player.getColor() == player.getCurrentRound()) {
            final int x = e.getX();
            final int y = e.getY();

            if (highlighted == null) {
               highlighted = boardGUI.getGUIField(x, y);
            } else {
                final FieldInterface destination = boardGUI.getGUIField(x, y);
                if (destination != null) {
                    String message = "MOVE:"
                            + highlighted.getColumn()
                            + "," + highlighted.getRow()
                            + "," + destination.getColumn()
                            + "," + destination.getRow();
                    System.out.println(message);
                    player.sendMessage(message);
                    highlighted = null;
                }
            }
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(final MouseEvent e) { }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(final MouseEvent e) { }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(final MouseEvent e) { }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(final MouseEvent e) { }
}
