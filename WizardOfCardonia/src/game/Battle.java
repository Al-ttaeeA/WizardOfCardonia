/***********************************************************************
 * Wizard of Cardonia
 * Copyright (C) 2025 A2Z Studios
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 ***********************************************************************/

package game;

import data.*;
import card.*;
import enemy.*;
import java.util.*;

public class Battle {
	public static int currentMana, maxMana;
	
	public static double battleMult = 1;
	public static double attackMult = 1;
	public static double blockMult = 1;
	public static double healMult = 1;
	public static double enemyDamageMult = 1;
	
	public static double battleDifficulty;
	private static int battleGold;
	private static int battleXp;
	private static int battleCardCount;
	
	public static int currentBlock = 0;
	
	public static Card currentCard;
	
	private static ArrayList<Card> tempDeck = new ArrayList<>();
	private static ArrayList<Card> hand = new ArrayList<>();
	
	public static Enemy currentEnemy = Data.getEnemy();
	
	private static void initiateTempDeck() {
		for(int i = 0; i < Main.deck.size(); i++) {
			tempDeck.add(Main.deck.get(i).copy());
		}
	}
	
	public static boolean draw() {
		if(tempDeck.size() == 0) {
			System.out.println("You have no more cards to draw!");
			Commands.pressEnter();
			
			return false;
		}
		
		int random = Commands.getRandomInt(tempDeck.size()-1);
		hand.add(tempDeck.get(random));
		tempDeck.remove(random);
		
		return true;
	}
	
	public static void Battle(){
		initiateTempDeck();
		
		maxMana = Main.permMaxMana;
		currentMana = maxMana;
		
		Main.battleCount++;
		
		startBattle();
		
		do {
			playerTurn();
			
			if(currentEnemy.getHealth() <= 0) {
				enemIsDead();
				
				currentBlock = 0;
				
				battleMult = 1;
				attackMult = 1;
				blockMult = 1;
				healMult = 1;
				enemyDamageMult = 1;
				
				tempDeck.clear();
				hand.clear();
				
				return;
			}
			
			enemyTurn();
			
			if(Main.playCurrentHP <= 0) {
				playIsDead();
				
				currentBlock = 0;
				
				battleMult = 1;
				attackMult = 1;
				blockMult = 1;
				healMult = 1;
				enemyDamageMult = 1;
				
				tempDeck.clear();
				hand.clear();
				
				return;
			}
		} while(true);
	}
	
