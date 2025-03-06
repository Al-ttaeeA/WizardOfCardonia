package game;

import data.*;

public class Savegame {
	public static void newGame() {
		Main.playMaxHP = 100;
		Main.playCurrentHP = 100;
		Main.gold = 1000; //Change
		Main.battleCount = 1;
		Main.playIsDead = false;
		
		Main.xpLevel = 1;
		Main.currentXp = 0;
		Main.maxXp = 50;
		Main.skillpoints = 0;
		
		Main.intelligence = 1;
		Main.strength = 1;
		Main.arcana = 1;
		Main.corruptedness = 0;
		
		Main.permMult = 1.00;
		Main.permIntMult = 1.00;
		Main.permStrMult = 1.00;
		Main.permCorMult = 1.00;
		Main.permShopSale = 1.00;
		
		Main.location = 1;
		
		Data.initiateDeck();
		Data.initiateArtifactList();
		
		
		System.out.println("New Game started!");
		Commands.pressEnter();
		
		System.out.println("Please Enter your username: ");
		Main.playName = Commands.inputString();
		
		story();
	}
	
	public static void loadGame() {
		System.out.println("This is a placeholder currently and there is no previous game sorry!");
		Commands.pressEnter();
		
		newGame();
	}
	
	public static void story() {
		System.out.println("Story goes here");
		Commands.pressEnter();
	}
}
