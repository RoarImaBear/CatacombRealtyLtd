/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catacombrealtyltd.map;

import static catacombrealtyltd.CatacombRealtyLtd.MAP_SIZE;
import static catacombrealtyltd.CatacombRealtyLtd.player;

/**
 *
 * @author seb
 */
public class Map {
    
    // Map is a matrix of height and width = MAP_SIZE
    private final int width = MAP_SIZE;
    
    // Spawn coordinates, with y = rows, x = columns.
    private int y = width - 1;
    private int x = width / 2;

    public Room [][]rooms;
    private Room currentRoom;
    
    
    public Map(){
        this.rooms = new Room[width][width];
        for(int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                this.rooms[i][j] = new Room();
            }
        }
        this.currentRoom = rooms[y][x];
        currentRoom.addOccupant(player);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    // Moves player on the map.
    void insertPlayer () { //int roomI, int roomJ
        currentRoom.addOccupant(player);
        currentRoom.setContainsPlayer(true);
    }
    void removePlayer () { //int roomI, int roomJ
        currentRoom.removeOccupant(player);
        currentRoom.setContainsPlayer(false);
    }

    // Moves objects in a direction
    // Doesnt apply to objects -- xy tied to player.
    public String moveObject (Object object, int direction){
        switch(direction){
            case 1: //up
                if(y > 0){
                    y -= 1;
                    removePlayer();
                    currentRoom = rooms[y][x];  
                    insertPlayer();
                    return null;
                }
                else
                    return "The door won't budge.\n";
            case 2: //"left":
                if(x > 0){
                    x -= 1;
                    removePlayer();
                    currentRoom = rooms[y][x];  
                    insertPlayer();
                    return null;                    
                }
                else
                    return "The door won't budge.\n";
            case 3: //"down"
                if(y < width - 1){
                    y += 1;
                    removePlayer();
                    currentRoom = rooms[y][x];  
                    insertPlayer();
                    return null;
                }
                else
                    return "The door won't budge.\n";

            case 4: //"right"
                if(x < width - 1){
                    x += 1;
                    removePlayer();
                    currentRoom = rooms[y][x];  
                    insertPlayer();
                    return null;
                }
                else
                    return "The door won't budge.\n";
        }
        return "";
    }
    public void print() {
        for(int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                System.out.print(rooms[i][j].isContainsPlayer() + " ");       
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
    
}