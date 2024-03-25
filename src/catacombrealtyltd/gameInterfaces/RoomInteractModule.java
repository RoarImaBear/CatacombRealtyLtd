package catacombrealtyltd.gameInterfaces;

import static catacombrealtyltd.CatacombRealtyLtd.formattedNumber;
import static catacombrealtyltd.CatacombRealtyLtd.gatedScanner;
import static catacombrealtyltd.CatacombRealtyLtd.map;
import static catacombrealtyltd.CatacombRealtyLtd.scanner;

/**
 *
 * @author seb
 */
public class RoomInteractModule extends AbstractGameModule{
        
    private final int scannerConstraints = 6;
    
    public RoomInteractModule() {
    }

    @Override
    protected void printUI() {
        
        double roomValue = currentRoom.getCurrentValue();
        double roomPotentialValue = currentRoom.getPotentialValue();
        String roomValueString = formattedNumber.format(roomValue);
        String roomPotentialValueString = formattedNumber.format(roomPotentialValue);
        
        gatedScanner.selectConstraints(scannerConstraints);
        System.out.println(" Expertise:  [1.Estimate] [2.Stage] [3.Sell] ");
        System.out.println(" Improve: [4.Clean] [5.Renovate] [6.Buy furniture]");
        System.out.println(" Navigate:             [0.Back]   ");
        System.out.println(" Room Value: " + roomValueString + "   " + "Potential Value: " + roomPotentialValueString);    
    }
    
    @Override
    protected int nextModule(int scannerInput) {
        switch(scannerInput){
            case 0:
                return 4;
            case 1:
                altMessage = currentRoom.interact("estimate");
                return 5;
            case 2:
                altMessage = currentRoom.interact("stage");
                return 5;
            case 3:
                altMessage = currentRoom.interact("list");
                return 5;
            case 4:
                altMessage = currentRoom.interact("clean");
                return 5;
            case 5:
                altMessage = currentRoom.interact("renovate");
                return 5;
            case 6:
                altMessage = currentRoom.interact("furnish");
                return 5;
            }
        return 0;    
    }

    @Override
    protected void printMainDisplay() {
        currentRoom = map.getCurrentRoom();
        currentRoom.print();
    }
    
}
