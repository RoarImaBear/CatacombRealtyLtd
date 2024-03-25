/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catacombrealtyltd.utilities;

/**
 *
 * @author seb
 */
public class AssetLoader {
    
    //Class that loads game assets accessed by GameModules.
    public static String[] ceilingTextures;
    public static String[] floorTextures;
    public static String[] converseModuleDialogue;
    public static String[] dialogue;
    public static String[] roomAssets;
    public static String[] interfaceTextures;
    public static String[] creatures;
    public static String[] characterNames;
    public static String[] corridorRoom;
    public static String[] tinyRoom;
    public static String[] smallRoom;
    public static String[] mediumRoom;
    public static String[] largeRoom;
    
    public AssetLoader(){
        loadAssets();
    }
    
    public static void loadAssets(){
        ceilingTextures = Utilities.fileInput("CeilingTextures");
        floorTextures = Utilities.fileInput("FloorTextures");
        interfaceTextures = Utilities.fileInput("InterfaceTextures");
        roomAssets = Utilities.fileInput("RoomAssets");
        creatures = Utilities.fileInput("Creatures");
        
        converseModuleDialogue = Utilities.fileInput("ConverseModuleDialogue");
        dialogue = Utilities.fileInput("Dialogue");
        characterNames = Utilities.fileInput("CharacterNames");
        corridorRoom = Utilities.fileInput("CorridorRoom");
        tinyRoom = Utilities.fileInput("TinyRoom");
        smallRoom = Utilities.fileInput("SmallRoom");
        mediumRoom = Utilities.fileInput("MediumRoom");
        largeRoom = Utilities.fileInput("LargeRoom");
    }
    
}
