/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catacombrealtyltd.utilities;

import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author seb
 */
public class Utilities {
    
//  I/O UTILITY
    static char BREAK_POINT = '%';
    // Loads dynamically selected .txt files into arrays, allowing assets to be 
    // accessed and manipulated during gameplay.
    public static String[] fileInput(String fileName) {
        // Could work with array lists, but the memory waste is more or less negligible.
        String[] fileInput = new String[100];
        
        StringBuilder chunk = new StringBuilder();
        //try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./assets/" + name + ".txt"))){
        try (FileReader reader = new FileReader("./assets/" + fileName + ".txt") ){
            int readerOut;
            char chr;
            int i = 0;
            
            while ((readerOut = reader.read()) != -1) {
                chr = (char) readerOut;
                if (chr == BREAK_POINT) {
                    fileInput[i] = chunk.toString();
                    i++;
                    chunk = new StringBuilder();
                }
                else {
                    chunk.append(chr);
                }
            }
            reader.close();
        }
        catch (IOException e){
            //System.out.println("Game assets could not be read. Ensure files adhere to naming system.");
            //e.printStackTrace();
            //System.exit(0);
        }
        
        return fileInput;
    }

// SLEEP UTILITIES
    //Used to stall delivery of output.
    private static final int sleep = 10;
    public static void tinySleep(){
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ex) {
        }
    }
    public static void littleSleep(){
        try {
            Thread.sleep(sleep * 3);
        } catch (InterruptedException ex) {
        }
    }
    public static void mediumSleep(){
        try {
            Thread.sleep(sleep * 8);
        } catch (InterruptedException ex) {
        }
    }

//COMMAND LINE CLEAR UTILITIES
    public static void instantClear() {
        for(int i = 0; i < 20; i++)
            System.out.println("\n");
        
    }
    
    public static void slowClear() {
        for(int i = 0; i < 20; i++){
            System.out.println();
            tinySleep();
        }
        
    }
    
}
