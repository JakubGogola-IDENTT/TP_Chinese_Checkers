package PolskiZwiazekWarcabowy.Client;

import javafx.scene.paint.Color;

/**
 * This class has method to handle actions on graphic represntation of board.
 */
public class Actions {

    /**
     * Is main window with game.
     */
    private GameWindow gameWindow;

    /**
     * Array representation of board.
     */
    private GraphicField[][] board;

    /**
     * Temporary field to remember reference.
     */
    private GraphicField tempField;

    public Actions (GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        board = gameWindow.getBoard();
    }

    /**
     * Makes move on graphical board.
     * @param row1 is row of pawn.
     * @param col1 is column of pawn.
     * @param row2 is row of destination.
     * @param col2 is column of destination.
     */
    public void doMove (int row1, int col1, int row2, int col2) {
        tempField = board[row1][col1];

        board[row2][col2].setIsPawn(true);
        board[row2][col2].setIsPlayerPawn(tempField.getIsPlayerPawn());
        board[row2][col2].setIsOpponentPawn(tempField.getIsOpponentPawn());
        board[row2][col2].setFill(tempField.getFill());

        tempField.setIsPawn(false);
        tempField.setIsPlayerPawn(false);
        tempField.setIsPlayerPawn(false);
        tempField.setFill(Color.KHAKI);
    }

}
