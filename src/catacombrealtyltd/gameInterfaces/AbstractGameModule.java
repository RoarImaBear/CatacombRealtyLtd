package catacombrealtyltd.gameInterfaces;

import static catacombrealtyltd.CatacombRealtyLtd.gatedScanner;
import static catacombrealtyltd.CatacombRealtyLtd.map;
import catacombrealtyltd.map.Room;
import static catacombrealtyltd.utilities.AssetLoader.interfaceTextures;


public abstract class AbstractGameModule {
    
    Room currentRoom = map.getCurrentRoom();
    int nextModule = 0;
    String message = currentRoom.getRoomDescription();
    String altMessage = null;
    // Each child module may call its own constraints.
    int playerInput;
    
    // Fetches pointer to currentRoom.
    
    //Use currentRoom function.
    
    
    public AbstractGameModule(){
        System.out.print(".");
    }
    
    public int display() {
        currentRoom = map.getCurrentRoom();
        message = currentRoom.getRoomDescription();
        printEdge();
        printMessage();
        printDivider();
        printMainDisplay();
        printDivider();
        printUI();
        printEdge();
        nextModule = nextModule(gatedScanner.scannerInput());
        return nextModule;
    }
    //Override for each Module
    abstract protected void printUI();
    abstract protected void printMainDisplay();
    abstract protected int nextModule(int scannerInput);
    
    // Combines assets to generate content.
    protected void printEdge(){
        System.out.print(interfaceTextures[1]);
    }
    protected void printDivider(){
        System.out.println(interfaceTextures[2]);
    }
        
    //Function that prints lines from the dialogue[], populated by text from
    //relevant dialogue files.
    public void printSpeech(int firstLine, int lastLine, String[] assetArray){
        for(int i = firstLine; i <= lastLine; i++) {
            System.out.print(assetArray[i]);
        }
    }
    
    protected void printMessage() {
        this.message = currentRoom.getRoomDescription();
        if (this.altMessage != null)
            System.out.print(this.altMessage);
        else
            System.out.print(this.message);
        this.altMessage = null;
    }
}
