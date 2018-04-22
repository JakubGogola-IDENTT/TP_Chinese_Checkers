package PolskiZwiazekWarcabowy.Board;

import PolskiZwiazekWarcabowy.Field;
import PolskiZwiazekWarcabowy.Pawn;

import java.util.ArrayList;

/**
 * This class builds board.
 */
public class BoardAssembler {

    public BoardAssembler() {

    }

    /**
     * This method builds board which matches to concrete type of board.
     * @param boardBuilder is type of board.
     * @return array which is representation of board.
     */
    public Field[][] getBoard(BoardBuilder boardBuilder) {
        boardBuilder.fillArrayOfFieldsWithNulls();
        boardBuilder.buildBoard();
        boardBuilder.setColoursToTriangles();
        boardBuilder.setPawns();
        return boardBuilder.getBoard();
    }

    /**
     * This method return list of pawns on board.
     * @param boardBuilder is type of board.
     * @return list of pawns.
     */
    public ArrayList<Pawn> getPawns(BoardBuilder boardBuilder) {
        return boardBuilder.getPawns();
    }


}
