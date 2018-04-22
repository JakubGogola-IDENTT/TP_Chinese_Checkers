package PolskiZwiazekWarcabowy.Client.GraphicBoard;

import PolskiZwiazekWarcabowy.Client.GraphicField;

/**
 * Assembles board.
 */
public class GraphicBoardAssembler {

    public  GraphicBoardAssembler () {

    }

    public GraphicField[][] getBoard (GraphicBoardBuilder graphicBoardBuilder) {
        graphicBoardBuilder.fillBoardWithNulls();
        graphicBoardBuilder.buildBoard();
        graphicBoardBuilder.setPawnsForPlayers();
        return graphicBoardBuilder.getBoard();
    }
}
