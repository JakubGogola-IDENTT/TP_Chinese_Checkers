package PolskiZwiazekWarcabowy.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class is responsible for sending and receiving messages between the game and bot player.
 */

public class BotPlayer extends Player {

    /**
     * Fields contain the socket with its buffer reader, writer, and the last received message.
     */
    private Socket socket;

    private BufferedReader input;

    private PrintWriter output;

    private String message;

    /**
     * Constructor sets proper values to fields.
     * @param socket is socket to communicate between the game and player
     * @param game is the game that bot is playing
     */
    public BotPlayer(Socket socket, Game game) {
        System.out.println("Bot player created.");
        this.socket = socket;
        this.game = game;
        try {
            input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            output = new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

    }

    /**
     * Enables sending the message through socket and writes the message to console.
     * @param message
     */
    @Override
    public void sendMessage (String message) {
        System.out.println(message);
        output.println(message);
    }

    /**
     * Method that enables receiving messages from bot (from socket)
     * @return message received from bot (from socket)
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
     * Getter for BufferedReader field
     * @return reference to input
     */
    public BufferedReader getInput() {
        return input;
    }

}