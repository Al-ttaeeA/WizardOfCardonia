package game;

import data.*;
import card.*;
import item.*;
import enemy.*;
import java.util.*;

public class Main {
	public static int testInt;
	public static double testDouble;
	public static int testIntelligence;
	public static int testStrength;
	public static int testArcana;
	public static int testCorruptness;
	
	public static int location;
	
	public static void main(String[] args) {
		Commands.clearScreen();
		
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
				break;
			}
			
			case 2:{
				break;
			}
			
			case 3:{
				codex();
				break;
			}
			
			case 4:{
				break;
			}
			
			case 5:{
				System.exit(0);
			}
			}
		} while(mainMenuChoice != 1 && mainMenuChoice != 2);
		
		
	}
	
	
	
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
}

