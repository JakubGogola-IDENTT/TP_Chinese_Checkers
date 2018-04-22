package PolskiZwiazekWarcabowy.Client.GraphicBoard;

/**
 * Concrete builder for board for three players.
 */
public class GraphicBoardForThreePlayersBuilder extends AbstractGraphicBoardBuilder implements GraphicBoardBuilder {


    public GraphicBoardForThreePlayersBuilder () {
        super();
    }

    /**
     * Sets pawns on board.
     */
    @Override
    public void setPawnsForPlayers() {
        int tempColour = 1;
        int position;

        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setFill(coloursConnections.get(tempColour));
                    board[i][j].setIsPawn(true);
                }
            }
        }

        tempColour = 3;
        position = 23;
        for (int i = 10; i <= 13; i++) {
            for (int j = position; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setFill(coloursConnections.get(tempColour));
                    board[i][j].setIsPawn(true);
                }
            }
            position--;
        }

        //Setting pawns to the South-Western triangle.
        tempColour = 5;
        position = 8;
        for (int i = 13; i >= 10; i--) {
            for (int j = position; j >= 0; j--) {
                if (board[i][j] != null) {
                    board[i][j].setFill(coloursConnections.get(tempColour));
                    board[i][j].setIsPawn(true);
                }
            }
            position--;
        }
    }
}
