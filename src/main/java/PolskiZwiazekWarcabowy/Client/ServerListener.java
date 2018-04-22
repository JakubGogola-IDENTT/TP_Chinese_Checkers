package PolskiZwiazekWarcabowy.Client;

import PolskiZwiazekWarcabowy.CommandParser;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is listener of commands from server.
 */
public class ServerListener implements Runnable {

    /**
     * Instance of client.
     */
    private GameClient gameClient;

    /**
     * Instance of main window with game.
     */
    private GameWindow gameWindow;

    /**
     * Instance of class with method to perform actions on graphic board.
     */
    private Actions actions;

    /**
     * Instance of client's PrintWriter.
     */
    private PrintWriter output;

    /**
     * Instance of client's BufferedReader.
     */
    private BufferedReader input;

    /**
     * Message from server.
     */
    private String message;

    /**
     * Parsed command.
     */
    private String command;

    /**
     * Variable to remember int value.
     */
    private int temp;

    /**
     * Status of disconnection.
     */
    private boolean isDisconectedOccured;

    public ServerListener (GameClient gameClient) {
        this.gameClient = gameClient;
        output = gameClient.getOutput();
        input = gameClient.getInput();
        isDisconectedOccured = false;
    }


    public void run () {
        while (true) {
            try {
                message = input.readLine();
            } catch (IOException ex) {

                Platform.runLater(() -> {
                    ClientGUI.getPrimaryStage().setScene(ClientGUI.getFirstScene());
                    ClientGUI.getPrimaryStage().setHeight(300);
                    ClientGUI.getPrimaryStage().setWidth(300);
                    isDisconectedOccured = true;
                });
            }

            if (isDisconectedOccured) {
                isDisconectedOccured = false;
                break;
            }

            command = CommandParser.getCommand(message);

            switch (command) {
                case "GAMESTART" :
                    temp = CommandParser.getArgumentAsInteger(message, 1);
                    gameClient.setNumberOfPlayers(String.valueOf(temp));
                    Platform.runLater(
                            () -> {
                                gameWindow = new GameWindow(gameClient, gameClient.getNumberOfPlayers());
                                actions = new Actions(gameWindow);
                            }
                    );
                    break;

                case "ROUNDSTART" :
                    gameClient.setIsPlayerRound(true);
                    Platform.runLater(() -> gameWindow.setRoundLabelText("YOUR ROUND!"));
                    break;

                case "SETCOLOUR" :
                    gameClient.setPlayerColour(CommandParser.getArgumentAsInteger(message, 1));
                    break;

                case "PAWNMOVED" :
                    int row1 = CommandParser.getArgumentAsInteger(message, 1);
                    int col1 = CommandParser.getArgumentAsInteger(message, 2);
                    int row2 = CommandParser.getArgumentAsInteger(message, 3);
                    int col2 = CommandParser.getArgumentAsInteger(message, 4);
                    actions.doMove(row1, col1, row2, col2);
                    break;

                case "ROUNDFINISHED" :
                    gameClient.setIsPlayerRound(false);
                    System.out.println("It is end of your round!");
                    Platform.runLater(() -> gameWindow.setRoundLabelText("ROUND FINISHED - WAIT!"));
                    break;

                case "CANNOTCREATEGAME" :
                    System.out.println("Game is already created! \nPlease - join to existing game!");
                    Scene scene = ClientGUI.getFirstScene();
                    Platform.runLater(() -> ClientGUI.getPrimaryStage().setScene(scene));
                    break;

                case "PLAYERWON" :
                    System.out.println("You won!");
                    Platform.runLater(()-> gameWindow.setRoundLabelText("YOU WON!"));
                    break;

                case "GAMEFINISHED" :
                    System.out.println("Game finished!");
                    Platform.runLater(() -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EndWindow.fxml"));
                            Parent root = fxmlLoader.load();
                            Scene endScene = new Scene(root);
                            ClientGUI.getPrimaryStage().setScene(endScene);
                            ClientGUI.getPrimaryStage().setHeight(300);
                            ClientGUI.getPrimaryStage().setWidth(300);
                        } catch(Exception ex) {
                            System.err.println(ex.toString());
                        }
                    });
                    break;
            }
        }
    }
}
