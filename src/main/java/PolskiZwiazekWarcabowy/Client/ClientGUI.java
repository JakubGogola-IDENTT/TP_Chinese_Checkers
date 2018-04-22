package PolskiZwiazekWarcabowy.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main client GUI controller.
 */
public class ClientGUI extends Application {

    /**
     * Scene of invitation window.
     */
    private static Scene firstScene;

    /**
     * Holds loaded
     */
    private Parent root;

    /**
     * Primary stage which appears on start of game.
     */
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starting JavaFX application.
     * @param primaryStage primary stage.
     * @throws Exception throw exception if FXML file is unavailable.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientGUI.primaryStage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("InvitationWindow.fxml"));
        firstScene = new Scene(root, 300, 275);
        primaryStage.setTitle("Polski Związek Warcabowy");
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }

    /**
     * Returns instance of primary stage.
     * @return primary stage.
     */
    public static Stage getPrimaryStage () {
        return primaryStage;
    }

    /**
     * Returns instance of primary stage's scene.
     * @return primary stage's scene.
     */
    public static Scene getFirstScene () {
        return firstScene;
    }

}
