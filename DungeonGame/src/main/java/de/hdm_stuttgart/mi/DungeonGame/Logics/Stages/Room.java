/*
 * Room.java
 *
 * Class for room creation and managing.
 *
 * author: Sascha W.
 * last edit / by: 2020-01-15 / Sascha W.
 */
package de.hdm_stuttgart.mi.DungeonGame.Logics.Stages;

//Import statements
import de.hdm_stuttgart.mi.DungeonGame.Helper.Stages.CreateEnemies;
import de.hdm_stuttgart.mi.DungeonGame.Helper.Stages.CreateWallAndFloor;
import de.hdm_stuttgart.mi.DungeonGame.Helper.Stages.Entry;
import de.hdm_stuttgart.mi.DungeonGame.Helper.Stages.PutEntryAndExit;
import de.hdm_stuttgart.mi.DungeonGame.Logics.Actors.Enemy;
import de.hdm_stuttgart.mi.DungeonGame.Logics.Enum.Difficulty;
import de.hdm_stuttgart.mi.DungeonGame.Logics.Stages.Enum.FieldType;

import java.util.ArrayList;

/**
 * Junction of all Field related classes.
 */
public class Room {

    //Attributes

    //random FieldType hight
    private final int HIGHT = (int) (Math.random() * 9) + 6;
    //random FieldType width
    private final int WIDTH = (int) (Math.random() * 19) + 6;
    //new entry
    private Entry entry;
    //List with all entrys
    private ArrayList<Entry> doorsAndStairs = new ArrayList<Entry>();
    //random roomType
    private final int ROOMTYPE = (int) (Math.random() * 100);
    //Difficulty
    private final Difficulty DIFFICULTY;
    //List of all Enemies
    private ArrayList<Enemy> enemies;
    //List of all Items
    //private ArrayList<Item> items;

    /**
     * FieldType is the main array in which the end Field will be saved.
     */
    private FieldType[][] room = new FieldType[HIGHT][WIDTH];

    /**
     * Constructor to Field.
     *
     * @param entry is the new direction for entry position.
     */
    public Room(Difficulty difficulty, Entry entry) {
        this.DIFFICULTY = difficulty;
        this.entry = entry;

        //Fill room-array with simple wall and floor
        new CreateWallAndFloor(room);

        /**
         * Selection of roomtypes.
         */
        if(ROOMTYPE <= 100 && ROOMTYPE >= 0) {
            //Adding entry and exit
            new PutEntryAndExit(entry, room, doorsAndStairs);
        //ToDo: Different map genertors
        }

        enemies = CreateEnemies.fillEnemyList(room);
        refreshRoom();
    }

    /**
     * Refresh room with new Player and Enemy Coordinates
     */

    public void refreshRoom() {
        room[Field.getPlayer().GetCoordinate().getyCoordinate()][Field.getPlayer().GetCoordinate().getxCoordinate()] = FieldType.Player;
        for(int enemycount = 0; enemycount < enemies.size(); enemycount++) {
            room[enemies.get(enemycount).GetCoordinate().getyCoordinate()][enemies.get(enemycount).GetCoordinate().getxCoordinate()] = FieldType.Enemy;
        }
    }

    /**
     * Method to trigger other mechanics
     */

    public void checkPlayerField() {
        //ToDo:Item and Exit check
    }


    /**
     * Passes on the EntryDirection for the next field.
     *
     * @return dirction of the new entry.
     */
    public Entry getEntry() {
        return entry;
    }


    /**
     * Returns FieldType for Field-, Player-interactions or rendering.
     *
     * @return a specific FieldType.
     */
    public FieldType[][] getRoom() {
        return room;
    }

    /**
     * Returns a requested FieldType
     *
     * @param xCoordinate of requested tile
     * @param yCoordinate of requested tile
     * @return FieldType of requested tile
     */
    public FieldType getFieldType(int xCoordinate, int yCoordinate) {
        return room[yCoordinate][xCoordinate];
    }
}
