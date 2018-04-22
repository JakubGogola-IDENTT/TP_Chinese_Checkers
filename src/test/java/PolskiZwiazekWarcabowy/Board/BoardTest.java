package PolskiZwiazekWarcabowy.Board;

import PolskiZwiazekWarcabowy.Colour;
import PolskiZwiazekWarcabowy.Field;
import PolskiZwiazekWarcabowy.Pawn;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Kajtek on 2017-12-03.
 * Testing everything connected with board - settlement of pawns,
 * movements, map creation etc.
 */
public class BoardTest {

    private Board board;
    private Field[][] map;

    @Before
    public void prepareBoard() {
        board = new Board(0);
        map = board.getMap();
    }

    @Test
    public void testSimplePawnMove() {
        Pawn pawnTest = new Pawn(1);
        board.setPawn(pawnTest, 6, 5);
        board.movePawn(6,5, 6,7, true);
        assertSame(pawnTest, board.getPawn(6,7));
        assertSame(null, board.getPawn(6,5));

    }

    @Test
    public void testJumpingOverPawn() {
        Pawn pawnToOverjump = new Pawn(1);
        Pawn pawnToJump = new Pawn(1);
        board.setPawn(pawnToJump, 5, 6);
        board.setPawn(pawnToOverjump, 6,7);
        board.movePawn(5,6, 7,8, true);
        assertSame(pawnToJump, board.getPawn(7,8));
        assertSame(null, board.getPawn(5,6));
    }

    @Test
    public void testJumpingOverPawnNotDiagonal() {
        Pawn pawnToOverjump = new Pawn(1);
        Pawn pawnToJump = new Pawn(1);
        board.setPawn(pawnToJump, 6, 5);
        board.setPawn(pawnToOverjump, 7,6);
        board.movePawn(6,5, 8,5, true);
        assertSame(pawnToJump, board.getPawn(6,5));
        assertSame(null, board.getPawn(8,5));
    }

    @Test
    public void testJumpingFurtherThanPossible() {
        Pawn pawnTest = new Pawn(1);
        board.setPawn(pawnTest, 5, 6);
        board.movePawn(5,6, 9,6, true);
        assertSame(null, board.getPawn(9,6));
        assertSame(pawnTest, board.getPawn(5,6));
    }

    @Test
    public void testJumpIntoAnother() {
        Pawn pawnToJumpOn = new Pawn(1);
        Pawn pawnToJump = new Pawn(1);
        board.setPawn(pawnToJump, 5, 6);
        board.setPawn(pawnToJumpOn, 6,7);
        board.movePawn(5,6, 6,7, true);
        assertSame(pawnToJump, board.getPawn(5,6));
        assertSame(pawnToJumpOn, board.getPawn(6,7));
    }

    @Test
    public void testJumpDiagonalButNothingBetween() {
        Pawn pawnToJump = new Pawn(1);
        board.setPawn(pawnToJump, 5, 6);
        board.movePawn(5,6, 7,8, true);
        assertSame(null, board.getPawn(7,8));
        assertSame(pawnToJump, board.getPawn(5,6));
    }

    @Test
    public void testIfPawnCanMoveFromOppositeTriangle() {
        Pawn pawn = new Pawn(4);
        board.setPawn(pawn, 4,13);
        board.movePawn(4,13, 5,14, true);
        assertSame(null, board.getPawn(5,14));
        assertSame(pawn, board.getPawn(4,13));
    }

