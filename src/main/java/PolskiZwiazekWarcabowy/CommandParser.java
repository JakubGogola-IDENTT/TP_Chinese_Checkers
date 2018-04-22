package PolskiZwiazekWarcabowy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser used to extract commands and its parameters in communication between client and server.
 */
public class CommandParser {

    /**
     * Extract command from message (The pattern is "COMMAND(PARAM1, PARAM2, PARAM3 ...)")
     * @param message is message to parse
     * @return command string value
     */
    public static String getCommand(String message) {
        if (message == null) {
            return "NOCOMMAND";
        }
        String command = null;
        Pattern pat = Pattern.compile("(\\w*)\\(.*\\)");
        Matcher m = pat.matcher(message);

        if (m.matches()) {
            command = m.group(1);
        }
        return command;
    }

    /**
     * Gets requested argument from message.
     * @param message is message.
     * @param i is number of argument to extract.
     * @return integer value of extracted argument.
     */
    public static int getArgumentAsInteger(String message, int i) {
        int arg = 0;
        String pat1 = "\\w+\\(([0-9]*,){";
        String pat2 = "}(\\w+).*";
        String pat = pat1 + (i-1) + pat2;
        Pattern p = Pattern.compile(pat);
        Matcher m = p.matcher(message);
        if (m.matches()) {
            arg = Integer.parseInt(m.group(2));
        }
        return arg;
    }
}