	static void startBattle() {
		switch(Main.location) {
		case 1:{
			battleDifficulty = Commands.getRandomDouble(1.00, 1.50);
			
			if(Commands.getRandomChance() < 0.90) battleCardCount = 1;
			else battleCardCount = 2;
			
			break;
		}
		case 2:{
			battleDifficulty = Commands.getRandomDouble(1.50, 3.00);
			
			if(Commands.getRandomChance() < 0.60) battleCardCount = 1;
			else if(Commands.getRandomChance() < 0.75) battleCardCount = 2;
			else battleCardCount = 3;
			
			break;
		}
		case 3:{
			battleDifficulty = Commands.getRandomDouble(3.00, 5.00);
			
			if(Commands.getRandomChance() < 0.60) battleCardCount = 2;
			else if(Commands.getRandomChance() < 0.75) battleCardCount = 3;
			else battleCardCount = 4;
			
			break;
		}
		}
		
		
		if(Main.battleCount % 5 == 0 && Main.battleCount <= 15) {
			int boss = Main.battleCount / 5;
			
			currentEnemy = Data.bosses[boss - 1];
			currentEnemy.initialize();
			
			battleCardCount = Commands.getRandomInt(3) + 1;
			
			battleGold = 50 * Main.battleCount;
			battleXp = (int) (battleGold * 0.50);
			
			switch(boss) {
			case 1:{
				Commands.typeWriterEffect("""
						You see a grotesque, bear-like creature-dark, bloody, and hideous...

						It snarls as it senses your presence-and charges without hesitation!

						Up close, the truth hits you: this is no beast.

						It's Plensor-the corrupted mage!

						Prepare for battle!
						""");
				Commands.pressEnter();
				
				return;
			}
			case 2:{
				Commands.typeWriterEffect("""
						Your hands begin to go numb... your vision blurs...

						Around you, the grass shrivels, the bushes wilt, and the flowers decay...

						Then-out of the dying earth-it appears!

						A wolf-like figure of pure corruption, draining life from everything nearby.

						Mejashi has revealed himself!

						His presence alone chills your soul...
						
						Prepare for battle!
						""");
				Commands.pressEnter();
				
				return;
			}
			case 3:{
				Commands.typeWriterEffect("""
						The land trembles as life itself begins to fade...

						Trees recoil, hiding their branches. Birds flee in desperation...

						A heavy, suffocating presence looms nearby...

						You don't know where it came from-or how-but you feel it...

						Suddenly, the world around you twists into a void of shadows...

						The ground cracks open. Corrupt creatures crawl from the abyss...

						Then-something watches you...
						""");
				Commands.pressEnter();

				Commands.typeWriterEffect("""
						From within the darkness, two glowing red eyes pierce through...

						Your heart sinks...

						This is no mere beast...

						This is Cruden-the first to fall to the corruption...

						...and the strongest of them all!

						Prepare for the hardest battle of your life!
						""");
				Commands.pressEnter();
				
				return;
			}
			}
		}
		
		
		battleGold = (int) (battleDifficulty * 100);
		battleXp = (int) (battleGold * 0.50);
		
		if(Main.battleCount == 1) {
			battleDifficulty = 0.40;
			battleCardCount = 1;
			battleGold = Commands.getRandomInt(50) + 50;
			battleXp = (int) (battleGold * 0.7);
		}
		else if(Main.battleCount <= 4) {
			battleDifficulty *= 0.70;
			battleGold = (int) (battleGold * 0.80);
			battleXp = (int) (battleXp * 0.80);
		}
		else if(Main.battleCount <= 9) {
			battleDifficulty *= 1.20;
		}
		else if(Main.battleCount <= 14) {
			battleDifficulty *= 2;
		}
		else if(Main.battleCount > 15) {
			battleDifficulty *= Math.pow(1.10, Main.battleCount - 15);
			battleGold *= Math.pow(1.02, Main.battleCount - 15);
			battleXp *= Math.pow(1.02, Main.battleCount - 15);
		}
		
		currentEnemy = Data.getEnemy();
		currentEnemy.initialize();
		
		Commands.typeWriterEffect("You stumble across a hostile " + currentEnemy.getName() + " in your adventure!" + "\n\nBattle No: " + Main.battleCount + " starts!");
		Commands.pressEnter();
	}
	
	static void playerTurn() {
		int choice;
		int innerChoice;
		
		currentBlock = 0;
		currentMana = maxMana;
		
		for(int i = 0; i < Main.permHand; i++) {
			if(!draw()) {
				break;
			}
		}
		
		while(true) {
			if(currentEnemy.getHealth() <= 0) return;
			
			battleBanner();
			System.out.println("""
					
					 What would you like to do?
					
					1. View Hand and/or play a card
					
					2. View inventory and/or use an item
					
					3. End turn
					""");
			
			choice = Commands.inputInt(1, 3);
			
			switch(choice) {
			case 1:{
				if(hand.size() == 0) {
					System.out.println("Your hand is empty!");
					Commands.pressEnter();
					break;
				}
				
				System.out.println("You have " + hand.size() + " Cards in your hand: \n");
				
				for(int i = 1; i < hand.size()+1; i++) {
					System.out.println(i + ". " + hand.get(i-1));
				}
				
				System.out.println("********************************************************************************");
				System.out.println("Enter the number of the card you wish to play or 0 to exit");
				innerChoice = Commands.inputInt(0, hand.size());
				
				if(innerChoice == 0) {
					break;
				}
				
				
				currentCard = hand.get(innerChoice - 1).copy();
				
				if(currentCard.use()) {
					tempDeck.add(hand.get(innerChoice - 1).copy());
					hand.remove(innerChoice - 1);
					Commands.pressEnter();
				}
				
				break;
			}
			case 2:{
				inventory();
				break;
			}
			case 3:{
				System.out.println("""
						Are you sure you want to end your turn?
						
						1. End my turn
						
						2. DONT end my turn
						""");
				innerChoice = Commands.inputInt(1, 2);
				
				if(innerChoice == 2) break;
				
				int num = hand.size();
				
				for(int i = 0; i < num; i++) {
					tempDeck.add(hand.get(0).copy());
					hand.remove(0);
				}
				
				return;
			}
			}
		}
	}
	
