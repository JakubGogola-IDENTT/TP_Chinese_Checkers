package PolskiZwiazekWarcabowy.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class GameClient {

    /**
     * Socket to communicate with sever.
     */
    private static Socket socket;

    /**
     * Reader to read messages from server.
     */
    private static BufferedReader input;

    /**
     * Writer to send messages to server.
     */
    private static PrintWriter output;

    /**
     * Player's colour.
     */
    private int playerColour;

    /**
     * Thread which starts server listener.
     */
    private Thread serverListener;

    /**
     * Number of players.
     */
    private String numberOfPlayers;

    /**
     * True if it is player's round, oth. false.
     */
    private boolean isPlayerRound;

    /**
     * Constructor for creating new game.
     * @param numberOfPlayers is number of players in new game.
     * @param numberOfBots is number of bots in new game.
     */
    public GameClient (String numberOfPlayers, String numberOfBots) {
        try {
            socket = new Socket("localhost", 4444);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to server.");
            this.numberOfPlayers = numberOfPlayers;
        }
        catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(-1);
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            System.err.println(ex.toString());
        }

        isPlayerRound = false;
        System.out.println("CREATEGAME(" + numberOfPlayers +","+numberOfBots+")");
        output.println("CREATEGAME(" + numberOfPlayers +","+numberOfBots+")");
        serverListener = new Thread(new ServerListener(this));
        serverListener.start();
    }

    /**
     * Constructor for joining to game.
     */
    public GameClient () {
        try {
            socket = new Socket("localhost", 4444);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to server.");
            this.numberOfPlayers = "0";
        }
        catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(-1);
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            System.err.println(ex.toString());
        }
        isPlayerRound = false;
        output.println("JOINGAME()");
        serverListener = new Thread(new ServerListener(this));
        serverListener.start();

    }

    /**
     * Returns reference to PrintWriter of this client.
     * @return reference to PrintWriter.
     */
    public PrintWriter getOutput() {
        return output;
    }

    /**
     * Returns reference to BufferedReader of this client.
     * @return reference to BufferedReader.
     */
    public BufferedReader getInput() {
        return input;
    }

    /**
     * Sets number of players in game.
     * @param numberOfPlayers number of players.
     */
    public void setNumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Returns number of players.
     * @return number of players.
     */
    public String getNumberOfPlayers () {
        return numberOfPlayers;
    }

    /**
     * Sets player's colour.
     * @param playerColour colour.
     */
    public void setPlayerColour (int playerColour) {
        this.playerColour = playerColour;
    }

    /**
     * Returns player's colour.
     * @return player's colour.
     */
    public int getPlayerColour () {
        return  playerColour;
    }

    /**
     * Sets value of variable isPlayerRound.
     * @param isPlayerRound is value of variable.
     */
    public void setIsPlayerRound (boolean isPlayerRound) {
        this.isPlayerRound = isPlayerRound;
    }

    /**
     * Return true if it's player's round now, oth. false.
     * @return true if it's player round now, oth. false.
     */
    public boolean getIsPlayerRound () {
        return isPlayerRound;
    }

}
