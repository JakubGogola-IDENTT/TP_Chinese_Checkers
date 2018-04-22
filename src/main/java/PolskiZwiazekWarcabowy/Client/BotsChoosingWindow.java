package PolskiZwiazekWarcabowy.Client;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * It is windows where player can choose number of bots.
 */
public class BotsChoosingWindow {

    /**
     * Scene of this window.
     */
    private Scene scene;

    /**
     * Layout of scene.
     */
    private BorderPane borderPane;

    /**
     * Label to display text.
     */
    private Label label;

    /**
     * Choice box with number of bots to choose.
     */
    private ChoiceBox choiceBox;

    /**
     * Number of bots.
     */
    private String numberOfBots;

    /**
     * Array  with available numbers of bots.
     */
    private String[] options;

    /**
     * Instance of current GameClient.
     */
    private GameClient client;

    /**
     * Temporary variable to remember value of an integer.
     */
    private int temp;

    /**
     * Instance of waiting window.
     */
    private WaitingWindow waitingWindow;

    /**
     * Creating bots choosing window.
     * @param numberOfPlayers is number of players.
     */
    public BotsChoosingWindow (String numberOfPlayers) {

        borderPane = new BorderPane();
        scene = new Scene(borderPane, 300, 300);
        choiceBox = new ChoiceBox();
        label = new Label("Choose number of bots");
        options = new String[] {"0", "1", "2", "3", "4", "5"};
        choiceBox.setItems(FXCollections.observableArrayList("0", "1", "2", "3", "4", "5"));

        temp = 0;
        try {
            temp = Integer.parseInt(numberOfPlayers);
        } catch (NumberFormatException ex) {
            System.err.println(ex.toString());
            System.exit(1);
        }
        borderPane.setTop(label);
        borderPane.setCenter(choiceBox);

        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int value = 0;
            try {
                value = Integer.parseInt(options[newValue.intValue()]);
            } catch (NumberFormatException ex) {
                System.err.println(ex.toString());
                System.exit(1);
            }
            if (value < temp) {
                numberOfBots = options[newValue.intValue()];
                client = new GameClient(numberOfPlayers, numberOfBots);
                waitingWindow = new WaitingWindow();
            }

        });

        ClientGUI.getPrimaryStage().setScene(scene);
        ClientGUI.getPrimaryStage().setWidth(300);
        ClientGUI.getPrimaryStage().setHeight(300);
    }

}
