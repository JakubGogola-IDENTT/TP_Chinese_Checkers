package PolskiZwiazekWarcabowy.Client;

import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ColoursConnectionsTest {

    private HashMap<Integer, Color> matches;
    private HashMap<Integer, Color> testedMap;
    private String msg;

    @Before
    public void setUp() throws Exception {
        matches = new HashMap<>();
        matches.clear();
        matches.put(0, Color.KHAKI);
        matches.put(1, Color.RED);
        matches.put(2, Color.BLACK);
        matches.put(3, Color.BLUE);
        matches.put(4, Color.GREEN);
        matches.put(5, Color.WHITE);
        matches.put(6, Color.YELLOW);
    }

    @Test
    public void testIsMapOfConnectionsInCorrectSize () {
        msg = "Different sizes!";
        testedMap = ColoursConnections.getInstance();
        assertEquals(msg, matches.size(), testedMap.size());
    }

    @Test
    public void testIfColoursAreCorrectlyMatched () {
        msg = "Incorrect matches!";
        testedMap = ColoursConnections.getInstance();

        for (int i = 0 ; i < matches.size(); i++) {
            assertEquals(msg, matches.get(i), testedMap.get(i));
        }
    }

    @Test
    public void testMultiThreading () throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        List<ColoursConnections> references = Collections.synchronizedList(new ArrayList<ColoursConnections>());

        for (int i = 0; i < 10; i++) {
            Thread worker = new Thread(new Worker(i, references));
            threads.add(worker);
            worker.start();
        }

        for (Thread thread : threads) {
            while (thread.isAlive()) {

            }
            System.out.println("Thread " + thread.getId() + " died!");

        }
        for (int i = 0; i < references.size(); i++) {
            ColoursConnections expected = references.get(i);
            for (int j = i; j < references.size(); j++) {
                assertEquals(expected, references.get(j));
            }
        }

    }

    @After
    public void tearDown() throws Exception {
        matches.clear();
        matches = null;
        //testedMap = null;
        msg = null;
    }

    private class Worker implements Runnable {

        private int number;
        private List<ColoursConnections> references;

        private Worker (int number, List<ColoursConnections> references) {
            this.number = number;
            this.references = references;
        }

        @Override
        public void run() {
            this.references.add(ColoursConnections.getInstance());
        }

        private void setnumber (int number) {
            this.number = number;
        }
    }
}