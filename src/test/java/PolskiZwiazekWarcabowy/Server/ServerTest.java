package PolskiZwiazekWarcabowy.Server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ServerTest {

    private Socket socket;

    private BufferedReader input;

    private PrintWriter output;

    private Thread thread;

    private FakeServer fakeServer;

    String msg;

    @Before
    public void setUp () {
        fakeServer = new FakeServer();
        thread = new Thread(fakeServer);
        thread.start();

        try {
            socket = new Socket("localhost", 4444);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to server.");
        }
        catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(-1);
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            System.err.println(ex.toString());
        }
    }

    @Test
    public void testCommandsForOnePlayerOneBot () {

        output.println("CREATEGAME(2,1)");
        try {
            msg = input.readLine();
            assertEquals("SETCOLOUR(1)", msg);
            msg = input.readLine();
            assertEquals("GAMESTART(2)", msg);
            msg = input.readLine();
            assertEquals("ROUNDSTART()", msg);

            output.println("MOVEPAWN(4,11,5,12)");
            msg = input.readLine();
            assertEquals("MOVEPOSSIBLE()", msg);
            msg = input.readLine();
            assertEquals("PAWNMOVED(4,11,5,12)", msg);

            output.println("ENDROUND()");
            msg = input.readLine();
            assertEquals("ROUNDFINISHED()", msg);

        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

    @Test
    public void testCommandsForOnePlayerTwoBots () {

        output.println("CREATEGAME(3,2)");
        try {
            msg = input.readLine();
            assertEquals("SETCOLOUR(1)", msg);
            msg = input.readLine();
            assertEquals("GAMESTART(3)", msg);
            msg = input.readLine();
            assertEquals("ROUNDSTART()", msg);

            output.println("MOVEPAWN(4,11,5,12)");
            msg = input.readLine();
            assertEquals("MOVEPOSSIBLE()", msg);
            msg = input.readLine();
            assertEquals("PAWNMOVED(4,11,5,12)", msg);

            output.println("ENDROUND()");
            msg = input.readLine();
            assertEquals("ROUNDFINISHED()", msg);

        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(1);
        }
    }

    @Test
    public void testCommandsForOnePlayerThreeBots () {

        output.println("CREATEGAME(4,3)");
        try {
            msg = input.readLine();
            assertEquals("SETCOLOUR(1)", msg);
            msg = input.readLine();
            assertEquals("GAMESTART(4)", msg);
            msg = input.readLine();
            assertEquals("ROUNDSTART()", msg);

            output.println("MOVEPAWN(4,11,5,12)");
            msg = input.readLine();
            assertEquals("MOVEPOSSIBLE()", msg);
            msg = input.readLine();
            assertEquals("PAWNMOVED(4,11,5,12)", msg);

            output.println("ENDROUND()");
            msg = input.readLine();
            assertEquals("ROUNDFINISHED()", msg);

        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(1);
        }
    }

    @Test
    public void testCommandsForOnePlayerFiveBots () {

        output.println("CREATEGAME(6,5)");
        try {
            msg = input.readLine();
            assertEquals("SETCOLOUR(1)", msg);
            msg = input.readLine();
            assertEquals("GAMESTART(6)", msg);
            msg = input.readLine();
            assertEquals("ROUNDSTART()", msg);

            output.println("MOVEPAWN(4,11,5,12)");
            msg = input.readLine();
            assertEquals("MOVEPOSSIBLE()", msg);
            msg = input.readLine();
            assertEquals("PAWNMOVED(4,11,5,12)", msg);

            output.println("ENDROUND()");
            msg = input.readLine();
            assertEquals("ROUNDFINISHED()", msg);

        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

    @After
    public void tearDown () {
        try {
            fakeServer.getServerSocket().close();
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }


    }

    private class FakeServer extends GameServer implements Runnable {

        private boolean isRunning;

        private FakeServer () {
            isRunning = false;
        }

        @Override
        public synchronized void run() {
            if(!isRunning) {
                this.runServer();
                isRunning = true;
            }
        }
    }

}
