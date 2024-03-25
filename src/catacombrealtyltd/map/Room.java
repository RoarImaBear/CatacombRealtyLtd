package catacombrealtyltd.map;

import static catacombrealtyltd.CatacombRealtyLtd.NPCs;
import static catacombrealtyltd.CatacombRealtyLtd.formattedNumber;
import catacombrealtyltd.entities.NPC;
import catacombrealtyltd.entities.Item;
import static catacombrealtyltd.CatacombRealtyLtd.player;
import static catacombrealtyltd.CatacombRealtyLtd.random;
import static catacombrealtyltd.CatacombRealtyLtd.scanner;
import catacombrealtyltd.entities.AbstractCreature;
import static catacombrealtyltd.utilities.AssetLoader.ceilingTextures;
import static catacombrealtyltd.utilities.AssetLoader.corridorRoom;
import static catacombrealtyltd.utilities.AssetLoader.floorTextures;
import static catacombrealtyltd.utilities.AssetLoader.largeRoom;
import static catacombrealtyltd.utilities.AssetLoader.mediumRoom;
import java.util.ArrayList;
import static catacombrealtyltd.utilities.AssetLoader.roomAssets;
import static catacombrealtyltd.utilities.AssetLoader.smallRoom;
import static catacombrealtyltd.utilities.AssetLoader.tinyRoom;


public class Room {
    // Room is an object contianing information about: room size.
    
    private final int[] sizeRiggedTable = {1, 1, 1, 1, 1, 2, 2, 3, 4, 5};
    private final int[] conditionRiggedTable = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2};
    private final boolean[] entitySpawnRiggedTable = {false, false, false, false, false, false, true, true, true, true};

    
    private String roomDescription;    
    private int size;
    public int condition;
    private int capacity;
        
    
    NPC occupant = null;
    private boolean containsPlayer = false;
    private boolean containsNPC = false;
    private boolean visited = false;    
    private boolean furnished = false;
    private boolean cleaned = false;
    private boolean renovated = false;
    private boolean staged = false;
    private boolean sold = false;
    private boolean reception = false;
    
    
    private double currentValue;
    private double potentialValue;
    public double currentOffer;
    
    //Each room has unique assets generated for it.
    private String[] playerModel;
    private String[][] roomBlocks = new String[5][5];
    private String ceiling;
    private String floor;
    ArrayList<AbstractCreature> occupants;
    ArrayList<Item> contents;
    
    
    public Room(){
        this.occupants = new ArrayList<>();
        this.contents = new ArrayList<>();      
        this.condition = conditionRiggedTable[random.nextInt(10)];
        this.size = sizeRiggedTable[random.nextInt(10)];
        this.capacity = size * 2;
        this.roomDescription = loadRoomDescription(size);
        
        this.reception = random.nextBoolean();
        
        if (this.size >1)
            if(entitySpawnRiggedTable[random.nextInt(10)]){
                occupant = new NPC();
                occupants.add(occupant);
                NPCs.add(occupant);
                containsNPC = true;
            }
        
        calculateValue();
        calculatePotentialValue();
        generateAssets();
        
        if(containsNPC)
            furnish();
        
    }
    
    
    public String interact(String interaction) {
        switch(interaction){
            case "estimate":
                calculatePotentialValue();
                return "Hmmm, yea... that seems about righ.\n";
            case "stage":
                if (!staged && furnished){
                    currentValue += ((1 + random.nextDouble() * 3 * currentValue));
                    staged = true;
                    return "Glass bottle here, child's toombstone there...\nfeels like home \n.";
                }
                else if (staged)
                    return "There's barely enough time for first guessing\nyourself. What would Joe Rogan do?\n";
                else
                    return "You probably need some furniture first...\n";
            case "clean":
                if(!cleaned){
                    cleaned = true;
                    currentValue *= 1.02;
                    return "Yeah. Yeah. Bring a bucket and a mop...\n";
                }
                else
                    return "The only way it's getting cleaner is \nto you call your mum.\n";
            case "renovate":
                if (!renovated) {
                    condition += random.nextInt(3);
                    generateAssets();
                    double cost = (0.1 + random.nextDouble()) * currentValue;
                    String costString = formattedNumber.format(cost);
                    player.changeAccountBalance(cost, '-');
                    renovated = true;
                    currentValue += (currentValue * random.nextDouble());
                    return "White walls, grey floors —" + costString + "\n";
                }
                else
                    return "It's already perfect... thanks to you!\n";
            case "furnish":
                if (!furnished) {
                    furnished = true;
                    double cost = (0.1 + random.nextDouble()) * currentValue;
                    String costString = formattedNumber.format(cost);
                    player.changeAccountBalance(cost, '-');
                    currentValue += (currentValue * random.nextDouble());
                    furnish();
                    return "You have impeccable taste —" + costString + "\n";
                }
                return "The room's already furnished.\n";
            case "list":
                if (!sold){
                    System.out.print("Name the Room: ");
                    this.roomDescription = scanner.nextLine() + "\n";
                    if(player.getRoomsForSale().size() < 9){
                        player.listRoom(this);
                        sold = true;
                        return "You put the unit on the market.\n";
                    }
                    return "You can only make 9 listings.\n";
                }
                return "The unit is already for sale. Adam Smith wouldn't\nwant you committing fraud.\n";
                
            case ("persuadeTenant"):
                if (random.nextBoolean()){
                    occupants.remove(occupant);
                    containsNPC = false;
                    return "Proponent of freeing the market, they left their\nhome for you to do as you please.\n";
                }
                return "There's no reasoning with communists.";
            case "murderTenant":
                occupants.remove(occupant);
                containsNPC = false;
                return "It's time to move the body.\nLet's hope mum doesn't find out.\n";
            case "bribeTenant":
                double bribe = 200 * (random.nextInt(10) + 1);
                player.changeAccountBalance(bribe, '-');
                containsNPC = false;
                return "You pay them the market price to go away.\n";
        }
        return "Couldn't do it.";
    }
    
    
    
