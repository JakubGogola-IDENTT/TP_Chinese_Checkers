package PolskiZwiazekWarcabowy.Client.GraphicBoard;

/**
 * Concrete builder for board for no players.
 */
public class GraphicBoardForNoPlayersBuilder extends AbstractGraphicBoardBuilder implements GraphicBoardBuilder {

    public GraphicBoardForNoPlayersBuilder () {
        super();
    }

    /**
     * This method does nothing.
     */
    @Override
    public void setPawnsForPlayers() {

    }
}
