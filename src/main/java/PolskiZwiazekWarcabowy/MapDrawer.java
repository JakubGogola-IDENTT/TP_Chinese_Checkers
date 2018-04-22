package PolskiZwiazekWarcabowy;

import PolskiZwiazekWarcabowy.Board.Board;

/**
 * Created by Kajtek on 2017-12-03.
 */
public class MapDrawer {

    Board map;
    public MapDrawer(Board board) {
        map = board;
    }

    public void draw() {

        Field[][] temp = map.getMap();

        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp[i].length; j++) {
                if(temp[i][j] == null) {
                    System.out.print("_");
                }
                else if ( temp[i][j].getPawn() != null){
                    System.out.print("X");
                }
                else System.out.print("*");
            }
            System.out.println("\n");
        }
    }
}
