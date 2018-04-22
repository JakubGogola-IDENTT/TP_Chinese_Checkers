package PolskiZwiazekWarcabowy.Server;

import PolskiZwiazekWarcabowy.Board.BoardPattern;
import PolskiZwiazekWarcabowy.Field;
import PolskiZwiazekWarcabowy.Pawn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is actually bot decision-making class. Receives proper commands with arguments from
 * BotPlayerListener and then properly performs action, specifically deciding what move to make
 * and what communicates send to the game.
 */

public class BotRunner extends Thread {

    private PrintWriter output;

    private BufferedReader input;

    private Thread botPlayerListener;

    private int playerColour;

    private BoardPattern serverBoard;

    private ArrayList<Pawn> botPawns;

    private ArrayList<Pawn> checkedPawns;

    private Socket socket;


    /**
     * Constructor that sets proper value of fields and starts thread.
     * @param serverBoard is board the game performs on.
     */
    public BotRunner(BoardPattern serverBoard) {

        try {
            socket = new Socket("localhost", 4444);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(1);
        }

        this.serverBoard = serverBoard;

        botPlayerListener = new Thread(new BotPlayerListener(this));
        botPlayerListener.start();
    }

    /**
     * Enables bot running
     */
    @Override
    public void run () {
        while(isAlive()) { }
    }

    /**
     * Getter for input BufferedReader
     * @return value of field input
     */
    public BufferedReader getInput() {
        return input;
    }


    /**
     * Method selects pawns of bot from all pawns and puts it in an ArrayList
     */
    public void prepareBotPawns() {
        Pawn p;
        botPawns = new ArrayList<>();
        checkedPawns = new ArrayList<>();
        for (int i=0; i<serverBoard.getPawns().size(); i++) {
            p = serverBoard.getPawns().get(i);
            if (p.getColourInt() == playerColour) {
                botPawns.add(p);
            }
        }
    }

    /**
     * Clears ArrayList of pawns checked in move.
     */
    public void clearCheckedPawns() {
        checkedPawns.clear();
    }

    /**
     * This method has instruction on how pawn should be chosen and which methods
     * should prepare coordinates of its destination in move
     */
    public void doMove() {

        if (checkedPawns.equals(botPawns)) {
            skipRound();
        }
        else {
            Random generator = new Random();
            // number of pawns
            int nop = botPawns.size();
            int numberOfPawnToMove;

            numberOfPawnToMove = generator.nextInt(nop);
            System.out.println("Number of pawn to move: " + numberOfPawnToMove);
            Pawn pawnToMove = botPawns.get(numberOfPawnToMove);
            checkedPawns.add(pawnToMove);

            int[] coordsToMove = countBestMoves(pawnToMove);

            movePawn(pawnToMove.getRow(), pawnToMove.getColumn(), coordsToMove[0], coordsToMove[1]);
        }
    }

    /**
     * This method is performed when bot needs to skip round without move.
     * Sends proper message to server.
     */
    private void skipRound() {
        String msgToServer = "ENDROUND()";
        sendMessage(msgToServer);
    }