// GETTERS AND SETTERS
    
    public String getRoomDescription() {
        return roomDescription;
    }

    public double getPotentialValue() {
        return potentialValue;
    }
    public double getCurrentValue() {
        return currentValue;
    }
    public int getSize() {
        return size;
    }

    public boolean isContainsNPC() {
        return containsNPC;
    }
    public boolean isContainsPlayer() {
        return containsPlayer;
    }
    public void setContainsPlayer(boolean containsPlayer) {
        this.containsPlayer = containsPlayer;
    }

    public boolean isReception() {
        return reception;
    }

    public boolean isSold() {
        return sold;
    }
    public boolean isVisited() {
        return visited;
    }
    

    
    

// GENERATE ROOM ASSETS
    // Pulls assets from assets object and places them into 2D array containing
    // with each row representing next block. Since blocks are interlaced 
    // during print, index[1] for each block will containt same asset types 
    // allowing them to load simultaneously. 
    private void generateAssets() {

        int j = 1;
        int assetLoadPattern;
        for (int i = 1; i <= 4; i++) {
            assetLoadPattern = j + condition;
            for (int k = 1; k <= 4; k++) {
                roomBlocks[k][i] = roomAssets[assetLoadPattern + random.nextInt(3)];
            }
            j += 10;
        }

        StringBuilder ceilingBlueprint = new StringBuilder();
        StringBuilder floorBlueprint = new StringBuilder();
        for (int i = 1; i <= size * 2; i++) {
            ceilingBlueprint.append(ceilingTextures[1 + condition + random.nextInt(3)]);
            floorBlueprint.append(floorTextures[1 + condition + random.nextInt(3)]);
        }
        ceiling = ceilingBlueprint.toString();
        floor = floorBlueprint.toString();
        roomDescription = loadRoomDescription(size);
    }
    
    private void furnish(){
        //furnitureStart MAX = 56. 
        int furnitureStart = 41 + random.nextInt(15);
        if (furnished){
            for (int i = 3; i <=4; i++) {
                for (int k = 1; k <= 4; k++) {
                    roomBlocks[k][i] = roomAssets[furnitureStart + k];
                }
                furnitureStart += 20;
            }   
        }
    }
    
    
    // Loads random room description relevant to size of room.
    private String loadRoomDescription(int size){
        // size 2-5
        
        StringBuilder string = new StringBuilder();
        int totalLines = 10;
        int firstLine = 1 + (random.nextInt(totalLines) * 3);
        int lastLine = firstLine + 2;
        for(int i = firstLine; i <= lastLine; i++) {
            switch (size) {
                case 1:
                    string.append(corridorRoom[i]);
                    break;
                case 2:
                    string.append(tinyRoom[i]);
                    break;
                case 3:
                    string.append(smallRoom[i]);
                    break;
                case 4:
                    string.append(mediumRoom[i]);
                    break;
                case 5:
                    string.append(largeRoom[i]);
                    break;
                default:
                    break;
            }
        }
        return string.toString();
    }
    
 // PRINT ROOM
    // Treat the room as a collection of n blocks, with each block holding a string.
    // First block contains player. Next 0-3 blocks contain room and furniture.
    // Last block may be a room block or an NPC block.
    // Uses first index [i] = 1 as to maintain consistency with input file handling.
    public void print() {
        playerModel = player.getModel();
        System.out.println(ceiling);
        if (containsNPC && size > 1){
            AbstractCreature npc = occupants.get(0);
            String[] model = npc.getModel();
            for (int i = 1; i <= 4; i++) {
                System.out.print(playerModel[i]);
                for (int j = 1; j <= size - 2; j++) {
                    System.out.print(roomBlocks[j][i]);
                }
                System.out.print(model[i]);
                System.out.println("");
            }
        }
        if (!containsNPC){
            
            for (int i = 1; i <= 4; i++) {
                System.out.print(playerModel[i]);
                for (int j = 1; j <= size - 1; j++) {
                    System.out.print(roomBlocks[j][i]);
                }
                System.out.println("");
            }
        }
        System.out.println(floor);
    }
    
// INSERT AND REMOVE OBJECTS AND OCCUPANTS
    public void addOccupant(AbstractCreature creature) {
        if (!occupants.contains(creature))
            occupants.add(creature);
        if (creature == player){
            containsPlayer = true;
            visited = true;
        }
    }
    public void removeOccupant(AbstractCreature creature) {
        if (!occupants.contains(creature))
            occupants.add(creature);
        if (creature == player)
            containsPlayer = false;
    }
    public void addItem (Item item) {
        if (contents.size() <= capacity){
            contents.add(item);
        }   
    }
    public void removeItem (Item item) {
        if (contents.contains(item)){
            contents.remove(item);
        }
    }
    
// CALCULATE VALUE    
    private void calculateValue() {
        currentValue = Math.pow(4, size)* 40 + condition * 800 + random.nextDouble() * 1000;
    }
    
// ROOM UPDATE
    // Reruns methods that update variables based on dynamic conditions.

    public void calculatePotentialValue() {
        potentialValue = currentValue * ((0.1 + random.nextDouble()) * 10) * (random.nextInt(7) + 1);
    }
}

  