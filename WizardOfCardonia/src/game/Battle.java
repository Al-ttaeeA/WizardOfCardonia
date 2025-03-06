package game;

import data.*;
import card.*;
import item.*;
import enemy.*;
import java.util.*;

public class Battle {
	private static int currentMana, maxMana;
	
	private static double battleMult;
	
	public static double battleDifficulty;
	private static int battleGold;
	private static int battleXp;
	private static int battleCardCount;
	
	private static ArrayList<Card> tempDeck = new ArrayList<>();
	
	private static Enemy currentEnemy;
	
	private static void iniateTempDeck() {
		for(int i = 0; i < Main.deck.size(); i++) {
			tempDeck.add(Main.deck.get(i).copy());
		}
	}
	
	public static void Battle(){
		iniateTempDeck();
		
		currentMana = 3;
		maxMana = 3;
		
		battleMult = 1.00;
		
		switch(Main.location) {
		case 1:{
			battleDifficulty = Commands.getRandomDouble(1.00, 1.20);
			
			if(Commands.getRandomChance() < 0.80) battleCardCount = 1;
			else battleCardCount = 2;
			
			break;
		}
		case 2:{
			battleDifficulty = Commands.getRandomDouble(1.20, 2.00);
			
			if(Commands.getRandomChance() < 0.50) battleCardCount = 1;
			else if(Commands.getRandomChance() < 0.60) battleCardCount = 2;
			else battleCardCount = 3;
			
			break;
		}
		case 3:{
			battleDifficulty = Commands.getRandomDouble(2.00, 3.50);
			
			if(Commands.getRandomChance() < 0.50) battleCardCount = 2;
			else if(Commands.getRandomChance() < 0.25) battleCardCount = 3;
			else battleCardCount = 4;
			
			break;
		}
		}
		battleGold = (int) (battleDifficulty * 100);
		battleXp = (int) (battleGold / 2);
		
		currentEnemy = Data.getEnemy();
		currentEnemy.initialize();
		
		System.out.println("You stumble across a hostile " + currentEnemy.getName() + " in your adventure!");
		System.out.println("\nBattle No: " + Main.battleCount + " starts!");
		Commands.pressEnter();
		
		do {
			
		} while(true);
	}
	
	static void playerTurn() {
		
	}
	
	static void enemyTurn() {
		
	}
	
	static void enemIsDead() {
		Main.battleCount++;
		Main.gold += battleGold;
		Main.currentXp += battleXp;
		
		System.out.println("You");
	}
	
	static void levelUp() {
		int gainedSkillPoints;
		
		Main.currentXp -= Main.maxXp;
		
		Main.maxXp = (int) Math.pow(1.20, Main.battleCount - 1);
		
		Main.xpLevel += 1;
		Main.playMaxHP += 5;
		
		if(Commands.getRandomChance() < 0.4) gainedSkillPoints = 2;
		else if(Commands.getRandomChance() < 0.6) gainedSkillPoints = 3;
		else gainedSkillPoints = 4;
	}
	
	static void playIsDead() {
		
	}
}
