package client;

import borad.board;
import borad.boardBuilder.BoardGUI;
import borad.field.field;
import game.*;

import javax.swing.*;
import java.io.IOException;

public class player {
    private final color myColor;
    private final client myConnection;
    private final clientGUI myGUI;
    private color currentRound;
    private board myBoard;

    public player(color myNewColor, client newClient, clientGUI newGUI) {
        myColor = myNewColor;
        myConnection = newClient;
        myGUI = newGUI;
    }

    public void setBoard(board newBoard) {
        myBoard = newBoard;
    }
    public board getBoard() { return myBoard; }
    public color getColor() {
        return myColor;
    }

    public void sendMessage(String message){
        myConnection.socketOutput.println(message);
    }

    public void processCommand(String message) {
        if(message.startsWith("MOVE:")) {
            try {
                message = message.substring(message.indexOf(":") + 1);
                int sColumn = Integer.parseInt(message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1);
                int sRow = Integer.parseInt(message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1);
                int dColumn = Integer.parseInt(message.substring(0, message.indexOf(",")));
                message = message.substring(message.indexOf(",") + 1);
                int dRow = Integer.parseInt(message.substring(0, message.indexOf(";")));
                message = message.substring(message.indexOf(":") + 1);
                currentRound = color.valueOf(message);
                myBoard.movePawn(pawn.getPawn(sColumn, sRow, myBoard.getPawns()), field.getField(dColumn, dRow, myBoard.getFields()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void play() {
        try {
            while(myConnection.socketInput.hasNextLine()) {
                final String response = myConnection.socketInput.nextLine();
                if(response.startsWith("MOVE:")) {
                    processCommand(response);
                    myGUI.repaint();
                    myGUI.updateTitle();
                } else if (response.startsWith("QUIT")) {
                    JOptionPane.showMessageDialog(myGUI.getFrame(), "OTHER PLAYER LEFT");
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                myConnection.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public color getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(color newRound) {
        currentRound = newRound;
    }
}
