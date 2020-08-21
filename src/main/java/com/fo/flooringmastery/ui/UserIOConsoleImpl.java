/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.ui;

import java.util.Scanner;

/**
 *
 * @author TheFemiFactor
 */
public class UserIOConsoleImpl implements UserIO {
    
    Scanner userInput = new Scanner(System.in);
  
        @Override
    public void print(String prompt) {
         System.out.println(prompt);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        String userValue = userInput.nextLine();
        double value;
        if (userValue.isBlank()) {
            value = 0;
        }
        else {
        value = Double.parseDouble(userValue); 
        }
         return value;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        String userValue = userInput.nextLine();
        double value = Double.parseDouble(userValue);
        while(value < min || value > max)
            { 
                System.out.println("Please enter a value between " + min + " and"
                    + " " + max); 
                userValue = userInput.nextLine();
                value = Integer.parseInt(userValue);
            }
        return value;

    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        String userValue = userInput.nextLine();
        float value = Float.parseFloat(userValue);
        return value;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        String userValue = userInput.nextLine();
        float value = Float.parseFloat(userValue);
        while(value < min || value > max)
            { 
                System.out.println("Please enter a value between " + min + " and"
                    + " " + max); 
                userValue = userInput.nextLine();
                value = Integer.parseInt(userValue);
            }
        return value;
    }

    @Override
    public int readInt(String prompt) {
        boolean numOrNot = false;
        int value = 0;
        do {
        System.out.println(prompt);
        String userValue = userInput.nextLine();
        try { 
        value = Integer.parseInt(userValue);
        numOrNot = true;
        } catch(NumberFormatException e) {
            System.out.println("Not a valid number");
            numOrNot = false;
        }
        } while(!numOrNot);
        return value;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        
        System.out.println(prompt);
        boolean numOrNot = false;
        String userValue = "";
        int value = 0;
        
        do {
            
        userValue = userInput.nextLine();
        try { 
        value = Integer.parseInt(userValue);
        
        numOrNot = true;
        } catch(NumberFormatException e) {
            System.out.println("Please enter a valid number:");
            numOrNot = false;
        }
        } while(!numOrNot);
        
        while(value < min || value > max)
            { 
                System.out.println("Please enter a value between " + min + " and"
                    + " " + max); 
                userValue = userInput.nextLine();
                
                
                value = Integer.parseInt(userValue);
                
            }
        return value;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        String userValue = userInput.nextLine();
        long value = Long.parseLong(userValue);
        return value;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        String userValue = userInput.nextLine();
        long value = Long.parseLong(userValue);
        while(value < min || value > max)
            { 
                System.out.println("Please enter a value between " + min + " and"
                    + " " + max); 
                userValue = userInput.nextLine();
                value = Integer.parseInt(userValue);
            } 
        return value;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String userValue = userInput.nextLine();
        return userValue;
    }
    
}
