package catacombrealtyltd;

import catacombrealtyltd.entities.Player;
import catacombrealtyltd.entities.AbstractCreature;
import catacombrealtyltd.map.Map;
import catacombrealtyltd.utilities.AssetLoader;
import catacombrealtyltd.utilities.GatedScanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Catacomb Realty Ltd
 * Grindset Mindset Type Game
 * @author Sebastian Dymanski
 */
public class CatacombRealtyLtd {
    
    public static int MAP_SIZE = 15;
    // Load game assets used for generating various GameModules. Must be done
    // before map generation.
    public static AssetLoader assetLoader = new AssetLoader();
    
    // Global objects updated and accessed by various objects.
    // These are also relevant to loading and saving the game.
    public static GatedScanner gatedScanner = new GatedScanner();
    public static Scanner scanner = new Scanner(System.in);
   
    public static NumberFormat formattedNumber = NumberFormat.getCurrencyInstance();
    public static Random random = new Random();
    
    public static int gameClock = 0; // 15min increments. += 1 with actions.
    
    public static ArrayList<AbstractCreature> NPCs = new ArrayList<>();
    public static Player player = new Player();
    public static Map map = new Map();
    
    public static void main(String[] args) {
        // Need a CUI Command-Line User Interface
        
        //CUI going well
        MainLoop mainLoop = new MainLoop();
        mainLoop.start();
        
        //Multiple threads??
    }
}

//        StartMenuModule startMenuModule = new StartMenuModule();
//        RoomEnter roomEnter = new RoomEnter();
//        RoomInteract roomInteract = new RoomInteract();
//        MapModule mapModule = new MapModule(MAP_SIZE);
//        MenuModule menuModule = new MenuModule();
//        OptionsModule optionsModule = new OptionsModule();
//        InventoryModule inventoryModule = new InventoryModule();
//        JournalModule journalModule = new JournalModule();
//        ConverseModule converseModule = new ConverseModule();
//        MainLoop mainLoop = new MainLoop(startMenuModule,roomEnter, roomInteract, 
//                mapModule, menuModule, optionsModule, inventoryModule, journalModule, converseModule);
