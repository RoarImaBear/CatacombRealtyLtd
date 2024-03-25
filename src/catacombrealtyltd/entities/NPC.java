package catacombrealtyltd.entities;

import static catacombrealtyltd.CatacombRealtyLtd.random;
import static catacombrealtyltd.utilities.AssetLoader.characterNames;
import static catacombrealtyltd.utilities.AssetLoader.creatures;

/**
 *
 * @author seb
 */
public class NPC extends AbstractCreature {

    public NPC() {
        this.name = characterNames[1 + random.nextInt(49)];
        this.creatureOrientation = 1;
        int randomCreatureModel = (random.nextInt(5) * 5);
        for (int i = 1; i <= 4; i++) {
            this.model[i] = creatures[randomCreatureModel + i];
        }
    }
    
}
