package PolskiZwiazekWarcabowy.Client;

import javafx.event.EventTarget;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * This class allows to set handlers to handle event on graphical layer of client.
 */
public class HandlersSetter  {

    /**
     * Reference to main window with game.
     */
    private GameWindow gameWindow;

    /**
     * Reference to client.
     */
    private GameClient client;

    /**
     * Reference to array representation of board.
     */
    private GraphicField[][] board;

    /**
     * List with clicked fields.
     */
    private ArrayList<GraphicField> fields;

    /**
     * Instance of graphic field.
     */
    private GraphicField field;

    /**
     * Reference to GameWindow's Pane.
     */
    private Pane pane;

    /**
     * Target of an event/
     */
    private EventTarget target;

    /**
     * Command from server;
     */
    private String command;

    /**
     * Builder of command.
     */
    private StringBuilder stringBuilder;

    /**
     * Constructor of handler's setter.
     * @param gameWindow reference to main window with game.
     * @param client reference to client.
     */
    public HandlersSetter (GameWindow gameWindow, GameClient client) {
        this.gameWindow = gameWindow;
        this.client = client;
        this.board = gameWindow.getBoard();
        this.pane = gameWindow.getPane();
        fields = new ArrayList<>();
        fields.clear();
    }

    /**
     * Sets handlers.
     */
    public void setHandlers () {
        pane.setOnMouseClicked(e -> {
            target = e.getTarget();
            if (target.getClass() == GraphicField.class) {

                field = (GraphicField) target;

                if (!field.getIsPawn()) {
                    fields.add(field);
                } else {
                    fields.clear();
                    fields.add(field);
                }

                if (fields.size() == 2) {
                    command = "MOVEPAWN(";
                    stringBuilder = new StringBuilder(command);
                    for (int i = 0; i < fields.size(); i++) {
                         stringBuilder.append(getPos(fields.get(i)));
                        if (i < fields.size() -1 ) {
                            stringBuilder.append(",");
                        }
                    }
                    stringBuilder.append(")");
                    command = stringBuilder.toString();
                    System.out.println(command);
                    if (fields.get(0).getIsPlayerPawn() && client.getIsPlayerRound()) {
                        client.getOutput().println(command);
                    }
                    fields.clear();
                }
            }
        });
    }

    /**
     * Compute's position of field.
     * @param graphicField field to compute position.
     * @return position of field.
     */
    private String getPos (GraphicField graphicField){
        String str = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && board[i][j].equals(graphicField)) {
                    str = String.valueOf(i) + "," + String.valueOf(j);
                    System.out.println(str);
                    break;
                }
            }
        }
        return str;
    }

}
