package catacombrealtyltd.utilities;

import java.util.Scanner;

public class GatedScanner  {
    
    // GatedScanner creates scanner instance until input matches constraints set
    // within constraints array. This allows each interface to call constraints 
    // dynamically.
    private final Scanner scanner =new Scanner(System.in);
    private String[] constraints;
    // Arrays containing constraints for gated check — must be within appropriate
    // range for the options displayed.
    private final String[] constraintsOne = {"1", "0"};
    private final String[] constraintsTwo = {"1", "2", "0"};
    private final String[] constraintsThree = {"1", "2", "3", "0"};
    private final String[] constraintsFour = {"1", "2", "3", "4", "0"};
    private final String[] constraintsFive = {"1", "2", "3", "4", "5"};
    private final String[] constraintsSix = {"1", "2", "3", "4", "5", "6", "0"};
    private final String[] constraintsSeven = {"1", "2", "3", "4", "5", "6", "7"};
    private final String[] constraintsEight = {"1", "2", "3", "4", "5", "6", "7", "8", "0"};
    private final String[] constraintsNine = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    
    public GatedScanner() {
    }
    
    // Method scans for input and, if input fits constraints, returns as int.
    public int scannerInput (){
        while(true) {
            String input = scanner.nextLine();
            if (constraintsCheck(input, constraints)) {
                return Integer.parseInt(input);
            }
            System.out.println("Wrong input.");
        }
    }
    
    // Method checks if input is inside selected constraints array.
    private boolean constraintsCheck (String input, String[] conditions) {
        for (String condition : conditions) {
            if (input.equals(condition)) {
                return true;
            }
        }
        return false;
    }
    
    // Method used to select constraints.
    public void selectConstraints (int conditionsSelector) {
        switch (conditionsSelector){
            case 1:
                constraints = constraintsOne;
                break;
            case 2:
                constraints = constraintsTwo;
                break;
            case 3:
                constraints = constraintsThree;
                break;
            case 4:
                constraints = constraintsFour;
                break;
            case 5:
                constraints = constraintsFive;
                break;
            case 6:
                constraints = constraintsSix;
                break;
            case 7:
                constraints = constraintsSeven;
                break;
            case 8:
                constraints = constraintsEight;
                break;
            case 9:
                constraints = constraintsNine;
        }
        
    }
   
}
