package PolskiZwiazekWarcabowy.Server;

import PolskiZwiazekWarcabowy.Board.Board;
import PolskiZwiazekWarcabowy.Board.BoardPattern;
import PolskiZwiazekWarcabowy.CommandParser;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class that reads messages from socket buffer and interprets commands properly, causing
 * action in BotRunner
 */
public class BotPlayerListener implements Runnable {

    private BufferedReader input;

    private String message;

    private String command;

    private BotRunner bot;

    private BoardPattern board;


    /**
     * Sets basic fields values.
     * @param bot is class that performs action in game
     */
    public  BotPlayerListener (BotRunner bot) {
        this.bot = bot;
        this.input = bot.getInput();
    }

    /**
     * Method is listening to socket, and if receives message interprets it and executes
     * proper methods in bot
     */
    @Override
    public void run() {
        while (true) {
            try {
                message = input.readLine();
                System.out.println("BOT " +message);
            } catch (IOException ex) {
                System.err.println(ex.toString());
                System.exit(1);
            }
            command = CommandParser.getCommand(message);
            switch (command) {

                case "GAMESTART" :
                    int temp = CommandParser.getArgumentAsInteger(message,1);
                    board = new Board(temp);
                    bot.prepareBotPawns();
                    break;
                case "SETCOLOUR" :
                    bot.setPlayerColour(CommandParser.getArgumentAsInteger(message,1));
                    break;
                case "PAWNMOVED" :
                    break;
                case "ROUNDSTART":
                    bot.clearCheckedPawns();
                    bot.doMove();
                    break;
                case "MOVEPOSSIBLE":
                    bot.finishRound();
                    break;
                case "CANTMOVEPAWN":
                    bot.doMove();
                    break;
            }
        }
    }
}
