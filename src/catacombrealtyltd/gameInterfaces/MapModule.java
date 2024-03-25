package catacombrealtyltd.gameInterfaces;

import static catacombrealtyltd.CatacombRealtyLtd.MAP_SIZE;
import static catacombrealtyltd.CatacombRealtyLtd.gatedScanner;
import static catacombrealtyltd.CatacombRealtyLtd.map;

/**
 *
 * @author seb
 */
public class MapModule extends AbstractGameModule{
    
    
    int scannerConstraints = 1;

    public MapModule() {
    }

    @Override
    protected void printMessage() {
        System.out.println("These are the traces of your journey.");
    }

    @Override
    protected void printUI() {
        gatedScanner.selectConstraints(scannerConstraints);
        System.out.println(" Navigate:          [0.Back]   ");
    }

    @Override
    protected void printMainDisplay() {    
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map.rooms[i][j].isContainsPlayer())
                    System.out.print(" @ ");
                else if (map.rooms[i][j].isVisited())
                    System.out.print(" " + map.rooms[i][j].getSize() + " ");
                else
                    System.out.print(" ? ");
            }
            System.out.println("");
        }
    }

    @Override
    protected int nextModule(int scannerInput) {
        return 4;
    }
    

    
}
