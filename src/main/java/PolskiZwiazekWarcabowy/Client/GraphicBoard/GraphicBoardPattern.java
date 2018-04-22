package PolskiZwiazekWarcabowy.Client.GraphicBoard;

import PolskiZwiazekWarcabowy.Client.GraphicField;

/**
 * Is pattern to every new type of board.
 */
public interface GraphicBoardPattern {

    /**
     * returns array representation of board.
     * @return array representation of board.
     */
    GraphicField[][] getBoard();
}
