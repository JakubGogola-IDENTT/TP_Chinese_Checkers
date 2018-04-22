package PolskiZwiazekWarcabowy.Board;

import PolskiZwiazekWarcabowy.Pawn;

/**
 * Concrete builder for board for two players.
 */
public class HexagonalBoardForTwoPlayersBuilder extends AbstractHexagonalBoardBuilder implements BoardBuilder {

    public HexagonalBoardForTwoPlayersBuilder () {
        super();
    }

    /**
     * This method sets pawns on map for two players.
     */
    @Override
    public void setPawns() {

        int colour;

        //Setting pawns in the North triangle.
        colour = 1;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setPawn(new Pawn(colour));
                    pawns.add(board[i][j].getPawn());
                }
            }
        }
        //Setting pawns in the Southern triangle.
        colour = 4;
        for (int i = 14; i <= 17; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setPawn(new Pawn(colour));
                    pawns.add(board[i][j].getPawn());
                }
            }
        }
    }
}
