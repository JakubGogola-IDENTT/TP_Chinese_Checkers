package PolskiZwiazekWarcabowy.Client;

import PolskiZwiazekWarcabowy.Client.GraphicBoard.GraphicBoard;
import PolskiZwiazekWarcabowy.Pawn;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphicBoardTest {

    private GraphicBoard board;

    private ColoursConnections coloursConnections;

    @Before
    public void initialize () {
        board = new GraphicBoard(0);
        coloursConnections = ColoursConnections.getInstance();
    }

    @Test
    public void testIfFieldsAreWellColoured () {
        GraphicBoard twoPlayerBoard = new GraphicBoard(2);
        GraphicBoard threePlayerBoard = new GraphicBoard(3);
        GraphicBoard fourPlayerBoard = new GraphicBoard(4);
        GraphicBoard sixPlayerBoard = new GraphicBoard(6);


        int colour, position;

        //Checking pawns in the North triangle.
        colour = 1;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < twoPlayerBoard.getBoard()[i].length; j++) {
                if (board.getBoard()[i][j] != null) {
                    assertEquals(coloursConnections.get(colour), twoPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, twoPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), threePlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, threePlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), fourPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, fourPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), sixPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, sixPlayerBoard.getBoard()[i][j].getIsPawn());
                }
            }
        }

        //Checking pawns in the North-Eastern triangle.
        colour = 2;
        position = 20;
        for (int i = 5; i <= 8; i++) {
            for (int j = position; j < twoPlayerBoard.getBoard()[i].length; j++) {
                if (board.getBoard()[i][j] != null) {
                    assertEquals(coloursConnections.get(0), twoPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(false, twoPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(0), threePlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(false, threePlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(0), fourPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(false, fourPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), sixPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, sixPlayerBoard.getBoard()[i][j].getIsPawn());
                }
            }
            position++;
        }

        //Checking pawns in the South-Eastern triangle.
        colour = 3;
        position = 23;
        for (int i = 10; i <= 13; i++) {
            for (int j = position; j < twoPlayerBoard.getBoard()[i].length; j++) {
                if (board.getBoard()[i][j] != null) {
                    assertEquals(coloursConnections.get(0), twoPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(false, twoPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), threePlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, threePlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), fourPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, fourPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), sixPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, sixPlayerBoard.getBoard()[i][j].getIsPawn());
                }
            }
            position--;
        }

        //Changing pawns in the Southern triangle.
        colour = 4;
        Pawn testPawn4 = new Pawn(colour);
        for (int i = 14; i <= 17; i++) {
            for (int j = 0; j < twoPlayerBoard.getBoard()[i].length; j++) {
                if (board.getBoard()[i][j] != null) {
                    assertEquals(coloursConnections.get(colour), twoPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, twoPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(0), threePlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(false, threePlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), fourPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, fourPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), sixPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, sixPlayerBoard.getBoard()[i][j].getIsPawn());
                }
            }
        }

        //Changing pawns in the South-Western triangle.
        colour = 5;
        position = 8;
        for (int i = 13; i >= 10; i--) {
            for (int j = position; j >= 0; j--) {
                if (board.getBoard()[i][j] != null) {
                    assertEquals(coloursConnections.get(0), twoPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(false, twoPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), threePlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, threePlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(0), fourPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(false, fourPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), sixPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, sixPlayerBoard.getBoard()[i][j].getIsPawn());
                }
            }
            position--;
        }
        //Changing pawns in the North-Western triangle.
        colour = 6;
        position = 5;
        for (int i = 8; i >= 5; i--) {
            for (int j = position; j >= 0; j--) {
                if (board.getBoard()[i][j] != null) {
                    assertEquals(coloursConnections.get(0), twoPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(false, twoPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(0), threePlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(false, threePlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), fourPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, fourPlayerBoard.getBoard()[i][j].getIsPawn());

                    assertEquals(coloursConnections.get(colour), sixPlayerBoard.getBoard()[i][j].getColour());
                    assertEquals(true, sixPlayerBoard.getBoard()[i][j].getIsPawn());
                }
            }
            position++;
        }
    }

    @Test
    public void testIfBoardForNoPlayersHasOnlyEmptyFields () {
        GraphicField[][] testBoard;
        testBoard = board.getBoard();

        for (int i = 0; i < testBoard.length; i++) {
            for (int j = 0; j < testBoard[i].length; j++) {
                if (testBoard[i][j] != null) {
                    assertEquals(coloursConnections.get(0), testBoard[i][j].getColour());
                    assertEquals(false, testBoard[i][j].getIsPawn());
                }
            }
        }
    }

    @After
    public void tearDown () {
        board = null;
        coloursConnections = null;
    }
}
