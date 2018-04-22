package PolskiZwiazekWarcabowy;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;


public class CommandParserTest {

    String message;
    @Before
    public void prepareMessage() {
        message = new String("TESTCOMMAND(43,2,5432,1)");
    }

    @Test
    public void isCommandCorrect() {
        String testCmd = CommandParser.getCommand(message);
        assertEquals("TESTCOMMAND", testCmd);
    }

    @Test
    public void areArgumentsCorrect() {
        int arg1 = CommandParser.getArgumentAsInteger(message,1);
        int arg2 = CommandParser.getArgumentAsInteger(message,2);
        int arg3 = CommandParser.getArgumentAsInteger(message,3);
        int arg4 = CommandParser.getArgumentAsInteger(message,4);
        assertEquals(43, arg1);
        assertEquals(2, arg2);
        assertEquals(5432, arg3);
        assertEquals(1, arg4);

    }
}
