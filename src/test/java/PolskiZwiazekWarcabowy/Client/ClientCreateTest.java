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

public class ClientCreateTest {

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
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

    @Test
    public void testIfClientCorrectlyCreatesGameWithBot () {
        String msg;

        try {
            fakeClient = new FakeClient("2", "1");
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

        try {
            fakeClient.getOutput().println("CREATEGAME(2,1)");
            msg = input.readLine();
            assertEquals("CREATEGAME(2,1)", msg);
        } catch (IOException ex) {
            System.err.println(1);
            System.exit(1);
        }

    }

    @Test
    public void testIfClientCorrectlyCreatesGameWithAnotherPlayer () {
        String msg;
        Socket joinSocket;
        PrintWriter joinOutput;
        BufferedReader joinInput;
        FakeClient joinFakeClient;
        Thread joinThread;

        try {
            fakeClient = new FakeClient("2", "0");
            thread = new Thread(fakeClient);
            thread.start();
            socket = serverSocket.accept();
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            joinFakeClient = new FakeClient();
            joinThread = new Thread(joinFakeClient);
            joinThread.start();
            joinSocket = serverSocket.accept();
            joinInput = new BufferedReader(new InputStreamReader(joinSocket.getInputStream()));
            joinOutput = new PrintWriter(joinSocket.getOutputStream(), true);
            System.out.println("Connected with clients!");

            fakeClient.getOutput().println("CREATEGAME(2,0)");
            msg = input.readLine();
            assertEquals("CREATEGAME(2,0)", msg);

            msg = joinInput.readLine();
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
            System.err.println(ex);
            System.exit(1);
        }
    }

    private class FakeClient extends GameClient implements Runnable {

        private boolean isRunning;

        private FakeClient () {
            super();
            isRunning = false;
        }

        private FakeClient (String numberOfPlayers, String numberOfBots) {
            super(numberOfPlayers, numberOfBots);
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
