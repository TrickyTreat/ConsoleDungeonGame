/*
 * FreeTiles.java
 *
 * All FreeTiles.
 *
 * author: Sascha W.
 * last edit / by: 2020-01-15 / Sascha W.
 */
package de.hdm_stuttgart.mi.DungeonGame.Helper.Logics.Stages;

//Import Statements
import de.hdm_stuttgart.mi.DungeonGame.Helper.Logics.Coordinate;
import de.hdm_stuttgart.mi.DungeonGame.Logics.Stages.Enum.FieldType;
import java.util.ArrayList;

public class FreeTiles {
    /**
     * Method to search for all FreeTiles
     *
     * @param room from Filed.java
     * @return List of all FreeTiles
     */
    public static ArrayList<Coordinate> freeTiles(FieldType[][] room) {
        ArrayList<Coordinate> freeSpace = new ArrayList<Coordinate>();
        for(int row = 1; row < room.length - 1; row++) {
            for(int column = 1; column < room[0].length - 1; column++) {
                if (room[row][column] == FieldType.Floor) {
                    freeSpace.add(new Coordinate(column, row));
                }
            }
        }
        return freeSpace;
    }
}
