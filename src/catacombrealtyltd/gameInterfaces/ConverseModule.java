package catacombrealtyltd.gameInterfaces;

import static catacombrealtyltd.CatacombRealtyLtd.formattedNumber;
import static catacombrealtyltd.CatacombRealtyLtd.gatedScanner;
import static catacombrealtyltd.CatacombRealtyLtd.map;
import static catacombrealtyltd.CatacombRealtyLtd.player;
import static catacombrealtyltd.CatacombRealtyLtd.random;
import static catacombrealtyltd.utilities.AssetLoader.converseModuleDialogue;

/**
 *
 * @author seb
 */
public class ConverseModule extends AbstractGameModule{
        
    private final int scannerConstraints = 6;
    
    public ConverseModule() {
    }

    @Override
    protected void printUI() {
        
        String playerBankBalance = formattedNumber.format(player.getAccountBalance());
        String currentRoomValue = formattedNumber.format(currentRoom.getCurrentValue());
        
        gatedScanner.selectConstraints(scannerConstraints);
        System.out.println("[1.Talk]     [2.Murder]     [3.Bribe] ");
        System.out.println("             [4.Pray]                 ");
        System.out.println(" Navigate:   [0.Back]   ");
        System.out.println(" Worth: "+"You:" + playerBankBalance + " Them: Definitely less.");    
    }
    
    @Override
    protected int nextModule(int scannerInput) {
        switch(scannerInput){
            case 0:
                return 4;
            case 1:
                int totalLines = 10;
                int firstLine = 1 + (random.nextInt(totalLines) * 3);
                int lastLine = firstLine + 2;
                for(int i = firstLine; i <= lastLine; i++) {
                    System.out.print(converseModuleDialogue[i]);
                }
                altMessage = currentRoom.interact("persuadeTenant");
                return 6;
            case 2:
                altMessage = currentRoom.interact("murderTenant");
                return 6;
            case 3:
                altMessage = currentRoom.interact("bribeTenant");
                return 6;
            case 4:
                if(currentRoom.isContainsNPC()){
                    altMessage = "They look at you rather funny.\n";
                    return 6;
                }
                altMessage = "It won't absolve you of sin.\n";
                return 6;
        }
        
        return 0;    
    }

    
    @Override
    protected void printMainDisplay() {
        currentRoom = map.getCurrentRoom();
        currentRoom.print();
    }
    
}
