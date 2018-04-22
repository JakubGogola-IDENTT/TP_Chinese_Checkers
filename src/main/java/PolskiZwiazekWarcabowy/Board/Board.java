package PolskiZwiazekWarcabowy.Board;

import PolskiZwiazekWarcabowy.Colour;
import PolskiZwiazekWarcabowy.Field;
import PolskiZwiazekWarcabowy.Pawn;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * It is instance of board.
 */
public class Board implements BoardPattern {

    /**
     * Variable which remembers instance of board.
     */
    private Field[][] map;

    private BoardBuilder boardBuilder;
    private BoardAssembler boardAssembler;
    private ArrayList<Pawn> pawns;
    private boolean hasPawnMovedToNeighbour = false;

    /**
     * Constructor of GraphicBoard class.
     */

    public Board(int howManyPlayers) {
        initialize(howManyPlayers);
    }

    /**
     * This method initializes specific HexagonalBoardBuilder and BoardAssembler.
     */
    private void initialize(int howManyPlayers) {
        switch (howManyPlayers) {
            case 0:
                boardBuilder = new HexagonalBoardWithNoPlayersBuilder();
                break;
            case 2:
                boardBuilder = new HexagonalBoardForTwoPlayersBuilder();
                break;
            case 3:
                boardBuilder = new HexagonalBoardForThreePlayersBuilder();
                break;
            case 4:
                boardBuilder = new HexagonalBoardForFourPlayersBuilder();
                break;
            case 6:
                boardBuilder = new HexagonalBoardForSixPlayersBuilder();
                break;

        }
        boardAssembler = new BoardAssembler();
        map = boardAssembler.getBoard(boardBuilder);
        pawns = boardAssembler.getPawns(boardBuilder);
    }

    /**
     * This method returns array with fields.
     * @return array with fields.
     */
    public Field[][] getMap() {
        return map;
    }

    /**
     * This method checks if moving pawn is allowed.
     * @param r1 row of first pawn.
     * @param c1 column of first pawn.
     * @param r2 row of second pawn.
     * @param c2 column of second pawn.
     * @param isFirstMove true if it is first move.
     * @return true if move is allowed, oth. false.
     */
    @Override
    public boolean movePawn(int r1, int c1, int r2, int c2, boolean isFirstMove) {

        Field start = getMap()[r1][c1];
        Field destination = getMap()[r2][c2];
        if (hasPawnMovedToNeighbour) {
            return false;
        }

        // if we give bad starting position can't make move
        if (start == null || !start.isOccupied()) {
            return false;
        }
        // can't make move if pawn wants to move to occupied field or position that is not a field.
        if (destination == null || destination.isOccupied()) {
            return false;
        }
        // can't make move if pawn wants to move away from opposite triangle
        if (start.getColour() == start.getPawn().getOppositeColourInt() && destination.getColour() != start.getColour()) {
            return false;
        }

        // if destination field is a neighbour
        if ((distance(start,destination) == 2 && start.getR() == destination.getR()) || distance(start, destination) == sqrt(2)) {
            if (isFirstMove) {
                makeMove(start, destination);
                hasPawnMovedToNeighbour = true;
            }
            else return false;
        }
        // else if destination is 1 field away from start
        // Simplified: map[average(start.getR(), destination.getR())][average(start.getC(), destination.getC())].isOccupied() == true
        else if (distance(start,destination) == 4 || distance(start, destination) == sqrt(8)) {
            Field fieldBetween = map[average(start.getR(), destination.getR())][average(start.getC(), destination.getC())];
            if (fieldBetween == null || fieldBetween.getR() - start.getR() > 1) return false;
            else if (fieldBetween.isOccupied() ) {
                makeMove(start,destination);
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
        return true;
    }

    /**
     * This method computes distance between fields.
     * @param p1 first field.
     * @param p2 second field.
     * @return distance.
     */
    private double distance(Field p1, Field p2) {
        return sqrt(pow((p1.getR()-p2.getR()), 2)+pow((p1.getC()-p2.getC()),2));
    }

    /**
     * This method makes move.
     * @param start is field where pawn is.
     * @param destination is field which is destination of move.
     */
    private void makeMove(Field start, Field destination){
        destination.setPawn(start.getPawn());
        start.setPawn(null);
    }

    /**
     * This method computes average.
     * @param a an Integer.
     * @param b an Integer.
     * @return average.
     */
    private int average(int a, int b) {
        return (a+b)/2;
    }

    /**
     * This method sets pawn on concrete field.
     * @param pawn is pawn to set.
     * @param r is row.
     * @param c is column.
     */
    @Override
    public void setPawn(Pawn pawn, int r, int c) {
        if (!map[r][c].isOccupied()) {
            map[r][c].setPawn(pawn);
            if (pawn != null) pawn.setLocation(r,c);
        }
    }

    /**
     * Returns pawn from concrete field.
     * @param r is row.
     * @param c is column.
     * @return pawn from field.
     */
    @Override
    public Pawn getPawn(int r, int c) {
        if (map[r][c] != null) return map[r][c].getPawn();
        else return null;
    }

    /**
     * Checks if player won.
     * @param colour is Colour of player.
     * @return true if player won, oth. false.
     */
    @Override
    public boolean hasPlayerWon(Colour colour) {
        return areOppositeFieldsFilledWithColourPawns(colour);
    }

    /**
     * Checks if player's opposite triangle is filed with his pawns.
     * @param colour is colour of player.
     * @return true if opposite triangle is filled, oth. false.
     */
    private boolean areOppositeFieldsFilledWithColourPawns(Colour colour) {
        Pawn pawn;
        for(int i=0; i < map.length; i++) {
            for (int j=0; j< map[i].length; j++) {
                if (map[i][j] != null) {
                    if (map[i][j].getColour() == colour.getOppositeColour()) {
                        if ((pawn = map[i][j].getPawn()) != null) {
                            if (pawn.getColourInt() != colour.getColour()) {
                                return false;
                            }
                        }
                        else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks if pawn was moved to neighbour field.
     * @param hasPawnMovedToNeighbour is value of move.
     */
    public void setHasPawnMovedToNeighbour(boolean hasPawnMovedToNeighbour) {
        this.hasPawnMovedToNeighbour = hasPawnMovedToNeighbour;
    }

    /**
     * This method return list of pawns.
     * @return list of pawns.
     */
    public ArrayList<Pawn> getPawns() {
        return pawns;
    }
}
