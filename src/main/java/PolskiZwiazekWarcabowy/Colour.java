package PolskiZwiazekWarcabowy;

/**
 * Class with int value of colour and its opposite.
 */
public class Colour {
    private int colour;
    private int oppositeColour;

    /**
     * Constructor that sets fields values.
     * @param colour is int value of colour. It is a flag, starting from top
     *               with 1 and then clockwise to 6 (counting the triangles on board).
     */
    public Colour(int colour) {
        this.colour = colour;
        setOppositeColour(colour);
    }

    /**
     * Getter for int colour value.
     * @return
     */
    public int getColour() {
        return colour;
    }

    /**
     * Getter for opposite int colour value.
     * @return
     */
    public int getOppositeColour() {
        return oppositeColour;
    }

    /**
     * Sets value of colour field and opposite colour.
     * @param colour
     */
    public void setColour(int colour) {
        this.colour = colour;
        setOppositeColour(colour);
    }

    /**
     * Sets value of opposite volour, depending on player colour.
     * Opposite colour is always in triangle opposite to players triangle.
     * @param colour is player colour.
     */
    private void setOppositeColour(int colour) {
        switch (colour) {
            case 1:
                oppositeColour = 4;
                break;
            case 2:
                oppositeColour = 5;
                break;
            case 3:
                oppositeColour = 6;
                break;
            case 4:
                oppositeColour = 1;
                break;
            case 5:
                oppositeColour = 2;
                break;
            case 6:
                oppositeColour = 3;
                break;
        }
    }
}
