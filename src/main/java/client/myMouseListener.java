package client;

import borad.boardBuilder.BoardGUI;
import borad.field.field;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class myMouseListener implements MouseListener {
    private int x, y;
    private BoardGUI GUI;
    private player player;
    private field highlighted;
    private field destination;

    public void setGUI(BoardGUI GUI) {
        this.GUI = GUI;
    }

    public void setPlayer(player player) {
        this.player = player;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(player.getColor() == player.currentRound) {
            x = e.getX();
            y = e.getY();

            if(highlighted == null) {
               highlighted = GUI.getGUIField(x, y);
            } else {
                destination = GUI.getGUIField(x, y);
                String message = "MOVE:" + highlighted.getColumn() +","+ highlighted.getRow()+","+destination.getColumn()+","+destination.getRow();
                System.out.println(message);
                player.sendMessage(message);
                highlighted = null;
                destination = null;
            }
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Mouse Pressed");
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("Mouse Released");
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("Mouse Entered");
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("Mouse Exited");
    }
}
