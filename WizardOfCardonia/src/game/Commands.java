package game;

import java.util.*;

public class Commands {
	// Clears the screen (platform-dependent)
    public static void clearScreen() {
        try {
            // For Windows
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            // For Unix-based systems (Linux, macOS)
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing screen.");
        }
    }
    
    
    
    /******************************
     * pressEnter
     * This method makes a pause in the program until the user enters anything
     ******************************/
    public static void pressEnter(){
        Scanner scanS = new Scanner(System.in);
        
        for(int i = 0; i < 80; i++){System.out.print("*");}
        System.out.println("\nPress [Enter] to continue");
        scanS.nextLine();
        
        clearScreen();
    }
    
    
    
    /*******************************************
     * inputInt
     * This method traps the user to input one of the right choices
     * @param min - the minimum choice the user must make
     * @param max - the maximum choice the user must make
     * @return - returns the user's input
     *******************************************/
    public static int inputInt(int min, int max){
        Scanner scanN = new Scanner(System.in);
        int input = 0;
        boolean flag = true;
        
        do{
            try{
                System.out.println("********************************************************************************\n");
                System.out.print("Enter input (int): ");
                input = scanN.nextInt();
                
                while(input < min || input > max){
                    System.out.println("********************************************************************************\n");
                    System.out.print("Invalid input, please try again: ");
                    input = scanN.nextInt();
                }
                
                flag = false;
            }catch(InputMismatchException e){
                System.out.println("********************************************************************************\n");
                System.out.println("Please enter an integer value!");
                scanN.nextLine();
            }
        }while(flag);
        
        clearScreen();
        
        return input;
    }
    
    
    
    /*********************************
     * inputString
     * This method gets a string input from the console and returns it
     * @return - returns the string input
     *********************************/
    public static String inputString(){
        Scanner scanS = new Scanner(System.in);
        
        String string = scanS.nextLine();
        
        clearScreen();
        
        return string;
    }
    
    
    
    public static int skillMultiplier(int num, card.Type type) {
    	double skillMultiplier;
    	
    	if(type == card.Type.PHYSICAL) {
    		skillMultiplier = 1 + ((Main.testStrength-1) / 20.0);
    	}
    	else if(type == card.Type.MAGICAL) {
    		skillMultiplier = 1 + ((Main.testIntelligence-1) / 20.0);
    	}
    	else {
    		skillMultiplier = 1 + ((Main.testArcana-1) / 20.0);
    	}

		num = (int) Math.ceil(num * skillMultiplier);
    	
    	return num;
    }
    
    
    
    public static double skillMultiplier(double num, card.Type type) {
    	double skillMultiplier;
    	
    	if(type == card.Type.PHYSICAL) {
    		skillMultiplier = 1 + ((Main.testStrength-1) / 20.0);
    	}
    	else if(type == card.Type.MAGICAL) {
    		skillMultiplier = 1 + ((Main.testIntelligence-1) / 20.0);
    	}
    	else {
    		skillMultiplier = 1 + ((Main.testArcana-1) / 20.0);
    	}

		num = num * skillMultiplier;
    	
    	return num;
    }
}