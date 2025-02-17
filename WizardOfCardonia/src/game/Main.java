package game;

import data.*;
import card.*;
import item.*;
import enemy.*;
import java.util.*;

public class Main {
	//Currently, these are all for testing
	public static int testInt;
	public static double testDouble;
	public static double testDiffMult;
	
	public static ArrayList<Card> deck = new ArrayList();
	public static ArrayList<Item> inventory = new ArrayList();
	
	public static String playName;
	public static int playMaxHP = 100;
	public static int playCurrentHP = 100;
	public static int gold;
	
	public static int xpLevel = 1;
	public static int currentXp = 0;
	public static int maxXp = 100;
	public static int skillpoints = 0;
	
	public static int intelligence = 1;
	public static int strength = 1;
	public static int arcana = 1;
	public static int corruptedness = 0;
	
	public static int location;
	
	public static void main(String[] args) {
		Commands.clearScreen();
		Data.initiateDeck();
		
		int mainMenuChoice;
		
		
		
		System.out.println("""
				********************************************************************************
				*                                                                              *
				*                     W I Z A R D   O F   C A R D O N I A                      *
				*                                                                              *
				********************************************************************************
				
				Welcome fellow wizard! The land of Cardonia awaits you!
				""");
		Commands.pressEnter();
		
		
		do {
			System.out.println("""
					********************************************************************************
					*                                  MAIN  MENU                                  *
					*****                                                                      *****
					
					What would you like to do?
					
					1. New game (WIP) 
					
					2. Load previous game (WIP)
					
					3. View Codex (Name could change)
					
					4. Credits (WIP)
					
					5. Exit (REALLY?)
					""");
			
			mainMenuChoice = Commands.inputInt(1, 5);
			
			switch(mainMenuChoice) {
			case 1:{
				Savegame.newGame();
				
				campsite();
				
				break;
			}
			
			case 2:{
				Savegame.loadGame();
				
				campsite();
				
				break;
			}
			
			case 3:{
				codex();
				break;
			}
			
			case 4:{
				credits();
				break;
			}
			
			case 5:{
				System.exit(0);
			}
			}
		} while(mainMenuChoice != 5); //Ends ONLY when the user exits
	} //End of main

	
	
	/******************
	 * codex
	 * This method allows the user to view all cards,
	 * items, and enemies
	 ******************/
	static void codex() {
		while(true) {
			System.out.println("""
					********************************************************************************
					*                                    CODEX                                     *
					*****                                                                      *****
					
					What would you like to view 
					
					1. View Cards 
					
					2. View Enemies
					
					3. View Items
					
					4. Exit
					""");
			int choice = Commands.inputInt(1, 4);
			
			switch(choice) {
			case 1:{
				Data.displayCards();
				break;
			}
			case 2:{
				Data.displayEnemies();
				break;
			}
			case 3:{
				Data.displayItems();
				break;
			}
			case 4:{
				return;
			}
			}
		}
	}
	
	
	
	/********************
	 * credits
	 * Prints the credits for the game (a lil bit narcissistic if u ask me)
	 ********************/
	static void credits() {
		System.out.println("Credits goes here");
		Commands.pressEnter();
	}
	
	
	
	/*********************
	 * campsite
	 * This method is basically the menu of the game
	 * Here the user can access their cards and stats
	 *********************/
	static void campsite() {
		int campChoice;
		int merchantCount = 0;
		
		do {
			System.out.println("""
					********************************************************************************
					*                                   CAMPSITE                                   *
					*****                                                                      *****
					""");
			
			if(skillpoints > 0) {
				System.out.println("   # You have " + skillpoints + " unspent skillpoints!\n");
			}
			
			System.out.println("""
					1. View Personal Stats
					""");
			System.out.println("2. View Deck (" + deck.size() + " Cards)");
			System.out.println("\n3. View inventory (" + inventory.size() + " Items)");		
					
			System.out.println("""
					
					4. Proceed
					
					5. Exit to main menu
					""");		
					
			campChoice = Commands.inputInt(1, 5);
			
			switch(campChoice) {
			case 1:{
				viewStats();
				break;
			}
			case 2:{
				viewDeck();
				break;
			}
			case 3:{
				viewInventory();
				break;
			}
			case 4:{
				if(merchantCount != 0 && (Commands.getRandomChance() < 0.33 || merchantCount == 3)) {
					merchantCount = 0;
					
					merchant();
					
					break;
				}
				
				merchantCount++;
				
				Battle.Battle();
				break;
			}
			}
		} while(campChoice != 5);
	}
	
	/**********************
	 * viewStats
	 * Lets the player view their stats
	 **********************/
	static void viewStats() {
		int choice;
		
		while(true) {
			System.out.println("""
					********************************************************************************
					*                                     STATS                                    *
					*****                                                                      *****
					""");
			System.out.println(playName + "\n");
			System.out.println("Health: " + playCurrentHP + " HP/ " + playMaxHP + " HP\n");
			System.out.println("Experience (Level " + xpLevel + "): " + currentXp + " XP/ " + maxXp + " XP\n");
			if(skillpoints > 0) {
				System.out.println("You have " + skillpoints + " unspent skillpoint(s)!\n");
			}
			System.out.println("Strength: " + strength);
			System.out.println("\nIntelligence: " + intelligence);
			System.out.println("\nArcana: " + arcana);
			System.out.println("\nCorruptedness: " + corruptedness);
			System.out.println("""
					\n********************************************************************************
					What would you like to do?
					
					1. Level up
					
					2. Return to campsite
					""");
			choice = Commands.inputInt(1, 2);
			
			if(choice == 2) {
				return;
			}
			
			if(skillpoints == 0) {
				System.out.println("You do not have any skillpoint to level up with!");
				Commands.pressEnter();
				continue;
			}
			
			levelUp();
		}
	}
	
