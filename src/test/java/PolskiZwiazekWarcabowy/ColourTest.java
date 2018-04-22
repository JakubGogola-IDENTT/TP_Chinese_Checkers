package PolskiZwiazekWarcabowy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ColourTest {

    private HashMap<Integer, Integer> matches;

    @Before
    public void setUp () {
        matches = new HashMap<>();
        matches.clear();
        //Key is colour, value is opposite colour.
        matches.put(1, 4);
        matches.put(2, 5);
        matches.put(3, 6);
        matches.put(4, 1);
        matches.put(5, 2);
        matches.put(6, 3);

    }

    @Test
    public void testAreOppositeColoursWellConnected () {
        Colour colour;
        String msg = "Colours are incorrectly matched!";

        for (int i = 1; i <= 6; i++) {
            colour = new Colour(i);
            assertEquals(msg, (int) matches.get(i), colour.getOppositeColour());
        }
    }

    @Test
    public void testIfChangingColourWorksCorrectly () {
        Colour colour;
        String msg = "Changing colour failed";

        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                colour = new Colour(i);
                assertEquals(msg, i, colour.getColour());

                colour.setColour(j);
                assertEquals(msg, j, colour.getColour());
                assertEquals(msg, (int) matches.get(j), colour.getOppositeColour());
            }
        }
    }

    @After
    public void tearDown () {
        matches.clear();
        matches = null;
    }
}
