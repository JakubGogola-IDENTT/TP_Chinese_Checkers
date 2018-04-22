package PolskiZwiazekWarcabowy.Client;

import PolskiZwiazekWarcabowy.Client.GraphicBoard.GraphicBoard;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.PrintWriter;
import java.util.HashMap;

public class GameWindow {

    /**
     * Scene with board.
     */
    private Scene scene;

    /**
     * Pane to draw board.
     */
    private Pane pane;

    private GraphicBoard graphicBoard;

    /**
     * GraphicBoard with fields. This board is identical as board on server side.
     */
    private GraphicField[][] board;

    /**
     * Number of plyers to right set pawns.
     */
    private int numberOfPlayers;

    /**
     * Instance of client.
     */
    private GameClient client;

    /**
     * String to catch message from server.
     */
    private String message;

    /**
     * String to remember parsed command.
     */
    private String command;

    /**
     * Colour which belongs to player.
     */
    private int colour;

    /**
     * Map of colours connections.
     */
    private HashMap<Integer, Color> coloursConnections;

    /**
     * Flag - true if any pawn on board is activated.
     */
    private boolean isPawnActivated;

    /**
     * Instance of client's PrintWriter.
     */
    private PrintWriter output;

    /**
     * Button which ends round.
     */
    private Button endRoundButton;

    /**
     * Part of layout.
     */
    private HBox infoBar;

    /**
     * Label with information of colour.
     */
    private Label colourLabel;

    /**
     * Label with information of client's round status.
     */
    private Label roundLabel;

    /**
     * Constructor of main window of game.
     * @param client reference to GameClient.
     * @param numberOfPlayers number of players.
     */
    public GameWindow(GameClient client, String numberOfPlayers) {

        this.client = client;
        colour = client.getPlayerColour();
        output = client.getOutput();

        pane = new Pane();
        scene = new Scene(pane, 700, 550);
        scene.setFill(Color.AQUAMARINE);
        board = new GraphicField[19][29];
        coloursConnections = ColoursConnections.getInstance();
        try {
            this.numberOfPlayers = Integer.parseInt(numberOfPlayers);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        endRoundButton = new Button("End of round");
        endRoundButton.setOnAction(e -> {
            if (this.client.getIsPlayerRound()) {
                output.println("ENDROUND()");
            }
        });
        //pane.getChildren().add(endRoundButton);

        graphicBoard = new GraphicBoard(this.numberOfPlayers);
        board = graphicBoard.getBoard();

        infoBar = new HBox();
        pane.getChildren().add(infoBar);

        infoBar.getChildren().add(endRoundButton);
        infoBar.getChildren().add(new Separator());

        setPositions();
        setPlayerColour();
        colourLabel = new Label();
        colourLabel.setText("Your colour: ");
        infoBar.getChildren().add(colourLabel);
        infoBar.getChildren().add(new Separator());
        infoBar.getChildren().add(new Circle(10, coloursConnections.get(colour)));

        roundLabel = new Label("WAIT");
        infoBar.getChildren().add(new Separator());
        infoBar.getChildren().add(roundLabel);

        HandlersSetter hs = new HandlersSetter(this, this.client);
        hs.setHandlers();
        ClientGUI.getPrimaryStage().setScene(scene);
        ClientGUI.getPrimaryStage().setWidth(700);
        ClientGUI.getPrimaryStage().setHeight(600);
        ClientGUI.getPrimaryStage().setResizable(false);
    }

    /**
     * Setting positions of fields on screen.
     */
    private void setPositions () {
        double posX;
        double posY = 0;

        for (int i = 0 ; i < board.length; i++) {
            posX = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setCenterX(posX);
                    board[i][j].setCenterY(posY);
                    pane.getChildren().add(board[i][j]);
                }
                posX += 25;
            }
            posY += 30;
        }
    }

    /**
     * This method sets colour which belongs to client.
     */
    private void setPlayerColour() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && board[i][j].getColour() == coloursConnections.get(colour)) {
                    board[i][j].setIsPlayerPawn(true);
                } else if (board[i][j] != null && board[i][j].getColour() != coloursConnections.get(colour)) {
                    board[i][j].setIsOpponentPawn(true);
                }
            }
        }
    }

    /**
     * Return reference to array representation of graphic board.
     * @return array representation of graphic board.
     */
    public GraphicField[][] getBoard () {
        return board;
    }

    /**
     * Returns reference to Pane.
     * @return referenc to Pane.
     */
    public Pane getPane () {
        return pane;
    }

    /**
     * Returns reference to scene of this window.
     * @return reference to scene.
     */
    public Scene getScene () {
        return scene;
    }

    /**
     * Sets text in label with information of client's round status.
     * @param text information about round status.
     */
    public void setRoundLabelText(String text) {
        roundLabel.setText(text);
    }

}