	/********************
	 * levelUp
	 * Lets the player level up their skills
	 ********************/
	static void levelUp() {
		int choice;
		
		while(true) {
			System.out.println("What would you like to level up?");
			System.out.println("\n1. Strength: " + strength);
			System.out.println("\n2. Intelligence: " + intelligence);
			System.out.println("\n3. Arcana: " + arcana + "  (Corruptedness: " + corruptedness + ")");
			choice = Commands.inputInt(1, 3);
			
			switch(choice) {
			case 1:{
				strength += 1;
				skillpoints -= 1;
				System.out.println("You level up you strength to " + strength);
				System.out.println("\nYou now have " + skillpoints + " skillpoints remaining!");
				Commands.pressEnter();
				break;
			}
			case 2:{
				intelligence += 1;
				skillpoints--;
				System.out.println("You level up you intelligence to " + intelligence);
				System.out.println("\nYou now have " + skillpoints + " skillpoints remaining!");
				Commands.pressEnter();
				break;
			}
			case 3:{
				arcana += 1;
				skillpoints--;
				System.out.println("You level up you arcana to " + arcana);
				System.out.println("\nYou now have " + skillpoints + " skillpoints remaining!");
				Commands.pressEnter();
				break;
			}
			}
			
			if(skillpoints == 0) {
				return;
			}
		}
	}
	
	/*********************
	 * viewDeck
	 * Allows the player to view their deck and remove cards if they wish
	 *********************/
	static void viewDeck() {
		int choice;
		int discardChoice;
		int pageSize = 10;
		int currentPage = 0; 
		int totalPages;
		
		while(true) {
			totalPages = (int) Math.ceil((double) (deck.size()) / pageSize);
			
			System.out.println("""
					********************************************************************************
					*                                     DECK                                     *
					*****                                                                      *****
					""");
			
			int start = currentPage * pageSize;
			int end = Math.min(start + pageSize, deck.size());
			
			for(int i = start; i < end; i++) {
				System.out.println((i+1) + ". " + deck.get(i));
			}
			
			System.out.println("\nEnter -1 for next page | -2 for previous page | Card number to discard | Or 0 to exit");
			choice = Commands.inputInt(-2, end);
			
			if(choice == -2) {
				if(currentPage == 0) {
        			System.out.println("This is the first page, cannot go to a previous page!");
        			Commands.pressEnter();
        			continue;
        		}
        		
        		currentPage--;
			}
			else if(choice == -1) {
				if(currentPage == totalPages-1) {
        			System.out.println("Page limit reached, invalid input!");
        			Commands.pressEnter();
        			continue;
        		}
        		
        		currentPage++;
			}
			else if(choice == 0) {
				return;
			}
			else {
				if(choice <= start) {
					System.out.println("Invalid input, please try again!");
					Commands.pressEnter();
					continue;
				}
				
				System.out.println(deck.get(choice-1));
				System.out.println("""
						********************************************************************************
						What would you like to do?
						
						1. Return
						
						2. Remove Card
						""");
				discardChoice = Commands.inputInt(1, 2);
				
				if(discardChoice == 1) continue;
				
				if(deck.size() <= 10) {
					System.out.println("Your deck is too small! You need to have a minimum of 10 cards at all times!");
					System.out.println("\nYou cannot remove " + deck.get(choice-1).getName() + "!");
					Commands.pressEnter();
					continue;
				}
				
				System.out.println(deck.get(choice-1).getName() + " has been removed from your deck!");
				Commands.pressEnter();
				
				deck.remove(choice-1);
			}
		}
	}
	
	/**************************
	 * viewInventory
	 * Allows the player to view all the items in their inventory
	 **************************/
	static void viewInventory() {
		int choice;
        
        final int PAGE_SIZE = 10;
        int currentPage = 0;
        int totalPages = (int) Math.ceil((double) (inventory.size()) / PAGE_SIZE);
        
        while(true) {
        	System.out.println("********************************************************************************");
            System.out.println("*                                  INVENTORY                                   *");
            System.out.println("*****                                                                      *****\n");
            
            int start = currentPage * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, inventory.size());

            if(inventory.size() == 0) {
            	System.out.println("\nYour inventory is currently empty!");
            	Commands.pressEnter();
            	return;
            }
            
            for (int i = start; i < end; i++) {
                System.out.println((i + 1) + ". " + inventory.get(i));
            }
            
            System.out.println("\nEnter 1 for next page | 2 for previous page | 3 To exit");
            choice = Commands.inputInt(1, 3);
            
            switch(choice) {
        	case 1:{
        		if(currentPage == totalPages-1) {
        			System.out.println("Page limit reached, invalid input!");
        			Commands.pressEnter();
        			break;
        		}
        		
        		currentPage++;
        		break;
        	}
        	case 2:{
        		if(currentPage == 0) {
        			System.out.println("This is the first page, cannot go to a previous page!");
        			Commands.pressEnter();
        			break;
        		}
        		
        		currentPage--;
        		break;
        	}
        	case 3:{
        		return;
        	}
            }
        }
	}
	
	static void merchant() {
		System.out.println("Merchant runs");
	}
}

