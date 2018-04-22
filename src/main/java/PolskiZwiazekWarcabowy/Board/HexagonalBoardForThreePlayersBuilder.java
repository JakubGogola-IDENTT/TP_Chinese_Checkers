package PolskiZwiazekWarcabowy.Board;

import PolskiZwiazekWarcabowy.Pawn;

/**
 * Concrete builder for board for three players.
 */
public class HexagonalBoardForThreePlayersBuilder extends AbstractHexagonalBoardBuilder implements BoardBuilder {

    public HexagonalBoardForThreePlayersBuilder () {
        super();
    }

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

        //Setting pawns to the South-Western triangle.
        colour = 5;
        position = 8;
        for (int i = 13; i >= 10; i--) {
            for (int j = position; j >= 0; j--) {
                if (board[i][j] != null) {
                    board[i][j].setPawn(new Pawn(colour));
                    pawns.add(board[i][j].getPawn());
                }
            }
            position--;
        }
    }
}
