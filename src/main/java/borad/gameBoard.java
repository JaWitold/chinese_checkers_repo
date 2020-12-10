package borad;

import borad.field.field;
import borad.field.gameField;

import java.util.ArrayList;
import java.util.List;

/**
 * this is class that makes whole borad.board for this game in standard game mode
 * vectors:
 * a = ( 2, 0), b = (1,-1), c = (-1,-1),
 * d = (-2, 0), e = (-1,1), f = ( 1, 1)
 * vertices:
 * p1 = (0,  8), p2 = ( 12,  4), p3 = (12, -4),
 * p4 = (0, -8), p5 = (-12, -4), p6 = (-12, 4)
 */
public class gameBoard implements board {
    public List<field> fieldsList;
    private final int[][] vectors = {{2, 0}, {1, -1}, {-1, -1}, {-2, 0}, {-1, 1}, {1, 1}};

    /**
     * default constructor of the borad.board
     */
    gameBoard() {
        fieldsList = new ArrayList<>();
        this.generate();
    }

    /**
     * generates borad.board
     */
    @Override
    public void generate() {
        //build all edges
        //set vertices      {{ p5,  p6}, { p1,  p6}, { p5, p4}}
        int[][] verticesX = {{-12, -12}, {  0, -12}, {-12,  0}};
        int[][] verticesY = {{ -4,   4}, {  8,   4}, { -4, -8}};
        //set vectors       {{ ax,  ay}, { bx,  by}, { fx, fy}}
        int[][] vectors =   {{  2,   0}, { 1,   -1}, {  1,  1}};

        for(int i = 0; i < 13; i++) {//for each line
            for(int j = 0; j < 3; j++) {//for each vector
                for(int k = 0; k < 2; k++) {//for each vertex
                    //vector = (vectors[j][0], vectors[j][1])
                    int col = verticesX[j][k] + vectors[j][0] * i;
                    int row = verticesY[j][k] + vectors[j][1] * i;

                    if(!field.exists(new gameField(col, row), fieldsList) && (i < 5 || i > 7)) {
                        fieldsList.add(new gameField(col, row, true));
                    }
                }
            }
        }
        //for debuging
        //fieldsList.forEach(borad.field.field -> System.out.println("col "+ borad.field.field.getColumn() + "\trow " + borad.field.field.getRow() + "\tedge " + borad.field.field.isEdge()));
        //System.out.println(fieldsList.size());

        //set all neighbors

        field tmp = new gameField(0,0);
        generateNeighbors(tmp);

        //for debuging
        //fieldsList.forEach(borad.field.field -> System.out.println("col "+ borad.field.field.getColumn() + "\trow " + borad.field.field.getRow() + "\tedge " + borad.field.field.isEdge()));
        //System.out.println(fieldsList.size());
    }


    /**
     * adds all valid neighbors
     * @param startingField is borad.field.field that algorithm starts at
     */
    @Override
    public void generateNeighbors(field startingField) {
        fieldsList.add(startingField);
        for(int i = 0; i < 6; i++) {
            int newColumn = vectors[i][0];
            int newRow = vectors[i][1];

            if(!field.exists(new gameField(startingField.getColumn() + newColumn, startingField.getRow() + newRow), fieldsList)){
                field tmp = new gameField(startingField.getColumn() + newColumn, startingField.getRow() + newRow);
                startingField.addNeighbor(tmp);
                tmp.addNeighbor(startingField);
                generateNeighbors(tmp);
            }

        }

    }
}