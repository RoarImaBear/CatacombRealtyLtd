package catacombrealtyltd.entities;

import static catacombrealtyltd.CatacombRealtyLtd.formattedNumber;
import catacombrealtyltd.map.Room;
import static catacombrealtyltd.utilities.AssetLoader.creatures;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author seb
 */
public class Player extends AbstractCreature{
    
    //Player stats.
    int handyWork = 0;
    int powerOfGab = 0;
    int attentionToDetail = 0;
    int mixedMartialArts = 0;
    int moralFabric = 5;
    
    
    ArrayList<Room> roomsForSale;
    
    private double accountBalance;
    
    public Player (){
        this.roomsForSale = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            this.model[i] = creatures[i];
        }
        this.accountBalance = -43212.68;
    }

    public void changeAccountBalance(double amount, char operator) {
        switch(operator){
            case '+':
                accountBalance += amount;
                break;
            case '-':
                accountBalance -= amount;
                break;
            case '*':
                accountBalance *= amount;
                break;
        }
    }

    public void listRoom(Room roomForSale) {
        roomsForSale.add(roomForSale);
    }

    public ArrayList<Room> getRoomsForSale() {
        return roomsForSale;
    }
    
    
    
    public double getAccountBalance() {
        return accountBalance;
    }
}
