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
    
    
    
    /**************************************
     * getRandomInt
     * Gets a random integer between 1 and num inclusive of both
     * @param num - Maximum integer
     * @return - returns a random int
     **************************************/
    public static int getRandomInt(int num) {
    	Random random = new Random();
    	
    	return random.nextInt(num) + 1;
    }
    
    
    
    /*************************************
     * getRandomChance
     * @return - returns a random chance value between 0 and 1
     *************************************/
    public static double getRandomChance() {
    	Random random = new Random();
    	
    	return random.nextDouble();
    }
    
    
    
    /*********************************************************
     * skillMultiplier
     * This is used to multiply a value of a card by the respective skill
     * @param num - the value that is to be multiplied
     * @param type - the type of the card
     * @return - returns the multiplier value
     *********************************************************/
    public static int skillMultiplier(int num, card.Type type) {
    	double skillMultiplier;
    	double permMult;
    	int skills;
    	
    	if(type == card.Type.PHYSICAL) {
    		skills = Main.strength - 1 - Main.corruptedness + Main.arcana;
    		permMult = Main.permStrMult;
    	}
    	else if(type == card.Type.MAGICAL) {
    		skills = Main.intelligence - 1 - Main.corruptedness + Main.arcana;
    		permMult = Main.permIntMult;
    	}
    	else {
    		skills = Main.corruptedness - Main.arcana;
    		permMult = Main.permCorMult;
    	}
    	
    	if(skills < 0) skills = 0;
    	
    	skillMultiplier = 1 + (skills / 10.0);
    	
    	skillMultiplier *= Main.permMult;
    	
    	skillMultiplier *= permMult;

		num = (int) Math.ceil(num * skillMultiplier);
    	
    	return num;
    }
    
    public static double skillMultiplier(double num, card.Type type) {
    	double skillMultiplier;
    	double permMult;
    	int skills;
    	
    	if(type == card.Type.PHYSICAL) {
    		skills = Main.strength - 1 - Main.corruptedness + Main.arcana;
    		permMult = Main.permStrMult;
    	}
    	else if(type == card.Type.MAGICAL) {
    		skills = Main.intelligence - 1 - Main.corruptedness + Main.arcana;
    		permMult = Main.permIntMult;
    	}
    	else {
    		skills = Main.corruptedness - Main.arcana;
    		permMult = Main.permCorMult;
    	}
    	
    	if(skills < 0) skills = 0;
    	
    	skillMultiplier = 1 + (skills / 10.0);
    	
    	skillMultiplier *= Main.permMult;
    	
    	skillMultiplier *= permMult;

		num = num * skillMultiplier;
    	
    	return num;
    }
}