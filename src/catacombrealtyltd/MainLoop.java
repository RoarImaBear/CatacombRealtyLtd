package catacombrealtyltd;
import catacombrealtyltd.gameInterfaces.PhoneModule;
import static catacombrealtyltd.CatacombRealtyLtd.map;
import catacombrealtyltd.gameInterfaces.ConverseModule;
import catacombrealtyltd.gameInterfaces.RoomMainModule;
import catacombrealtyltd.gameInterfaces.MapModule;
import catacombrealtyltd.gameInterfaces.RoomInteractModule;
import catacombrealtyltd.gameInterfaces.StartMenuModule;
import catacombrealtyltd.map.Room;
import static catacombrealtyltd.utilities.Utilities.littleSleep;
import static catacombrealtyltd.utilities.Utilities.slowClear;

/**
 * Catacomb Realty Ltd
 * Mindset Type Game
 * @author Sebastian Dymanski
 */
public class MainLoop {    
    
    private int nextModule = 4;
    
    // Create module objects.
    StartMenuModule startMenuModule;
    RoomMainModule roomMainModule;
    RoomInteractModule roomInteractModule;
    MapModule mapModule;
    ConverseModule converseModule;
    PhoneModule phoneModule;
    
    
    Room currentRoom;
    
    public MainLoop (){
        this.startMenuModule = new StartMenuModule();
        this.roomMainModule = new RoomMainModule();
        this.roomInteractModule = new RoomInteractModule();
        this.mapModule = new MapModule();
        this.converseModule = new ConverseModule();
        this.phoneModule = new PhoneModule();
        
    }
    
    public void start(){
        System.out.println("All assets successfully loaded.");
        littleSleep();
        slowClear();
        
        // Loop through stateSelector until case 0 is called. 
        while (true) {
            
            //            for (int i = 0; i < 20; i++) System.out.println("");
            currentRoom = map.getCurrentRoom();
            nextModule = moduleSelect(nextModule);  
        }  
    }
    
    //stateSelect switches between various gamestates, serving corresponding CUIs
    public int moduleSelect(int nextInterface){
        //Error handling
        switch(nextInterface){
            case 0:
                //System.out.println("Whatever, beta cuck. Enjoy your soybeans.");
                System.out.println("Good bye!");
                System.exit(0);
            case 1:
            case 2:
            case 3:
            case 4: return roomMainModule.display();
            case 5: return roomInteractModule.display();
            case 6: return converseModule.display();
            case 7: return phoneModule.display();
            case 8: return mapModule.display();

            case 12: return startMenuModule.display();
                

        }
        return 0;
    }
}

