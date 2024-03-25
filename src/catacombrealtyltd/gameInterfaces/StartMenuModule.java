/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catacombrealtyltd.gameInterfaces;

import static catacombrealtyltd.CatacombRealtyLtd.gatedScanner;
import static catacombrealtyltd.CatacombRealtyLtd.map;
import static catacombrealtyltd.CatacombRealtyLtd.player;
import static catacombrealtyltd.utilities.AssetLoader.dialogue;
import static catacombrealtyltd.utilities.Utilities.instantClear;
import static catacombrealtyltd.utilities.Utilities.littleSleep;
import static catacombrealtyltd.utilities.Utilities.mediumSleep;
import java.util.Scanner;

/**
 *
 * @author seb
 */
public class StartMenuModule extends AbstractGameModule {

    private final Scanner scanner = new Scanner(System.in);
    
    public StartMenuModule (){
        super();
    }
    
    //This is the Start Menu Script when beginning a new game.
    //If successful returns >> mainLoop.switch[5] >> roomModule
    @Override
    public int display(){
        System.out.println();
        printSpeech(1, 12, dialogue);
        
        //User input prompted.
        System.out.println();
        System.out.println("1) Yes. 2) No");
        gatedScanner.selectConstraints(2);
        playerInput = gatedScanner.scannerInput();
        if (playerInput == 2) return 0;
        
        System.out.println("Excellent! What's your name?");
        player.name = scanner.nextLine();
        mediumSleep();
        instantClear();
        System.out.println("Hello, " + player.name + ".\n\n\n\n\n\n\n");
        mediumSleep();
        instantClear();

        System.out.println(dialogue[13] + "\n\n\n\n\n\n\n");
                
        nextModule = 5;
        return nextModule;
    }

    @Override
    protected int nextModule(int scannerInput) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void printUI() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    protected void printMessage() {
       System.out.print(currentRoom.getRoomDescription());
    }
    
    @Override
    protected void printMainDisplay() {
        currentRoom = map.getCurrentRoom();
        currentRoom.print();
    }
    
}

        /*This is the boot up.
        Bla bla bla
        Name:
        *name*, you're a hustler. 

        I know you. You're young. Eager. Relentless. Ready to work, grind and husle. Ready to leave your mark on the most important, value driven, transformative industry in the world. Real Estate. Parisian Real Estate. While the city of Paris has been all but devoured by decrepid never-retirees and their yuppie trust fund spawn, you know something they don't... The underground is ripe for the taking. You will dwell lower than any realtor. Dig deeper than any flipper. Plunge further to get the plunder. You will gentrify the Parisian catacombs one burial chamber at a time.
        */
