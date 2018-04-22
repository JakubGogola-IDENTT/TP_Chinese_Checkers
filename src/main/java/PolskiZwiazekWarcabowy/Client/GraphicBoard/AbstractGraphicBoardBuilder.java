package PolskiZwiazekWarcabowy.Client.GraphicBoard;

import PolskiZwiazekWarcabowy.Client.ColoursConnections;
import PolskiZwiazekWarcabowy.Client.GraphicField;

/**
 * Common class for all concrete builders of graphic board.
 */
public abstract class AbstractGraphicBoardBuilder implements GraphicBoardBuilder {

    /**
     * Graphic representation of board.
     */
    protected GraphicField[][] board;

    /**
     * This variable is instance of colours connections map.
     */
    protected ColoursConnections coloursConnections;

    public AbstractGraphicBoardBuilder () {
        board = new GraphicField[19][29];
        coloursConnections = ColoursConnections.getInstance();
    }

    /**
     * This method returns array representation of board.
     * @return array representation of board.
     */
    @Override
    public GraphicField[][] getBoard() {
        return board;
    }

    /**
     * Filling board with nulls.
     */
    @Override
    public void fillBoardWithNulls() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }
    }

    /**
     * This method builds board.
     */
    @Override
    public void buildBoard() {
        int numberOfFields;
        int firstPosition = 2;
        int colour = 0;

        //Building main triangle.
        for(int i = 13; i >= 1; i--) {
            numberOfFields = i;
            for(int j = firstPosition; j < board[i].length; j += 2) {
                if(numberOfFields == 0) {
                    break;
                }
                board[i][j] = new GraphicField(15);
                board[i][j].setFill(coloursConnections.get(colour));
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
                    board[i][j] = new GraphicField(15);
                    board[i][j].setFill(coloursConnections.get(colour));
                }
                numberOfFields--;
            }
            firstPosition++;
            numberOfFields = 13 - firstPosition + 2;
        }
    }
}
