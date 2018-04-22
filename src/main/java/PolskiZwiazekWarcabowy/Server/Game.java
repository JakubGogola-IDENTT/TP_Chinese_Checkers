package PolskiZwiazekWarcabowy.Server;

import PolskiZwiazekWarcabowy.Board.Board;
import PolskiZwiazekWarcabowy.Board.BoardPattern;
import PolskiZwiazekWarcabowy.CommandParser;
import PolskiZwiazekWarcabowy.Pawn;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

/**
 * Class that contains players and board of a single game.
 * Waits for players, perfoms game and ends it, sending proper
 * messages to players and executes received messages from players.
 */
public class Game extends Thread {

    private int numberOfPlayers;
    private int numberOfBots;
    private ServerSocket socket;
    private HashMap<Integer, PlayerPattern> mapOfPlayers;
    private BoardPattern board;
    private Pawn activatedPawn;
    private boolean isFirstMove;

    /**
     * Constructor that initializes fields and sets their values.
     * @param socket is socket for connection with players.
     */
    public Game(ServerSocket socket, RoomHost host, int numberOfPlayers, int numberOfBots) {
        this.socket = socket;
        this.numberOfPlayers = numberOfPlayers;
        mapOfPlayers = new HashMap<>();
        mapOfPlayers.clear();
        board = getBoardInstance();
        this.numberOfBots = numberOfBots;

        PlayerPattern hostPlayer = new RealPlayer(host.getSocket(),this);
        mapOfPlayers.put(0, hostPlayer);
        System.out.println("Starting creating bots!");
        joinBotPlayers();
        System.out.println("Starting creating players!");
        createMissingPlayers();
        System.out.println("Creating threads has finished.");
        setPlayersColours();
        run();
    }

    /**
     * Method that joins required number of bot players to the game.
     */
    private void joinBotPlayers() {
        BotPlayer botPlayer;
        BotRunner botRunner;

        for (int i=1; i<numberOfBots+1; i++) {
            try {
                botRunner = new BotRunner( getBoardInstance());
                botRunner.start();
                botPlayer = new BotPlayer(this.socket.accept(), this);
                mapOfPlayers.put(i, botPlayer);

                System.out.println("Bot was created.");
            } catch (IOException ex) {
                System.err.println(ex.toString());
                System.exit(1);
            }
        }
    }

    /**
     * Method that joins real players to the game.
     * Waits for them trying to connect, gets their first messages, decides
     * whether player wants to join to game and then sends
     * them proper welcome messages.
     */
    private void createMissingPlayers () {
        Player player;
        int i = numberOfBots + 1;
        boolean isJoiningFinished = false;
        String initMessage;
        while (!isJoiningFinished) {
            try {
                if (i == numberOfPlayers ) {
                    isJoiningFinished = true;
                    break;
                }
                player = new RealPlayer(this.socket.accept(), this);
                initMessage = player.getMessage();
                System.out.println("First message form client: "+initMessage);
                String command = CommandParser.getCommand(initMessage);
                System.out.println(command);
                switch (command) {
                    case "JOINGAME":
                        mapOfPlayers.put(i, player);
                        player.start();
                        System.out.println("Thread number  " + (i + 1) + " was created.");
                        i++;
                        break;
                    case "CREATEGAME":
                        String msgToClient = "CANNOTCREATEGAME()";
                        System.out.println(msgToClient);
                        player.sendMessage(msgToClient);
                        break;
                    default:
                        System.out.println("Wrong communicate from new player: " + initMessage);
                        break;
                }

            } catch (IOException ex) {
                System.err.println(ex.toString());
                System.exit(-1);
            }
        }
    }

    /**
     * Sets proper colour to players depending on game type (number of players)
     */
    private void setPlayersColours() {

        PlayerPattern p[] = new PlayerPattern[6];

        for (int i=0; i<numberOfPlayers; i++) {
            p[i] = mapOfPlayers.get(i);
        }

        switch(numberOfPlayers) {
            case 2:
                p[0].setPlayerColour(1);
                p[0].sendMessage("SETCOLOUR(1)");
                p[1].setPlayerColour(4);
                p[1].sendMessage("SETCOLOUR(4)");
                break;
            case 3:
                p[0].setPlayerColour(1);
                p[0].sendMessage("SETCOLOUR(1)");
                p[1].setPlayerColour(3);
                p[1].sendMessage("SETCOLOUR(3)");
                p[2].setPlayerColour(5);
                p[2].sendMessage("SETCOLOUR(5)");
                break;
            case 4:
                p[0].setPlayerColour(1);
                p[0].sendMessage("SETCOLOUR(1)");
                p[1].setPlayerColour(4);
                p[1].sendMessage("SETCOLOUR(4)");
                p[2].setPlayerColour(3);
                p[2].sendMessage("SETCOLOUR(3)");
                p[3].setPlayerColour(6);
                p[3].sendMessage("SETCOLOUR(6)");
                break;
            case 6:
                p[0].setPlayerColour(1);
                p[0].sendMessage("SETCOLOUR(1)");
                p[1].setPlayerColour(4);
                p[1].sendMessage("SETCOLOUR(4)");
                p[2].setPlayerColour(2);
                p[2].sendMessage("SETCOLOUR(3)");
                p[3].setPlayerColour(5);
                p[3].sendMessage("SETCOLOUR(6)");
                p[4].setPlayerColour(3);
                p[4].sendMessage("SETCOLOUR(2)");
                p[5].setPlayerColour(6);
                p[5].sendMessage("SETCOLOUR(5)");
                break;
            default:
                System.out.println("error");
        }
        System.out.println("Colours set to players.");
    }

