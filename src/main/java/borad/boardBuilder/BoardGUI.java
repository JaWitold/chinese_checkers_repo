package borad.boardBuilder;

import borad.board;
import borad.field.field;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class BoardGUI extends JPanel{
    private static final float radius = 20;
    private float offsetX;
    private float offsetY;

    private static int pawnSize = 1;
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
            System.out.println("rysuje");
            Graphics2D graphic = (Graphics2D) g;
            graphic.setPaint(Color.BLUE);
            for (field fld : boardToDraw.getFields()) {
                offsetX = (float) (fld.getColumn() * 20.0 + 300.0);
                offsetY = (float) (fld.getRow() * 25.0 + 220);
                graphic.fill(new Ellipse2D.Float(offsetX, offsetY, radius, radius));
            }
        }
//        Graphics2D graphic = (Graphics2D) g;
//        graphic.setPaint(Color.BLUE);
//        graphic.fill(new Ellipse2D.Float((float) 2, (float) 7, radius, radius));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
