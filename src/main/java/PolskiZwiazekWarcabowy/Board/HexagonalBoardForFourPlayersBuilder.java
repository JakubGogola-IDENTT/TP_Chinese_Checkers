package PolskiZwiazekWarcabowy.Board;

import PolskiZwiazekWarcabowy.Pawn;

/**
 * Concrete builder for board for four players.
 */
public class HexagonalBoardForFourPlayersBuilder extends AbstractHexagonalBoardBuilder implements BoardBuilder {

    public HexagonalBoardForFourPlayersBuilder () {
        super();
    }

    /**
     * This method sets pawns on map for four players.
     */
    @Override
    public void setPawns() {

        int colour;
        int position;

        //Setting pawns to the North triangle.
        colour = 1;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setPawn(new Pawn(colour));
                    pawns.add(board[i][j].getPawn());
                }
            }
        }


        //Setting pawns to the South-Eastern triangle.
        colour = 3;
        position = 23;
        for (int i = 10; i <= 13; i++) {
            for (int j = position; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setPawn(new Pawn(colour));
                    pawns.add(board[i][j].getPawn());
                }
            }
            position--;
        }

        //Setting pawns to the Southern triangle.
        colour = 4;
        for (int i = 14; i <= 17; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setPawn(new Pawn(colour));
                    pawns.add(board[i][j].getPawn());
                }
            }
        }

        //Setting pawns to the North-Western triangle.
        colour = 6;
        position = 5;
        for (int i = 8; i >= 5; i--) {
            for (int j = position; j >= 0; j--) {
                if (board[i][j] != null) {
                    board[i][j].setPawn(new Pawn(colour));
                    pawns.add(board[i][j].getPawn());
                }
            }
            position++;
        }
    }
}
