package PolskiZwiazekWarcabowy.Server;

import PolskiZwiazekWarcabowy.CommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class needed for initializing the game. Host sets proper room values and
 * initializes the game.
 */
public class RoomHost extends Thread {

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
     * Message from client.
     */
    private String message;

    /**
     * Number of expected players.
     */
    private int numberOfPlayers;

    /**
     * Number of requested bots.
     */
    private int numberOfBots;

    GameServer server;

    /**
     * Constructor that sets values to fields.
     * @param socket is server socket
     * @param server is server with game
     */
    public RoomHost (Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
        try {
            input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            output = new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(-1);
        }

    }

    /**
     * Waits for message from host client and then start game.
     */
    public void run() {
            try {
                message = input.readLine();
            } catch (IOException ex) {
                System.err.println(ex.toString());
                System.exit(-1);
            }

            if (server.getGame() == null) {
                try {
                    numberOfPlayers = CommandParser.getArgumentAsInteger(message, 1);
                    numberOfBots = CommandParser.getArgumentAsInteger(message, 2);
                    server.initGame(this, numberOfPlayers, numberOfBots);
                    System.out.println("Game initialized. Host dies.");
                } catch (NumberFormatException ex) {
                    System.err.println(ex.toString());
                    System.exit(-1);
                }
            } //TODO other game will be created, for now its only one - should send some error and finish thread
    }

    /**
     * Getter for server socket.
     * @return server socket.
     */
    public Socket getSocket() {
        return socket;
    }
}
