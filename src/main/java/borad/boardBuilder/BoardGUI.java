package borad.boardBuilder;

import borad.board;
import borad.field.field;
import game.color;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;


public class BoardGUI extends JPanel {
    private static final float radius = 30;
    private float offsetX;
    private float offsetY;

    private static int pawnSize = 20;
    private board boardToDraw;

    public BoardGUI() {
        super();
        setBounds(0, 0, 640, 480);
        setBackground(Color.darkGray);
        setVisible(true);
    }

    public void setBoardToDraw(board newBoard) {
        boardToDraw = newBoard;
    }

    public void draw(Graphics g) {
        if(boardToDraw != null) {
            System.out.println("drawing the board");
            Graphics2D graphic = (Graphics2D) g;
            //antialiasing
            RenderingHints hints = new RenderingHints (
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            graphic.setRenderingHints(hints);

            for (field fld : boardToDraw.getFields()) {

                graphic.setPaint(translateColor(fld.getColor()));
                offsetX = (float) (fld.getColumn() * 20.0 + 320.0);
                offsetY = (float) (fld.getRow() * 25.0 + 240);
                graphic.setPaint(translateColor(fld.getColor()));
                graphic.fill(new Ellipse2D.Float(offsetX - radius/2, offsetY - radius/2, radius, radius));
                if(fld.getPawn() != null) {
                    graphic.setPaint(translateColor(fld.getPawn().getColor()));
                    graphic.fill(new Ellipse2D.Float(offsetX - pawnSize/2, offsetY - pawnSize/2, pawnSize, pawnSize));
                    graphic.setPaint(Color.WHITE);
                    graphic.draw(new Ellipse2D.Float(offsetX - pawnSize/2, offsetY - pawnSize/2, pawnSize, pawnSize));
                }
                graphic.setPaint(Color.BLACK);
                graphic.drawString(fld.getColumn() + " " + fld.getRow(), offsetX - pawnSize/3, offsetY);

            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public Color translateColor(color col) {
        return switch (col){
            case RED -> new Color(230, 99,96);
            case BLUE -> new Color(96, 194,230);
            case YELLOW -> new Color(227, 230,96);
            case GREEN -> new Color(96, 230,99);
            case ORANGE -> new Color(230, 139,96);
            case PURPLE -> new Color(230, 96,227);
            case NULL -> Color.WHITE;
        };
    }

    public field getGUIField(int x, int y) {
        field tmp = null;
        double min = Integer.MAX_VALUE;
        for (field fld : boardToDraw.getFields()) {
            offsetX = (float) (fld.getColumn() * 20.0 + 320.0);
            offsetY = (float) (fld.getRow() * 25.0 + 240);
            //System.out.println(offsetX + " " + offsetY + " "+ x + " " + y);
            double d = Math.sqrt(Math.pow(Math.abs(offsetX - x), 2) + Math.pow(Math.abs(offsetY - y + 30), 2));

            if(d < min) {
                tmp = fld;
                min = d;
            }
        }
        System.out.println("CLIECKED: " + tmp.getColumn() + " " + tmp.getRow());
        return tmp;
    }
}
