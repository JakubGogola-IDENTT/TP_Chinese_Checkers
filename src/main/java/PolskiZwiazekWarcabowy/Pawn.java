package PolskiZwiazekWarcabowy;

/**
 * Class identified with pawn in a game. Contains its location and colour (flag value).
 */
public class Pawn {

    private Colour colour;
    private int r;
    private int c;

    /**
     * Constructor that sets colour value (location is set when pawn is placed somewhere on map).
     * @param colour
     */
    public Pawn(int colour) {
        this.colour = new Colour(colour);
    }

    /**
     * Sets pawn colour value (int flag).
     * @param colour is int colour value to set.
     */
    public void setColour(int colour) {
        this.colour.setColour(colour);
    }

    /**
     * Getter for pawn colour (int flag).
     * @return int value of pawn colour.
     */
    public int getColourInt() {
        return colour.getColour();
    }

    /**
     * Getter for pawn colour (Colour object).
     * @return Colour field value.
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Getter for value of colour opposite to this pawn.
     * @return int value of opposite colour
     */
    public int getOppositeColourInt() {
        return colour.getOppositeColour();
    }

    /**
     * Sets pawn location.
     * @param r is pawn new row.
     * @param c is pawn new column.
     */
    public void setLocation(int r, int c) {
        this.r = r;
        this.c = c;
    }

    /**
     * Getter for pawn row.
     * @return pawn location row.
     */
    public int getRow() { return r; }

    /**
     * Getter for pawn column.
     * @return pawn location column.
     */
    public int getColumn() { return c; }

    /**
     * Overridden method to compare two pawns - they are equal when they have the same colour.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pawn)) {
            return false;
        }
        Pawn other = (Pawn) o;
        return (getColourInt()==other.getColourInt());
    }


}
