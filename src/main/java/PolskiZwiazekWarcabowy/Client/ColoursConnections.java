package PolskiZwiazekWarcabowy.Client;

import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * Remembers connections with colour's integer value and his
 */
public final class ColoursConnections extends HashMap<Integer, Color> {

    /**
     * Instance of this class.
     */
    private static ColoursConnections instance;

    private ColoursConnections() {
        initialize();
    }

    /**
     * This method prepares map of collocations - colour's ID and colour's value in class "Color".
     */
    private void initialize () {
        instance = this;
        instance.clear();
        instance.put(0, Color.KHAKI);
        instance.put(1, Color.RED);
        instance.put(2, Color.BLACK);
        instance.put(3, Color.BLUE);
        instance.put(4, Color.GREEN);
        instance.put(5, Color.WHITE);
        instance.put(6, Color.YELLOW);
    }

    /**
     * This method returns map of colours collocations.
     * @return instance of this class.
     */
    public static ColoursConnections getInstance() {
        //Double-checked locking
        if (instance == null) {
            synchronized (ColoursConnections.class) {
                if (instance == null) {
                    instance = new ColoursConnections();
                }
            }
        }
        return instance;
    }

    /**
     * Resets instance of ColoursConnections.
     */
    public void resetReference () {
        instance = null;
    }
}
