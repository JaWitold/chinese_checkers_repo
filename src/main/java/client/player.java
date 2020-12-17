package client;

import borad.board;
import borad.boardBuilder.BoardGUI;
import borad.field.field;
import game.*;

public class player {
    private final color myColor;
    private final client myConnection;
    public color currentRound;
    private board myBoard;

    public player(color myNewColor, client client) {
        myColor = myNewColor;
        myConnection = client;
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
                System.out.println(currentRound);
                myBoard.movePawn(pawn.getPawn(sColumn, sRow, myBoard.getPawns()), field.getField(dColumn, dRow, myBoard.getFields()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void play() throws Exception {
        try {
            while(myConnection.socketInput.hasNextLine()) {
                final String response = myConnection.socketInput.nextLine();
                if(response.startsWith("MOVE:")) {
                    processCommand(response);
                    clientGUI GUI = (clientGUI) myConnection;
                    GUI.getPanel().repaint();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myConnection.socket.close();
        }
    }
}
