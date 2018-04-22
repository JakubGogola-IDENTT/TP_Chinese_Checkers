package PolskiZwiazekWarcabowy.Client.GraphicBoard;

/**
 * Concrete builder for board for two players.
 */
public class GraphicBoardForTwoPlayersBuilder extends AbstractGraphicBoardBuilder implements GraphicBoardBuilder {


    public GraphicBoardForTwoPlayersBuilder () {
        super();
    }

    /**
     * Sets pawns on board.
     */
    @Override
    public void setPawnsForPlayers() {
        int tempColour = 1;

        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setFill(coloursConnections.get(tempColour));
                    board[i][j].setIsPawn(true);
                }
            }
        }

        tempColour = 4;
        //Setting pawns in the Southern triangle.
        for (int i = 14; i <= 17; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setFill(coloursConnections.get(tempColour));
                    board[i][j].setIsPawn(true);
                }
            }
        }
    }
}