    @Test
    public void testIfHasPlayerWonMethodReturnsTrueCorrectly() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < board.getMap()[i].length; j++) {
                if (board.getMap()[i][j] != null) {
                    board.setPawn(new Pawn(4), i, j);
                }
            }
        }
        Colour colour = new Colour(4);
        assertTrue(board.hasPlayerWon(colour));
    }

    @Test
    public void testIfHasPlayerWonMethodReturnsFalseCorectly() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < board.getMap()[i].length; j++) {
                if (board.getMap()[i][j] != null) {
                    if (i != 2)
                    board.setPawn(new Pawn(4), i, j);
                }
            }
        }
        Colour colour = new Colour(4);
        assertFalse(board.hasPlayerWon(colour));
    }

    @Test
    public void testIfPawnsAreWellPlaced() {
        Board twoPlayerBoard = new Board(2);
        Board threePlayerBoard = new Board(3);
        Board fourPlayerBoard = new Board(4);
        Board sixPlayerBoard = new Board(6);

        int colour, position;

        //Checking pawns in the North triangle.
        colour = 1;
        Pawn testPawn1 = new Pawn(colour);
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < twoPlayerBoard.getMap()[i].length; j++) {
                if (board.getMap()[i][j] != null) {
                    assertEquals(testPawn1, twoPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn1, threePlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn1, fourPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn1, sixPlayerBoard.getMap()[i][j].getPawn());
                }
            }
        }

        //Checking pawns in the North-Eastern triangle.
        colour = 2;
        Pawn testPawn2 = new Pawn(colour);
        position = 20;
        for (int i = 5; i <= 8; i++) {
            for (int j = position; j < twoPlayerBoard.getMap()[i].length; j++) {
                if (board.getMap()[i][j] != null) {
                    assertEquals(null, twoPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(null, fourPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(null, threePlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn2, sixPlayerBoard.getMap()[i][j].getPawn());
                }
            }
            position++;
        }

        //Checking pawns in the South-Eastern triangle.
        colour = 3;
        Pawn testPawn3 = new Pawn(colour);
        position = 23;
        for (int i = 10; i <= 13; i++) {
            for (int j = position; j < twoPlayerBoard.getMap()[i].length; j++) {
                if (board.getMap()[i][j] != null) {
                    assertEquals(null, twoPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn3, threePlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn3, fourPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn3, sixPlayerBoard.getMap()[i][j].getPawn());
                }
            }
            position--;
        }

        //Changing pawns in the Southern triangle.
        colour = 4;
        Pawn testPawn4 = new Pawn(colour);
        for (int i = 14; i <= 17; i++) {
            for (int j = 0; j < twoPlayerBoard.getMap()[i].length; j++) {
                if (board.getMap()[i][j] != null) {
                    assertEquals(testPawn4, twoPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(null, threePlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn4, fourPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn4, sixPlayerBoard.getMap()[i][j].getPawn());
                }
            }
        }

        //Changing pawns in the South-Western triangle.
        colour = 5;
        Pawn testPawn5 = new Pawn(colour);
        position = 8;
        for (int i = 13; i >= 10; i--) {
            for (int j = position; j >= 0; j--) {
                if (board.getMap()[i][j] != null) {
                    assertEquals(null, twoPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn5, threePlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(null, fourPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn5, sixPlayerBoard.getMap()[i][j].getPawn());
                }
            }
            position--;
        }
        //Changing pawns in the North-Western triangle.
        colour = 6;
        Pawn testPawn6 = new Pawn(colour);
        position = 5;
        for (int i = 8; i >= 5; i--) {
            for (int j = position; j >= 0; j--) {
                if (board.getMap()[i][j] != null) {
                    assertEquals(null, twoPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(null, threePlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn6, fourPlayerBoard.getMap()[i][j].getPawn());
                    assertEquals(testPawn6, sixPlayerBoard.getMap()[i][j].getPawn());
                }
            }
            position++;
        }

    }


    @Test
    public void testIfTrianglesHasRightColours() {
        int colour;
        int position;

        //Checking colour in North triangle.
        colour = 1;
        for(int i = 1; i <= 4; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    assertEquals(map[i][j].getColour(), colour);
                }
            }
        }

        //Checking colour in the North-Eastern triangle.
        colour = 2;
        position = 20;
        for(int i = 5; i <= 8; i++) {
            for (int j = position; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    assertEquals(map[i][j].getColour(), colour);
                }
            }
            position++;
        }

        //Checking colour in the South-Eastern triangle.
        colour = 3;
        position = 23;
        for(int i = 10; i <= 13; i++){
            for (int j = position; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    assertEquals(map[i][j].getColour(), colour);
                }
            }
            position--;
        }

        //Checking colour in the Southern triangle.
        colour = 4;
        for(int i = 14; i <= 17; i++){
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    assertEquals(map[i][j].getColour(), colour);
                }
            }
        }

        //Checking colour in the South-Western triangle.
        colour = 5;
        position = 8;
        for (int i = 13; i >=10; i--) {
            for (int j = position; j >= 0; j--) {
                if(map[i][j] != null) {
                    assertEquals(map[i][j].getColour(), colour);
                }
            }
            position--;
        }
        //Checking colour in the North-Western triangle.
        colour = 6;
        position = 5;
        for (int i = 8; i >= 5; i--) {
            for (int j = position; j >= 0; j--) {
                if(map[i][j] != null) {
                    assertEquals(map[i][j].getColour(), colour);
                }
            }
            position++;
        }
    }
}
