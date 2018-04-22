package PolskiZwiazekWarcabowy.Client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class ClientJoinTest {

    private FakeClient fakeClient;

    Thread thread;

    private ServerSocket serverSocket;

    private Socket socket;

    private BufferedReader input;

    private PrintWriter output;

    @Before
    public void initialize () {
        try {
            serverSocket = new ServerSocket(4444);
            fakeClient = new FakeClient();
            thread = new Thread(fakeClient);
            thread.start();
            socket = serverSocket.accept();

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected with client!");
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(1);
        }
    }

    @Test
    public void testIfClientCorrectlyJoinToGame () {
        String msg;

        try {
            msg = input.readLine();
            assertEquals("JOINGAME()", msg);
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(1);
        }
    }

    @After
    public void tearDown () {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(1);
        }
    }

    private class FakeClient extends GameClient implements Runnable {

        private boolean isRunning;

        private FakeClient () {
            super();
            isRunning = false;
        }

        @Override
        public void run() {
            if (!isRunning) {
                isRunning = true;
            }
        }
    }

}
