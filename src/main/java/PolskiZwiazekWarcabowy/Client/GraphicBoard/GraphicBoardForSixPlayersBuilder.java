package PolskiZwiazekWarcabowy.Client.GraphicBoard;

/**
 * Concrete builder for board for six players.
 */
public class GraphicBoardForSixPlayersBuilder extends AbstractGraphicBoardBuilder implements GraphicBoardBuilder {


    public GraphicBoardForSixPlayersBuilder () {
        super();
    }

    /**
     * Sets pawns on board.
     */
    @Override
    public void setPawnsForPlayers() {
        int tempColour;
        int position;

        //Calling method for 2 players.
        tempColour = 1;

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

        //Setting pawns to the South-Eastern triangle.
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

        //Setting pawns to the North-Western triangle.
        tempColour = 6;
        position = 5;
        for (int i = 8; i >= 5; i--) {
            for (int j = position; j >= 0; j--) {
                if (board[i][j] != null) {
                    board[i][j].setFill(coloursConnections.get(tempColour));
                    board[i][j].setIsPawn(true);
                }
            }
            position++;
        }

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

        //Setting pawns to the North-Eastern triangle.
        tempColour = 2;
        position = 20;
        for (int i = 5; i <= 8; i++) {
            for (int j = position; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setFill(coloursConnections.get(tempColour));
                    board[i][j].setIsPawn(true);
                }
            }
            position++;
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
