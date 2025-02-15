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
	
	public static String playName;
	public static int playMaxHealth = 100;
	public static int playCurrentHealth = 100;
	
	public static int intelligence = 1;
	public static int strength = 1;
	public static int arcana = 1;
	public static int corruptness = 1;
	
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
				
				Welcome fellow wizard! You have a great adventure ahead of you!
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
		
		do {
			System.out.println("Currently in campsite\n");
			System.out.println("1 to view deck\n");
			System.out.println("2-4 to do nothing\n");
			System.out.println("5 to exit to main menu");
			campChoice = Commands.inputInt(1, 5);
			
			switch(campChoice) {
			case 1:{
				viewDeck();
				break;
			}
			}
		} while(campChoice != 5);
	}
	
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
}

