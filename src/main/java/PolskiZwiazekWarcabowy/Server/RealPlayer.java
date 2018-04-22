package PolskiZwiazekWarcabowy.Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This is class for real players (not bot).
 * Enables to send and receive messages between server and client.
 */
public class RealPlayer extends Player {

    /**
     * Socket for client.
     */
    private Socket socket;

    /**
     * Reader to receiving messages from client.
     */
    private BufferedReader input;

    /**
     * Printer to sending messages to client.
     */
    private PrintWriter output;


    /**
     * Constructor that sets field values.
     * @param socket is server socket.
     * @param game is the game the player is created in.
     */
    public RealPlayer (Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
        // Trying to open BufferedReader and Print Writer for player.
        try {
            input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            output = new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(-1);
        }
    }

    /**
     * Waits for message from client.
     * @return message from client.
     */
    @Override
    public String getMessage() {
        try {
            message = input.readLine();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(-1);
        }
        return message;
    }

    /**
     * Sends message to player through socket.
     * @param message is message to be send.
     */
    @Override
    public void sendMessage (String message) {
        output.println(message);
    }
}

