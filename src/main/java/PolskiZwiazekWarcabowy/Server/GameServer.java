package PolskiZwiazekWarcabowy.Server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This is actual server. Enables host player to connect and create game.
 */
public class GameServer {

    protected static ServerSocket serverSocket;

    protected Game game = null;

    protected boolean end;

    /**
     * Starts server running.
     * @param args arguments of executing (none required)
     */
    public static void main (String[] args) {
        GameServer server = new GameServer();
        server.runServer();
    }

    /**
     * While server is running first it waits for host player and then waits
     * for the game to finish.
     */
    protected void runServer() {
            end = false;
            try {
                serverSocket = new ServerSocket(4444);
                RoomHost host = new RoomHost(serverSocket.accept(), this);
                System.out.println("Connection made.");
                host.run();
            } catch (IOException ex) {
                System.err.println(ex.toString());
                System.exit(-1);
            } finally {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    System.err.println(ex.toString());
                    System.exit(-1);
                }
            }
        while(true) {
                if(end) {
                    break;
                }
        }
    }

    /**
     * Getter for game.
     * @return field game value
     */
    public Game getGame() {
        return game;
    }

    /**
     * Initializes game with specified number of player and bots (by host player)
     * @param host is host player
     * @param numberOfPlayers is total number of players in a game (bot and real)
     * @param numberOfBots is number of bot players required in a game
     */
    public void initGame(RoomHost host,  int numberOfPlayers, int numberOfBots) {
        game = new Game(serverSocket, host, numberOfPlayers, numberOfBots);
    }

    /**
     * Getter for server socket.
     * @return server socket
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
