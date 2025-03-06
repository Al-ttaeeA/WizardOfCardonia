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
			playerTurn();
			
			if(currentEnemy.getHealth() <= 0) {
				enemIsDead();
				return;
			}
			
			enemyTurn();
			
			if(Main.playCurrentHP <= 0) {
				playIsDead();
				return;
			}
		} while(true);
	}
	
	static void playerTurn() {
		
	}
	
	static void enemyTurn() {
		
	}
	
	static void enemIsDead() {
		Card gainedCard;
		int keepChoice;
		
		Main.battleCount++;
		Main.gold += battleGold;
		Main.currentXp += battleXp;
		
		System.out.println("You have SUCCESSFULLY defeated " + currentEnemy.getName() + " and gained: ");
		System.out.println("-" + battleGold + " Gold for a total of " + Main.gold + " Gold!");
		System.out.println("-" + battleXp + " XP, you now have " + Main.currentXp + " XP/ " + Main.maxXp + " XP to level up!");
		System.out.println("-" + battleCardCount + " brand new cards that you can add to your deck!");
		Commands.pressEnter();
		
		while(Main.currentXp >= Main.maxXp) {
			levelUp();
		}
		
		for(int i = 0; i < battleCardCount; i++) {
			gainedCard = Data.getRandomCard();
			
			System.out.println("You have gained a card from this battle:");
			System.out.println("\n" + gainedCard);
			System.out.println("""
					********************************************************************************
					What would you like to do?
					
					1. Keep
					
					2. Discard 
					""");
			keepChoice = Commands.inputInt(1, 2);
			
			if(keepChoice == 2) {
				System.out.println("You choose to discard " + gainedCard.getName());
				Commands.pressEnter();
				continue;
			}
			
			Main.deck.add(gainedCard);
			
			System.out.println("You add " + gainedCard.getName() + " to your deck!");
			Commands.pressEnter();
		}
		
		System.out.println("You return back to your campsite after a successful battle!");
		Commands.pressEnter();
	}
	
	static void levelUp() {
		int gainedSkillPoints;
		
		Main.currentXp -= Main.maxXp;
		
		Main.maxXp = (int) Math.pow(1.20, Main.battleCount - 1);
		
		Main.xpLevel += 1;
		Main.playMaxHP += 5;
		
		if(Commands.getRandomChance() < 0.4) gainedSkillPoints = 2;
		else if(Commands.getRandomChance() < 0.8) gainedSkillPoints = 3;
		else gainedSkillPoints = 4;
		
		Main.skillpoints += gainedSkillPoints;
		
		System.out.println("CONGRATULATIONS! You have leveled up to level " + Main.xpLevel + "!");
		System.out.println("\nYou now have " + Main.currentXp + " XP/ " + Main.maxXp + " XP for your next level up!");
		System.out.println("\nYou have gained 5 HP to your maximum HP for a total of " + Main.playMaxHP + " HP!");
		System.out.println("\nYou have also gained " + gainedSkillPoints + " skillpoints, You now have " + Main.skillpoints + " skillpoints to spend!");
		Commands.pressEnter();
	}
	
	static void playIsDead() {
		
	}
}
