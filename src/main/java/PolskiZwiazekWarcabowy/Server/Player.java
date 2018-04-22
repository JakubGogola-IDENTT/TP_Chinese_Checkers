package PolskiZwiazekWarcabowy.Server;

import PolskiZwiazekWarcabowy.Colour;

/**
 * Abstract class for player in a game, contains basic getters and setters for players.
 */
public abstract class Player  extends Thread implements PlayerPattern {


    /**
     * Message from client.
     */
    String message;

    /**
     * Instance of current game.
     */
    Game game;

    /**
     * Information if player has already won the game.
     */
    private boolean hasWon;

    /**
     * Colour of the player in a game.
     */
    private Colour playerColour;

    public void run () {
        while(isAlive()) {
            Thread.yield();
       }
    }

    /**
     * Sets player colour.
     * @param playerColour is int value of player colour.
     */
    public void setPlayerColour(int playerColour) {
        this.playerColour = new Colour(playerColour);
    }


    /**
     * Getter for player colour.
     * @return player colour.
     */
    public Colour getColour() {
        return this.playerColour;
    }

    /**
     * Returns information if player has won.
     * @return true when player won, false otherwise.
     */
    public boolean hasWon() {
        return hasWon;
    }

    /**
     * Sets value of hasWon value.
     * @param hasWon true when player is to win, false otherwise.
     */
    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

}
