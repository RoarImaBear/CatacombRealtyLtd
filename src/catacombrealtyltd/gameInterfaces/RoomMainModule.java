/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catacombrealtyltd.gameInterfaces;

import static catacombrealtyltd.CatacombRealtyLtd.MAP_SIZE;
import static catacombrealtyltd.CatacombRealtyLtd.formattedNumber;
import static catacombrealtyltd.CatacombRealtyLtd.gatedScanner;
import static catacombrealtyltd.CatacombRealtyLtd.map;
import static catacombrealtyltd.CatacombRealtyLtd.player;

public class RoomMainModule extends AbstractGameModule {

    // Encounters involve player + another entity
    
    private int scannerConstraints;    
    public RoomMainModule() {
    }

    
    @Override
    protected void printUI() {
        String playerBankBalance = formattedNumber.format(player.getAccountBalance());
        String currentRoomValue = formattedNumber.format(currentRoom.getCurrentValue());
        scannerConstraints = 9;
        gatedScanner.selectConstraints(scannerConstraints);
        System.out.println(" Explore: [1.Forward] [2.Left] [3.Right] [4.Back]");
        System.out.println(" Interact:        [5.Room] [6.NPC] [7.Phone]  ");
        System.out.println(" Player:              [8.Map]   [0.Exit]   ");
        System.out.println(" Bank: " + playerBankBalance + "    " + "Room Value: " + currentRoomValue);
    }

    @Override
    protected int nextModule(int scannerInput){
        switch(scannerInput){
            case 0:
                return 0;
            case 1:
                altMessage = player.explore("forward");
                return 1;
            case 2:
                altMessage = player.explore("left");
                return 2;
            case 3:
                altMessage = player.explore("right");
                return 3;
            case 4:
                altMessage = player.explore("back");
                return 4;
            case 5:
                if (currentRoom.isContainsNPC()){
                    altMessage = "Someone lives here\n";
                    return 4;
                }
                if (currentRoom.isSold()) {
                    altMessage = "The unit's already on the market.\n";
                    return 4;
                }
                    return 5;
            case 6:
                if(currentRoom.isContainsNPC())
                    return 6;
                else {
                    altMessage = "Why are you talking to yourself?\n";
                    return 4;
                }
            case 7:
                if (currentRoom.isReception())
                    return 7;
                else {
                    altMessage = "No reception.\n";
                    return 4;
                }
            case 8:
                return 8;
        }
        return 0;
    }

    @Override
    protected void printMainDisplay() {
        currentRoom = map.getCurrentRoom();
        currentRoom.print();
    }
}
