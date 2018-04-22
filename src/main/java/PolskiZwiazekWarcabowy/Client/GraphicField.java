package PolskiZwiazekWarcabowy.Client;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Representation of Graphic field which extends Circle.
 * @see javafx.scene.shape.Circle
 */
public class GraphicField extends Circle {

    /**
     * Variable holds information about status if pawn is on field.
     */
    private boolean isPawn;

    /**
     * Variable holds information about status if pawn is player's pawn.
     */
    private boolean isPlayerPawn;

    /**
     * Variable holds information about status if pawn is opponent's pawn.
     */
    private boolean isOpponentPawn;

    /**
     * Creates new field.
     * @param radius radius of field.
     */
    public GraphicField(int radius) {
        super(radius);
        isPawn = false;
        isPlayerPawn = false;
        isOpponentPawn = false;
    }

    /**
     * Returns information about status if any pawn is on field.
     * @return true when pawn is on field, oth. false.
     */
    public boolean getIsPawn() {
        return isPawn;
    }

    /**
     * Setting pawn status on field.
     * @param isPawn pawn status.
     */
    public void setIsPawn(boolean isPawn) {
        this.isPawn = isPawn;
    }

    /**
     * Returns information if pawn on field belongs to player.
     * @return true if it's player's pawn, oth. false.
     */
    public boolean getIsPlayerPawn() {
        return isPlayerPawn;
    }

    /**
     * Sets information if pawn on field belongs to player.
     * @param isPlayerPawn information about pawn status.
     */
    public void setIsPlayerPawn(boolean isPlayerPawn) {
        this.isPlayerPawn = isPlayerPawn;
    }

    /**
     * Returns information if pawn on field belongs to opponent.
     * @return true if pawn belongs to player, oth. false.
     */
    public boolean getIsOpponentPawn () {
        return isOpponentPawn;
    }

    /**
     * Sets information if pawn on field belongs to opponent
     * @param isOpponentPawn is pawn status.
     */
    public void setIsOpponentPawn (boolean isOpponentPawn) {
        this.isOpponentPawn = isOpponentPawn;
    }

    /**
     * Return field's colour.
     * @return field's colour.
     */
    public Paint getColour () {
        return this.getFill();
    }

}
