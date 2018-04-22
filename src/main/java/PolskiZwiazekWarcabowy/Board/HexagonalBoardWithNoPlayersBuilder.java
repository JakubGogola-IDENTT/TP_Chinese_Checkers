package PolskiZwiazekWarcabowy.Board;

/**
 *
 * Builder that creates board without players, used for tests.
 */
public class HexagonalBoardWithNoPlayersBuilder extends AbstractHexagonalBoardBuilder implements BoardBuilder {

    public HexagonalBoardWithNoPlayersBuilder () {
        super();
    }

    /**
     * This method sets pawns on map for two players.
     */
    @Override
    public void setPawns() { }

}
