package PolskiZwiazekWarcabowy.Client;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Window which appers during waiting for connection with server.
 */
public class WaitingWindow {

    /**
     * Scene of window.
     */
    private Scene scene;

    /**
     * Layout of window.
     */
    private VBox vBox;

    /**
     * Label with text.
     */
    private Label label;

    public WaitingWindow () {
        vBox = new VBox();
        scene = new Scene(vBox, 200, 200);
        label = new Label("Waiting for players...");
        vBox.getChildren().add(label);
        ClientGUI.getPrimaryStage().setScene(scene);
        ClientGUI.getPrimaryStage().setWidth(300);
        ClientGUI.getPrimaryStage().setHeight(300);
    }
}
