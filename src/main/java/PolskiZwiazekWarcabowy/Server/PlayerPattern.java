package PolskiZwiazekWarcabowy.Server;

import PolskiZwiazekWarcabowy.Colour;

/**
 * Interface for every player in the game.
 * Contains methods need for every player to take part in a game.
 */

public interface PlayerPattern {

    /**
     * Waits for message from player.
     * @return message from player.
     */
    String getMessage();

    /**
     * Sends message to player.
     * @param msg is message to send.
     */
    void sendMessage(String msg);

    /**
     * Sets player colour.
     * @param colour
     */
    void setPlayerColour(int colour);

    /**
     * Return information if player has won.
     * @return true if player has won, false otherwise.
     */
    boolean hasWon();

    /**
     * Getter for player colour.
     * @return player colour.
     */
    Colour getColour();

    /**
     * Sets hasWon field.
     * @param hasWon true when player is to win, false otherwise.
     */
    void setHasWon(boolean hasWon);



}
