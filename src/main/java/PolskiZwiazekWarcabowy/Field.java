package PolskiZwiazekWarcabowy;

/**
 * Class identified with field on a map. Contains its coordinates, pawn and colour.
 */
public class Field {

    private int colour;
    private Pawn pawn;
    private int r;
    private int c;

    /**
     * Constructor that sets field coordinates.
     * @param r is row
     * @param c is column
     */
    public Field(int r, int c) {
        this.r = r;
        this.c = c;
        this.pawn = null;
    }

    /**
     * Getter for field row.
     * @return row number
     */
    public int getR() { return r; }

    /**
     * Getter for field column.
     * @return column number
     */
    public int getC() { return c; }

    /**
     * Sets colour of field.
     * @param colour is int flag colour value.
     */
    public void setColour(int colour) {
        this.colour = colour;
    }

    /**
     * MEthod that tells if there is any pawn on this field.
     * @return true if there is any pawn, false otherwise.
     */
    public boolean isOccupied() {
        if (pawn == null) return false;
        else {
            return true;
        }
    }

    /**
     * Getter for field colour (int flag value).
     * @return int colour value
     */
    public int getColour() {
        return colour;
    }

    /**
     * Sets pawn on this field and changes pawn location coordinates.
     * @param pawn is pawn to set on this field.
     */
    public void setPawn(Pawn pawn){
        this.pawn = pawn;
        if (pawn!=null) pawn.setLocation(r,c);
    }

    /**
     * Getter for pawn on this field.
     * @return pawn on this field.
     */
    public Pawn getPawn() {
        return pawn;
    }

}

