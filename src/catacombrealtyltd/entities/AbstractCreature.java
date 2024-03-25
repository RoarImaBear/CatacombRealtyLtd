package catacombrealtyltd.entities;

import static catacombrealtyltd.CatacombRealtyLtd.map;

/**
 *
 * @author seb
 */
public abstract class AbstractCreature implements CanMove{
    
    
    public String name;
    String[] model = new String[5];
    int[] inventory = new int[10];
    
    // Physical orientation in relation to the map[][]. 1: ↑; 2: ←; 3:↓; 4: → 
    int creatureOrientation = 1;
    
    public AbstractCreature() {
    }

    public String[] getModel() {
        return model;
    }
    
    // Movement method that allows creatures to move according to the direction they're heading.
    // Forward moves ahead according to orientation. Left, right and back change orientation.
    @Override
    public String explore (String direction){
        switch (direction){
            case "forward":
                return map.moveObject(this, creatureOrientation);
            case "left":
                if (creatureOrientation < 4)
                    creatureOrientation += 1;
                else
                    creatureOrientation = 1;
                return map.moveObject(this, creatureOrientation);
            case "right":
                if (creatureOrientation > 1)
                    creatureOrientation -= 1;
                else
                    creatureOrientation = 4;
                return map.moveObject(this, creatureOrientation);
            case "back":
                creatureOrientation += 2;
                if (creatureOrientation == 5)
                    creatureOrientation = 1;
                if(creatureOrientation == 6)
                    creatureOrientation = 2;
                return map.moveObject(this, creatureOrientation);
        }
        return "";
    }
}
