package PolskiZwiazekWarcabowy.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Window where we can choose parameters of game, create new game or join to existing.
 */
public class InvitationWindow {

    /**
     * Number of players in new game.
     */
    private String numberOfPlayers = "";

    /**
     * Number of bots - 0 on start.
     */
    private String numberOfBots = "0";

    /**
     * Instance of client.
     */
    private GameClient client;

    /**
     * Instance of game window.
     */
    private GameWindow gameWindow;

    /**
     * Instance of waiting window.
     */
    private WaitingWindow waitingWindow;

    /**
     * instance of number of bot chooseing window
     */
    private BotsChoosingWindow botsChoosingWindow;

    /**
     * Instance of Scene.
     */
    private Scene scene;

    /**
     * Instance of pane.
     */
    private StackPane pane;

    /**
     * Handler for creating game with 2 players.
     * @param actionEvent ActionEvent.
     */
    @FXML
    private void newGameFor2PlayersHandler(ActionEvent actionEvent) {
        numberOfPlayers = "2";
        botsChoosingWindow = new BotsChoosingWindow(numberOfPlayers);
    }

    /**
     * Handler for creating game with 4 players.
     * @param actionEvent ActionEvent.
     */
    @FXML
    private void newGameFor4PlayersHandler(ActionEvent actionEvent) {
        numberOfPlayers = "4";
        botsChoosingWindow = new BotsChoosingWindow(numberOfPlayers);
    }

    /**
     * Handler for creating game for 3 players.
     * @param actionEvent ActionEvent.
     */
    @FXML
    private void newGameFor3PlayersHandler(ActionEvent actionEvent) {
        numberOfPlayers = "3";

        botsChoosingWindow = new BotsChoosingWindow(numberOfPlayers);
    }

    /**
     * Handler for creating game for 6 players.
     * @param actionEvent ActionEvent.
     */
    @FXML
    private void newGameFor6PlayersHandler(ActionEvent actionEvent) {
        numberOfPlayers = "6";
        botsChoosingWindow = new BotsChoosingWindow(numberOfPlayers);
    }

    /**
     * Handler for joining game.
     * @param mouseEvent MouseEvent.
     */
    @FXML
    private void joinGameHandler (MouseEvent mouseEvent) {
        System.out.println(numberOfPlayers);
        client = new GameClient ();
        waitingWindow = new WaitingWindow();
    }

    /**
     * Handler for displaying window with information about authors.
     * @param actionEvent ActionEvent.
     */
    @FXML
    private void infoWindowHandler (ActionEvent actionEvent) {
        Stage stage = new Stage();
        VBox vBox = new VBox();
        Label label = new Label("Jakub Gogola \n Kajetan Korzycki");
        Scene scene = new Scene(vBox, 100, 100);
        vBox.getChildren().add(label);
        stage.setScene(scene);
        stage.show();
    }

}
