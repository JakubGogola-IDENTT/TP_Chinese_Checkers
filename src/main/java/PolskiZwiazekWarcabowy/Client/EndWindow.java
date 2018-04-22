package PolskiZwiazekWarcabowy.Client;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

/**
 * Window which appears when game finished.
 */
public class EndWindow {

    /**
     * Handler to close window and stop program.
     * @param mouseEvent event of mouse.
     */
    @FXML
    private void endClientHandler (MouseEvent mouseEvent) {
        System.exit(1);
    }


}
