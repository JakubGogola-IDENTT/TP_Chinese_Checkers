package PolskiZwiazekWarcabowy.Client.GraphicBoard;

import PolskiZwiazekWarcabowy.Client.GraphicField;

/**
 * This class is main representation of GraphicBoard. Implements pattern of graphic board.
 */
public class GraphicBoard implements GraphicBoardPattern{

    /**
     * Is array representation of board.
     */
    private GraphicField[][] board;

    /**
     * Is concrete instance of graphic board builder.
     */
    private GraphicBoardBuilder graphicBoardBuilder;

    /**
     * Is instance of board assembler.
     */
    private GraphicBoardAssembler graphicBoardAssembler;

    public GraphicBoard (int numberOfPlayers) {
        initialize(numberOfPlayers);
    }

    /**
     * Initialization of needed board.
     * @param numberOfPlayers is number of players on board.
     */
    private void initialize (int numberOfPlayers) {
        switch (numberOfPlayers) {
            case 0:
                graphicBoardBuilder = new GraphicBoardForNoPlayersBuilder();
                break;
            case 2:
                graphicBoardBuilder = new GraphicBoardForTwoPlayersBuilder();
                break;
            case 3:
                graphicBoardBuilder = new GraphicBoardForThreePlayersBuilder();
                break;
            case 4:
                graphicBoardBuilder = new GraphicBoardForFourPlayersBuilder();
                break;
            case 6:
                graphicBoardBuilder = new GraphicBoardForSixPlayersBuilder();
                break;

        }
        graphicBoardAssembler = new GraphicBoardAssembler();
        board = graphicBoardAssembler.getBoard(graphicBoardBuilder);
    }

    /**
     * Return array representation of board.
     * @return array representation of board.
     */
    @Override
    public GraphicField[][] getBoard() {
        return board;
    }
}
