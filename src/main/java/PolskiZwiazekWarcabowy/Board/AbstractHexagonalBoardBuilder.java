package PolskiZwiazekWarcabowy.Board;

import PolskiZwiazekWarcabowy.Field;
import PolskiZwiazekWarcabowy.Pawn;

import java.util.ArrayList;

/**
 * This class is common to all builders of board.
 */
public abstract class AbstractHexagonalBoardBuilder implements BoardBuilder {

    /**
     * Array representation of board.
     */
    protected Field[][] board;

    /**
     * List of pawns on board.
     */
    protected ArrayList<Pawn> pawns;

    /**
     * This constructor initializes array with fields and list with pawns.
     */
    public AbstractHexagonalBoardBuilder () {
        board = new Field[19][29];
        pawns = new ArrayList<>();
    }

    /**
     * This method prepares board to fill with fields.
     */
    public void fillArrayOfFieldsWithNulls() {
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }
    }

    /**
     * This method returns list with pawns.
     * @return list with pawns.
     */
    @Override
    public ArrayList<Pawn> getPawns() {
        return pawns;
    }

    /**
     * This method returns board.
     * @return board.
     */
    @Override
    public Field[][]  getBoard() {
        return board;
    }

    /**
     * This method builds board.
     */
    @Override
    public void buildBoard() {
        int numberOfFields;
        int firstPosition = 2;

        //Building main triangle.
        for(int i = 13; i >= 1; i--) {
            numberOfFields = i;
            for(int j = firstPosition; j < board[i].length; j += 2) {
                if(numberOfFields == 0) {
                    break;
                }
                board[i][j] = new Field(i,j);
                board[i][j].setColour(0);
                numberOfFields--;
            }
            firstPosition++;
        }

        firstPosition = 2;
        numberOfFields = 13;
        for (int i = 5; i < board.length - 1; i++) {
            for(int j = firstPosition; j < board[i].length; j += 2) {
                if(numberOfFields == 0) {
                    break;
                }
                if(board[i][j] == null) {
                    board[i][j] = new Field(i,j);
                    board[i][j].setColour(0);
                }
                numberOfFields--;
            }
            firstPosition++;
            numberOfFields = 13 - firstPosition + 2;
        }
    }

    /**
     * This method sets colours to specified triangles.
     */
    @Override
    public void setColoursToTriangles() {
        int colour;
        int position;

        //Setting colour to North triangle.
        colour = 1;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setColour(colour);
                }
            }
        }

        //Setting colour to the North-Eastern triangle.
        colour = 2;
        position = 20;
        for (int i = 5; i <= 8; i++) {
            for (int j = position; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setColour(colour);
                }
            }
            position++;
        }

        //Setting colour to the South-Eastern triangle.
        colour = 3;
        position = 23;
        for (int i = 10; i <= 13; i++) {
            for (int j = position; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setColour(colour);
                }
            }
            position--;
        }

        //Setting colour to the Southern triangle.
        colour = 4;
        for (int i = 14; i <= 17; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setColour(colour);
                }
            }
        }

        //Setting colour to the South-Western triangle.
        colour = 5;
        position = 8;
        for (int i = 13; i >= 10; i--) {
            for (int j = position; j >= 0; j--) {
                if (board[i][j] != null) {
                    board[i][j].setColour(colour);
                }
            }
            position--;
        }
        //Setting colour to the North-Western triangle.
        colour = 6;
        position = 5;
        for (int i = 8; i >= 5; i--) {
            for (int j = position; j >= 0; j--) {
                if (board[i][j] != null) {
                    board[i][j].setColour(colour);
                }
            }
            position++;
        }
    }
}
