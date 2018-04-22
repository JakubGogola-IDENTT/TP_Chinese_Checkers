package PolskiZwiazekWarcabowy.Board;

import PolskiZwiazekWarcabowy.Field;
import PolskiZwiazekWarcabowy.Pawn;

import java.util.ArrayList;


/**
 * Interface of builders.
 */
public interface BoardBuilder {

    /**
     * This method returns array with board.
     * @return array with board.
     */
    Field[][] getBoard();

    /**
     * This method builds board.
     */
    void buildBoard();

    /**
     * This method prepares board to creation.
     */
    void fillArrayOfFieldsWithNulls();

    /**
     * This method sets colours to triangles.
     */
    void setColoursToTriangles();

    /**
     * This method set pawns on concrete fields.
     */
    void setPawns();

    /**
     * This method return list of pawns.
     * @return list of pawns.
     */
    ArrayList<Pawn> getPawns();

}