    /**
     * Runs the game. Iterates through players performing their
     * round in the game and finishes when all winners are known.
     */
    public void run() {
        boolean gameFinished = false;
        String msgToClient;
        PlayerPattern activePlayer;
        int wonPlayersNum = 0;
        int activePlayerNum = 0;
        msgToClient = "GAMESTART("+numberOfPlayers+")";
        sendToEveryPlayer(msgToClient);

        while(!gameFinished) {
            activePlayerNum = activePlayerNum % numberOfPlayers;
            activePlayer = mapOfPlayers.get(activePlayerNum);
            if (!activePlayer.hasWon() ) {
                playRound(activePlayer);
            }
            for (int i=0; i<numberOfPlayers; i++) {
                PlayerPattern p = mapOfPlayers.get(i);
                if (board.hasPlayerWon(p.getColour())) {
                    wonPlayersNum++;
                }
            }
            if (wonPlayersNum == numberOfPlayers-1) {
                gameFinished = true;
            }
            activePlayerNum++;
        }
        System.out.println("Game has finished!");
        msgToClient = "GAMEFINISHED()";
        sendToEveryPlayer(msgToClient);
    }

    /**
     * Performs one round of a player, from start till player wants to
     * finish it. Receives messages from players and execute them properly.
     * @param activePlayer
     */
    private void playRound(PlayerPattern activePlayer) {
        String msgFromClient, command = "", msgToClient;
        int r1, c1, r2, c2;

        isFirstMove = true;
        activatedPawn = null;
        msgToClient = "ROUNDSTART()";
        activePlayer.sendMessage(msgToClient);
        board.setHasPawnMovedToNeighbour(false);
        while(!command.equals("ENDROUND")) {
            msgFromClient = activePlayer.getMessage();
            command = CommandParser.getCommand(msgFromClient);
            switch(command) {
                case "ENDROUND":
                    activePlayer.sendMessage("ROUNDFINISHED()");
                    break;
                case "MOVEPAWN":
                    r1 = CommandParser.getArgumentAsInteger(msgFromClient, 1);
                    c1 = CommandParser.getArgumentAsInteger(msgFromClient, 2);
                    r2 = CommandParser.getArgumentAsInteger(msgFromClient, 3);
                    c2 = CommandParser.getArgumentAsInteger(msgFromClient, 4);

                    if(board.getPawn(r1, c1) == null) {
                        msgToClient = "CANTMOVEPAWN()";
                        System.out.println(msgToClient);
                        activePlayer.sendMessage(msgToClient);
                    }
                    //If this is first move of player need to set first moved pawn as activated Pawn
                    if (isFirstMove) {
                        activatedPawn = board.getPawn(r1, c1);
                    }
                    if (activatedPawn == board.getPawn(r1, c1)) {
                        tryMovePawn(r1, c1, r2, c2, activePlayer);
                    }
                    else {
                        msgToClient = "CANTMOVEPAWN()";
                        System.out.println(msgToClient);
                        activePlayer.sendMessage(msgToClient);
                    }
                    break;
            }
        }

        if (board.hasPlayerWon(activePlayer.getColour())) {
            activePlayer.setHasWon(true);
            msgToClient = "PLAYERWON()";
            activePlayer.sendMessage(msgToClient);
        }

    }

    /**
     * Method that sends message to every player in the game.
     * @param msg
     */
    private void sendToEveryPlayer(String msg) {
        PlayerPattern p;
        for(int i=0; i<numberOfPlayers; i++) {
            p = mapOfPlayers.get(i);
            p.sendMessage(msg);
        }
    }

    /**
     * Method tries to move desirable pawn to desire position (checks if it is possible
     * in Board class)
     * @param r1 is row of pawn to move
     * @param c1 is column of pawn to move
     * @param r2 is row of destination
     * @param c2 is column of destination
     * @param activePlayer is player that wants to perform move
     * @return
     */
    private void tryMovePawn (int r1, int c1, int r2, int c2, PlayerPattern activePlayer) {
        String msgToClient;
        if (board.movePawn(r1, c1, r2, c2, isFirstMove)) {
            msgToClient= "PAWNMOVED("+r1+","+c1+","+r2+","+c2+")";
            isFirstMove = false;
            System.out.println(msgToClient);
            activePlayer.sendMessage("MOVEPOSSIBLE()");
            sendToEveryPlayer(msgToClient);
        }
        else {
            msgToClient = "CANTMOVEPAWN()";
            System.out.println(msgToClient);
            activePlayer.sendMessage(msgToClient);
        }
    }

    /**
     * Getter for board
     * @return board instance.
     */
    private BoardPattern getBoardInstance () {
        if (board == null) {
            board = new Board(numberOfPlayers);
        }
        return board;
    }

}