	static void inventory() {
		if(Main.inventory.size() == 0) {
			System.out.println("You inventory is currently empty!");
			Commands.pressEnter();
			return;
		}
		
		int choice;
		int useChoice;
		int pageSize = 5;
		int currentPage = 0; 
		int totalPages;
		
		while(true) {
			totalPages = (int) Math.ceil((double) (Main.inventory.size()) / pageSize);
			
			System.out.println("""
				     Inventory
					*****************
					""");
			
			int start = currentPage * pageSize;
			int end = Math.min(start + pageSize, Main.inventory.size());
			
			for(int i = start; i < end; i++) {
				System.out.println((i+1) + ". " + Main.inventory.get(i));
			}
			
			System.out.println("\nEnter -1 for next page | -2 for previous page | Item number to use | Or 0 to exit");
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
				
				System.out.println(Main.inventory.get(choice-1));
				System.out.println("""
						********************************************************************************
						What would you like to do?
						
						1. Use Item
						
						2. Return
						""");
				useChoice = Commands.inputInt(1, 2);
				
				if(useChoice == 2) continue;
				
				System.out.println("You use " + Main.inventory.get(choice-1).getName() + "!\n");
				Main.inventory.get(choice-1).copy().use();
				Commands.pressEnter();
				
				Main.inventory.remove(choice-1);
				
				return;
			}
		}
	}
	
	static void enemyTurn() {
		battleBanner();
		
		System.out.println("It is now the enemy, " + currentEnemy.getName() + "'s turn!\n");
		
		currentEnemy.action();
		Commands.pressEnter();
	}
	
	static void enemIsDead() {
		Card gainedCard;
		int keepChoice;
		
		Main.gold += battleGold;
		Main.currentXp += battleXp;
		
		if(Main.battleCount % 5 == 0 && Main.battleCount <= 15) {
			switch(Main.battleCount / 5) {
			case 1:{
				Commands.typeWriterEffect("""
						Plensor stumbles, his monstrous form cracking and breaking apart...

						Dark blood drips from the wounds of his corrupted shell...

						For a moment... he looks human again.

						A faint light flickers in his eyes-regret, maybe, or relief.

						Then, with a final gasp, the corrupted mage collapses.

						The ground stills.

						One of the founders has fallen...
						""");
				Commands.pressEnter();
				
				break;
			}
			case 2:{
				Commands.typeWriterEffect("""
						The withered grass begins to regrow...

						The air clears...

						Mejashi lets out a final, haunting howl as his body dissolves into ash...

						Where corruption once drained life, nature now breathes again.

						His shadow vanishes, carried away by the wind.

						One more wound in Cardonia begins to heal...
						""");
				Commands.pressEnter();
				
				break;
			}
			case 3:{
				Commands.typeWriterEffect("""
						The earth groans as the void shrinks inward...

						Cruden's monstrous form convulses-twisting, unraveling.

						The red eyes fade... but not with fear...

						With fury...

						A deafening scream tears through the darkness, then silence...

						The abyss crumbles.

						And in its place... nothing...

						The greatest corruption is gone-but its echoes will never truly fade...
						""");
				Commands.pressEnter();
				
			    System.out.println("""
			    		You have defeated the root of chaos and corruptness!
			    		
			    		However, humankind is far from safe...
			    		
			    		Congratulations! You have finished Wizard Of Cardonia!
			    		********************************************************************************
			    		
			    		Do you wish to continue your adventure infinitely or end your journey here?
			    		
			    		1. Continue infinitely (The story is over, there will be no more bosses)
			    		
			    		2. End the journey
			    		""");
			    int stayChoice = Commands.inputInt(1, 2);
			    
			    if(stayChoice == 2) {
			    	Main.playIsDead = true;
			    	return;
			    }
				
				break;
			}
			}
		}
		
		System.out.println("You have SUCCESSFULLY defeated " + currentEnemy.getName() + " and gained: ");
		System.out.println("\n- " + battleGold + " Gold for a total of " + Main.gold + " Gold!");
		System.out.println("\n- " + battleXp + " XP, you now have " + Main.currentXp + " XP/ " + Main.maxXp + " XP to level up!");
		System.out.println("\n- " + battleCardCount + " brand new cards that you can add to your deck!");
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
	}
	
	static void levelUp() {
		int gainedSkillPoints;
		
		Main.currentXp -= Main.maxXp;
		
		Main.maxXp = (int) (50 * Math.pow(1.20, Main.xpLevel));
		
		Main.xpLevel += 1;
		Main.playMaxHP += 5;
		
		if(Commands.getRandomChance() < 0.4) gainedSkillPoints = 2;
		else if(Commands.getRandomChance() < 0.9) gainedSkillPoints = 3;
		else gainedSkillPoints = 4;
		
		Main.skillpoints += gainedSkillPoints;
		
		System.out.println("CONGRATULATIONS! You have leveled up to level " + Main.xpLevel + "!");
		System.out.println("\nYou now have " + Main.currentXp + " XP/ " + Main.maxXp + " XP for your next level up!");
		System.out.println("\nYou have gained 5 HP to your maximum HP for a total of " + Main.playMaxHP + " HP!");
		System.out.println("\nYou have also gained " + gainedSkillPoints + " skillpoints, You now have " + Main.skillpoints + " skillpoints to spend!");
		Commands.pressEnter();
	}
	
	static void playIsDead() {
		Main.playIsDead = true;
		
		System.out.println("""
				********************************************************************************
				*                                                                              *
				*                              G A M E   O V E R                               *
				*                                                                              *
				*                                                                              *
				*                          Y O U   H A V E   D I E D                           *
				*                                                                              *
				********************************************************************************
				""");
		Commands.pressEnter();
	}
	
	static void battleBanner() {
		String playHealth = "Health: " + Main.playCurrentHP + " HP / " + Main.playMaxHP + " HP";
		String enemyHealth = "Health: " + currentEnemy.getHealth() + " HP / " + currentEnemy.getMaxHealth() + " HP";
		String playBlock = "Block: " + currentBlock + " Damage";
		String enemyBlock = "Block: " + currentEnemy.getBlock() + " Damage";
		String playMana = "Mana: " + currentMana + " MP / " + maxMana + " MP";
		String enemyDamage = "Damage: " + currentEnemy.getMinDamage() + " - " + currentEnemy.getMaxDamage() + " Damage";
		String playCards = "Cards in hand: " + hand.size();
		String enemyEffects = currentEnemy.getEffects();
		
		
		System.out.println("********************************************************************************");
		System.out.println(String.format("*   %-36s*   %-35s*", Main.playName, currentEnemy.getName()));
		System.out.println("*                                       *                                      *");
		System.out.println(String.format("* %-38s* %-37s*", playHealth, enemyHealth));
		System.out.println("*                                       *                                      *");
		System.out.println(String.format("* %-38s* %-37s*", playBlock, enemyBlock));
		System.out.println("*                                       *                                      *");
		System.out.println(String.format("* %-38s* %-37s*", playMana, enemyDamage));
		System.out.println("*                                       *                                      *");
		System.out.println(String.format("* %-38s* %-37s*", playCards, enemyEffects));
		System.out.println("********************************************************************************");
	}
}
