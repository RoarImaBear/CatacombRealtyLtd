/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catacombrealtyltd.gameInterfaces;

import static catacombrealtyltd.CatacombRealtyLtd.formattedNumber;
import static catacombrealtyltd.CatacombRealtyLtd.gatedScanner;
import static catacombrealtyltd.CatacombRealtyLtd.map;
import static catacombrealtyltd.CatacombRealtyLtd.player;
import static catacombrealtyltd.CatacombRealtyLtd.random;
import catacombrealtyltd.map.Room;
import java.util.ArrayList;

/**
 *
 * @author seb
 */
public class PhoneModule extends AbstractGameModule {
    
    int scannerConstraints;

    
    ArrayList<Room> roomsForSale;

    public PhoneModule() {
        this.altMessage = "                 cPhoneXL \n";
    }

    @Override
    protected void printUI() {
        String playerAccountBalance = formattedNumber.format(player.getAccountBalance());
        
        scannerConstraints = player.getRoomsForSale().size();
        gatedScanner.selectConstraints(scannerConstraints);
        System.out.println(" Accept: [1.][2.][3.]");
        System.out.println(" Accept: [4.][5.][6.]");
        System.out.println(" Accept: [7.][8.][9.]  ");
        System.out.println("           [0.Back]"  +" Bank: "+ playerAccountBalance);    
    }

//    @Override
//    public int display() {
//        currentRoom = map.getCurrentRoom();
//        message = currentRoom.getRoomDescription();
//        printEdge();
//        printMessage();
//        printDivider();
//        printMainDisplay();
//        printDivider();
//        printUI();
//        printEdge();
//        nextModule = nextModule(gatedScanner.scannerInput());
//        return nextModule;
//    }
    
    @Override
    protected void printMainDisplay() {
        roomsForSale = player.getRoomsForSale();
        calculateBuyerOffers(roomsForSale);
        if (!roomsForSale.isEmpty()){
            int i = 0;
            System.out.println("       http://www.CatacombRealty.fr");
            for(Room room : roomsForSale){
                System.out.println((i+1) + ".  " + room.getRoomDescription() + "   Best Offer:  " + formattedNumber.format(room.currentOffer));
                i++;
            }
            return;
        }
        else {
            System.out.println("       http://www.CatacombRealty.fr");
            for (int i = 0; i < 4; i++) {
                System.out.println("");
            }
        }
    }
    
    
    private void calculateBuyerOffers (ArrayList<Room> roomsForSale) {
        double currentValue;        
        for (Room room : roomsForSale) {
            currentValue = room.getCurrentValue();
            room.currentOffer = currentValue + (random.nextDouble() + 1) * (currentValue * 0.3) - (random.nextDouble() + 1) * (currentValue * 0.9);
        }
    }
    
    private void acceptOffer (int roomIndex){
        double offer = roomsForSale.get(roomIndex).currentOffer;
        player.changeAccountBalance(offer, '+');
        roomsForSale.remove(roomIndex);
    }

    @Override
    protected int nextModule(int scannerInput) {
        if (scannerInput != 0 && !roomsForSale.isEmpty()){
            acceptOffer(scannerInput - 1);
            altMessage = "Congratulations, you've accepted the bid!\n";
            return 7;
        }
        if (scannerInput == 0)
            return 4;
        altMessage = "There's nothing there.\n";
        return 7;
    }
    
}
