package PolskiZwiazekWarcabowy.Board;

import PolskiZwiazekWarcabowy.Colour;
import PolskiZwiazekWarcabowy.Field;
import PolskiZwiazekWarcabowy.Pawn;

import java.util.ArrayList;

/**
 * This interface could be implemented to new types of board.
 */
public interface BoardPattern {

    /**
     * This method return array representation of baord.
     * @return
     */
    Field[][] getMap();

    /**
     * This method checks if moving pawn is allowed.
     * @param r1 row of first pawn.
     * @param c1 column of first pawn.
     * @param r2 row of second pawn.
     * @param c2 column of second pawn.
     * @param isFirstMove true if it is first move.
     * @return true if move is allowed, oth. false.
     */
    boolean movePawn(int r1, int c1, int r2, int c2, boolean isFirstMove);

    /**
     * This method sets pawn on concrete field.
     * @param pawn is pawn to set.
     * @param r is row.
     * @param c is column.
     */
    void setPawn(Pawn pawn, int r, int c);

    /**
     * Returns pawn from concrete field.
     * @param r is row.
     * @param c is column.
     * @return pawn from field.
     */
    Pawn getPawn(int r, int c);

    /**
     * Checks if player won.
     * @param colour is Colour of player.
     * @return true if player won, oth. false.
     */
    boolean hasPlayerWon(Colour colour);

    /**
     * Checks if pawn was moved to neighbour field.
     * @param hasPawnMovedToNeighbour is value of move.
     */
    void setHasPawnMovedToNeighbour(boolean hasPawnMovedToNeighbour);

    /**
     * This method return list of pawns.
     * @return list of pawns.
     */
    ArrayList<Pawn> getPawns();

}
