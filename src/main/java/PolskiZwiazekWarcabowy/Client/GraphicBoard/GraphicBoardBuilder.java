package PolskiZwiazekWarcabowy.Client.GraphicBoard;

import PolskiZwiazekWarcabowy.Client.GraphicField;

/**
 *  Is common interface to all concrete builders.
 */
public interface GraphicBoardBuilder {

    GraphicField[][] getBoard();

    void fillBoardWithNulls ();

    void buildBoard();

    void setPawnsForPlayers();

}
