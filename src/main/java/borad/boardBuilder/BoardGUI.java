package borad.boardBuilder;

import borad.BoardInterface;
import borad.field.FieldInterface;
import game.CustomColor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;


/**
 * The type Board gui.
 */
public class BoardGUI extends JPanel {
    /**
     * Radius of the FieldInterface.
     */
    private static final float FIELD_SIZE = 30;
    /**
     * Radius of the FieldInterface.
     */
    private static final int PAWN_SIZE = 20;
    /**
     * The real BoardInterface.
     */
    private BoardInterface boardToDraw;

    /**
     * Instantiates a new Board gui.
     */
    public BoardGUI() {
        super();
        setBounds(0, 0, 640, 480);
        setBackground(Color.darkGray);
        setVisible(true);
    }

    /**
     * Sets BoardInterface to draw.
     *
     * @param newBoard the new BoardInterface
     */
    public void setBoardToDraw(final BoardInterface newBoard) {
        boardToDraw = newBoard;
    }

    /**
     * Draw the BoardInterface.
     *
     * @param g the g
     */
    public void draw(final Graphics g) {
        if (boardToDraw != null) {
            System.out.println("drawing the Board");
            Graphics2D graphic = (Graphics2D) g;
            //antialiasing
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            graphic.setRenderingHints(hints);

            for (FieldInterface fld : boardToDraw.getFields()) {

                graphic.setPaint(translateColor(fld.getColor()));
                final float offsetX = (float) (fld.getColumn() * 20.0 + 320.0);
                final float offsetY = (float) (fld.getRow() * 25.0 + 240);
                graphic.setPaint(translateColor(fld.getColor()));
                graphic.fill(new Ellipse2D.Float(offsetX - FIELD_SIZE / 2,
                        offsetY - FIELD_SIZE / 2,
                        FIELD_SIZE,
                        FIELD_SIZE));
                if (fld.getPawn() != null) {
                    graphic.setPaint(translateColor(fld.getPawn().getColor()));
                    graphic.fill(new Ellipse2D.Float(
                            offsetX - (PAWN_SIZE / 2),
                            offsetY - (PAWN_SIZE / 2),
                            PAWN_SIZE,
                            PAWN_SIZE));
                    graphic.setPaint(Color.WHITE);
                    graphic.draw(new Ellipse2D.Float(
                            offsetX - (PAWN_SIZE / 2),
                            offsetY - (PAWN_SIZE / 2),
                            PAWN_SIZE,
                            PAWN_SIZE));
                }
                graphic.setPaint(Color.BLACK);
                graphic.drawString(fld.getColumn() + " " + fld.getRow(),
                        offsetX - (PAWN_SIZE / 3),
                        offsetY);
            }
        }
    }
    /**
     * This is paint Component.
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Translate CustomColor to Color.
     *
     * @param col the col
     * @return the CustomColor
     */
    public Color translateColor(final CustomColor col) {
        return switch (col) {
            case RED -> new Color(230, 99, 96);
            case BLUE -> new Color(96, 194, 230);
            case YELLOW -> new Color(227, 230, 96);
            case GREEN -> new Color(96, 230, 99);
            case ORANGE -> new Color(230, 139, 96);
            case PURPLE -> new Color(230, 96, 227);
            case NULL -> Color.WHITE;
        };
    }

    /**
     * Gets gui FieldInterface.
     *
     * @param x the x
     * @param y the y
     * @return the gui FieldInterface
     */
    public FieldInterface getGUIField(final int x, final int y) {
        FieldInterface tmp = null;
        double min = Integer.MAX_VALUE;
        for (FieldInterface fld : boardToDraw.getFields()) {
            final float offsetX = (float) (fld.getColumn() * 20.0 + 320.0);
            final float offsetY = (float) (fld.getRow() * 25.0 + 240);
            //System.out.println(offsetX + " " + offsetY + " "+ x + " " + y);
            double d = Math.sqrt(Math.pow(Math.abs(offsetX - x), 2)
                    + Math.pow(Math.abs(offsetY - y + 30), 2));
            if (d < min) {
                tmp = fld;
                min = d;
            }
        }
        return tmp;
    }
}