    /**
     * Method calculates how pawn should move to reach opposite triangle.
     * If needed, tries to jump over other pawns.
     * @param pawn is pawn that is meant to move
     * @return coordinates of destination field
     */
    private int[] countBestMoves(Pawn pawn) {
        int r = pawn.getRow();
        int c = pawn.getColumn();
        int[] goal = getOppositeTriangleEnd(pawn.getColourInt());
        int[] move = new int[2];
        Random generator = new Random();
        Field fieldToCheck;


        if (r == goal[0] && c == goal[1]) {
            move[0] = r;
            move[1] = c;
        }
        // is in the same line, should go straight
        if (r == goal[0]) {
            if (goal[1] > c) {
                move[0] = r;
                move[1] = c+2;
                fieldToCheck = serverBoard.getMap()[move[0]][move[1]];
                if (fieldToCheck!= null && fieldToCheck.isOccupied()) {
                    move[1]+=2;
                }
            }
            else {
                move[0] = r;
                move[1] = c-2;
                fieldToCheck = serverBoard.getMap()[move[0]][move[1]];
                if (fieldToCheck!= null && fieldToCheck.isOccupied()) {
                    move[1]-=2;
                }
            }
        }
        else {
            if (goal[0] > r) {
                move[0] = r+1;
                if (goal[1] > c) {
                    move[1] = c+1;
                    fieldToCheck = serverBoard.getMap()[move[0]][move[1]];
                    if (fieldToCheck!= null && fieldToCheck.isOccupied()) {
                        move[0]+=1;
                        move[1]+=1;
                    }
                }
                else if (goal[1] < c) {
                    move[1] = c-1;
                    fieldToCheck = serverBoard.getMap()[move[0]][move[1]];
                    if (fieldToCheck!= null && fieldToCheck.isOccupied()) {
                        move[0]+=1;
                        move[1]-=1;
                    }
                }
                else {
                    if (generator.nextInt(1)==1) {
                        move[1] = c + 1;
                        fieldToCheck = serverBoard.getMap()[move[0]][move[1]];
                        if (fieldToCheck!= null && fieldToCheck.isOccupied()) {
                            move[0]+=1;
                            move[1]+=1;
                        }
                    }
                    else {
                        move[1] = c - 1;
                        fieldToCheck = serverBoard.getMap()[move[0]][move[1]];
                        if (fieldToCheck!= null && fieldToCheck.isOccupied()) {
                            move[0]+=1;
                            move[1]-=1;
                        }
                    }
                }
            }
            else {
                move[0] = r-1;
                if (goal[1] > c) {
                    move[1] = c+1;
                    fieldToCheck = serverBoard.getMap()[move[0]][move[1]];
                    if (fieldToCheck!= null && fieldToCheck.isOccupied()) {
                        move[0]-=1;
                        move[1]+=1;
                    }
                }
                else if (goal[1] < c) {
                    move[1] = c-1;
                    fieldToCheck = serverBoard.getMap()[move[0]][move[1]];
                    if (fieldToCheck!= null && fieldToCheck.isOccupied()) {
                        move[0]-=1;
                        move[1]+=1;
                    }
                }
                else {
                    if (generator.nextInt(1)==1) {
                        move[1] = c+1;
                        fieldToCheck = serverBoard.getMap()[move[0]][move[1]];
                        if (fieldToCheck!= null && fieldToCheck.isOccupied()) {
                            move[0]-=1;
                            move[1]+=1;
                        }
                    }
                    else {
                        move[1] = c-1;
                        fieldToCheck = serverBoard.getMap()[move[0]][move[1]];
                        if (fieldToCheck!= null && fieldToCheck.isOccupied()) {
                            move[0]-=1;
                            move[1]-=1;
                        }
                    }
                }
            }
        }
        return move;

    }


    /**
     * Return coordinates to opposite triangle end (the furthest field in opposite triangle)
     * @param colour is colour of player, which opposite triangle is searched
     * @return coordinates to opposite triangle end
     */
    private int[] getOppositeTriangleEnd(int colour) {
        int[] coordinates = new int[2];
        coordinates[0] = 0;
        coordinates[1] = 0;
        switch(colour) {
            case 1:
                coordinates[0] = 17;
                coordinates[1] = 14;
                break;
            case 2:
                coordinates[0] = 13;
                coordinates[1] = 2;
                break;
            case 3:
                coordinates[0] = 5;
                coordinates[1] = 2;
                break;
            case 4:
                coordinates[0] = 1;
                coordinates[1] = 14;
                break;
            case 5:
                coordinates[0] = 5;
                coordinates[1] = 26;
                break;
            case 6:
                coordinates[0] = 13;
                coordinates[1] = 26;
                break;

        }

        return coordinates;
    }

    /**
     * Performs movement of pawn to specified coordinates.
     * @param r1 is row of pawn to move
     * @param c1 is column of pawn to move
     * @param r2 is destination field row
     * @param c2 is destination field column
     */
    private void movePawn(int r1, int c1, int r2, int c2) {
        String msgToServer = "MOVEPAWN("+r1+","+c1+","+r2+","+c2+")";
        sendMessage(msgToServer);
    }


    /**
     * Method that sends to server message to end round.
     */
    public void finishRound() {
        sendMessage("ENDROUND()");
    }

    /**
     * Sets value of bot player colour.
     * @param playerColour is colour of this bot player.
     */
    public void setPlayerColour (int playerColour) {
        this.playerColour = playerColour;
    }

    /**
     * Method that sends message to server and prints it to console.
     * @param msg is message to send.
     */
    private void sendMessage(String msg) {
        System.out.println("BOT: "+msg);
        output.println(msg);
    }

}