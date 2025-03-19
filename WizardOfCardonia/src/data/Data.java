/***********************************************************************
 * Wizard of Cardonia
 * Copyright (C) 2025 Dr4Go5 Developers
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

package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import card.*;
import enemy.*;
import item.*;
import artifact.*;
import game.*;

public class Data {
	/********************************
	 * displayCards
	 * This method displays all cards in pages of 10 cards
	 ********************************/
	public static void displayCards() {
		int choice;
		
		List<Card> cardList = new ArrayList<>();
        for (Card[] rarityGroup : cards) {
            Collections.addAll(cardList, rarityGroup);
        }
        
        final int PAGE_SIZE = 10;
        int currentPage = 0;
        int totalPages = (int) Math.ceil((double) (cardList.size()) / PAGE_SIZE);
        
        while(true) {
        	System.out.println("********************************************************************************");
            System.out.println("*                                  CARD LIST                                   *");
            System.out.println("*****                                                                      *****\n");
            
            int start = currentPage * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, cardList.size());

            for (int i = start; i < end; i++) {
                System.out.println((i + 1) + ". " + cardList.get(i));
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
	
	/*********************************
	 * getRandomCard
	 * @return - returns a random card based on rarity
	 *********************************/
	public static Card getRandomCard() {
		double chance = Commands.getRandomChance();
		
		if(chance < 0.49) {
			return cards[0][Commands.getRandomInt(cards[0].length) - 1].copy();
		}
		else if(chance < 0.79) {
			return cards[1][Commands.getRandomInt(cards[1].length) - 1].copy();
		}
		else if(chance < 0.94) {
			return cards[2][Commands.getRandomInt(cards[2].length) - 1].copy();
		}
		else if(chance < 0.99) {
			return cards[3][Commands.getRandomInt(cards[3].length) - 1].copy();
		}
		else {
			return cards[4][Commands.getRandomInt(cards[4].length) - 1].copy();
		}
	}
	
	/********************************
	 * initiateDeck
	 * This method runs at the start of the game and inializes the deck with a damage card
	 * of each type and 7 random cards
	 ********************************/
	public static void initiateDeck() {
		Main.deck.add(cards[0][0].copy());
		Main.deck.add(cards[0][2].copy());
		Main.deck.add(cards[0][3].copy());
		
		for(int i = 0; i < 7; i++) {
			Main.deck.add(getRandomCard());
		}
	}
	
	/*************************
	 * The array of all cards, each row is a different rarity
	 *************************/
	static Card[][] cards = {{
		new DamageCard("Stab", 						Rarity.COMMON, 1, Type.PHYSICAL, 6),
		new DamageCard("Strike", 					Rarity.COMMON, 2, Type.PHYSICAL, 10),			
		new DamageCard("Fireball", 					Rarity.COMMON, 1, Type.MAGICAL, 7),
		new DamageCard("Arcane Bolt",				Rarity.COMMON, 2, Type.MAGICAL, 11),	
		new DamageCard("Dark Shroud", 				Rarity.COMMON, 1, Type.CORRUPT, 8),
		new DamageCard("Dark Bolt", 				Rarity.COMMON, 2, Type.CORRUPT, 12),
		new BlockCard("Shield", 					Rarity.COMMON, 1, Type.PHYSICAL, 6),
		new BlockCard("Iron Guard", 				Rarity.COMMON, 2, Type.PHYSICAL, 12),
		new BlockCard("Spectral Shield", 			Rarity.COMMON, 1, Type.MAGICAL, 5),
		new BlockCard("Arcane Barrier", 			Rarity.COMMON, 2, Type.MAGICAL, 10),
		new BlockCard("Shadow Seal", 				Rarity.COMMON, 1, Type.CORRUPT, 9),
		new BlockCard("Dark Pact Shield",			Rarity.COMMON, 2, Type.CORRUPT, 14),
		new HealCard("Vital surge",					Rarity.COMMON, 1, Type.PHYSICAL, 6),
		new HealCard("Healing Wave", 				Rarity.COMMON, 1, Type.MAGICAL, 7),
		new HealCard("Blood Pact",					Rarity.COMMON, 1, Type.CORRUPT, 8)
	},{
		new DamageBlockCard("Slash and Bash", 		Rarity.UNCOMMON, 2, Type.PHYSICAL, 7, 6),
		new DamageBlockCard("Runic Counterblast", 	Rarity.UNCOMMON, 2, Type.MAGICAL, 8, 6),
		new DamageBlockCard("Cursed Reflection", 	Rarity.UNCOMMON, 2, Type.CORRUPT, 8, 8),
		new BlockHealCard("Fortified Recovery",		Rarity.UNCOMMON, 1, Type.PHYSICAL, 6, 6),
		new BlockHealCard("Runic Restoration",		Rarity.UNCOMMON, 1, Type.MAGICAL, 6, 7),
		new BlockHealCard("Dark Resurgence", 		Rarity.UNCOMMON, 1, Type.CORRUPT, 8, 8),
		new StatusCard("Leg Smash",					Rarity.UNCOMMON, 2, Type.PHYSICAL, 0.40),
		new StatusCard("Ignite",					Rarity.UNCOMMON, 2, Type.MAGICAL, 0.50),
		new StatusCard("Poisoned Dart", 			Rarity.UNCOMMON, 2, Type.CORRUPT, 0.50)
	},{
		new HealCard("Enduring Spirit", 			Rarity.RARE, 1, Type.PHYSICAL, 9),
		new HealCard("Radiant Rejuvenation",		Rarity.RARE, 1, Type.MAGICAL, 10),
		new HealCard("Dark Regeneration",			Rarity.RARE, 1, Type.CORRUPT, 12),
		new ManaCard("Battle Fury", 				Rarity.RARE, 0, Type.PHYSICAL, 1),
		new ManaCard("Mana Replenish", 				Rarity.RARE, 0, Type.MAGICAL, 1),
		new ManaCard("Dark Ritual",					Rarity.RARE, 0, Type.CORRUPT, 1),
		new StatusCard("Head Bash",					Rarity.RARE, 2, Type.PHYSICAL, 0.75),
		new StatusCard("Fire Breath",				Rarity.RARE, 2, Type.MAGICAL, 0.80),
		new StatusCard("Toxic Mist", 				Rarity.RARE, 2, Type.CORRUPT, 0.80)
	},{
		new ManaCard("Warrior's Focus",				Rarity.EPIC, 0, Type.PHYSICAL, 2),
		new ManaCard("Celestial Flow", 				Rarity.EPIC, 0, Type.MAGICAL, 2),
		new ManaCard("Blood Offering",				Rarity.EPIC, 1, Type.CORRUPT, 3),
		new WeaknessCard("Weakening Blow", 			Rarity.EPIC, 1, Type.PHYSICAL, 0.90),
		new WeaknessCard("Arcane Disruption",		Rarity.EPIC, 1, Type.MAGICAL, 0.88),
		new WeaknessCard("Curse of Frality",		Rarity.EPIC, 1, Type.CORRUPT, 0.85),
		new StatusCard("Fracture Slash", 			Rarity.EPIC, 2, Type.PHYSICAL, 1),
		new StatusCard("Dragon’s Breath", 			Rarity.EPIC, 2, Type.MAGICAL, 1),
		new StatusCard("Necrotic Toxin", 			Rarity.EPIC, 2, Type.CORRUPT, 1)
		
	},{
		new WeaknessCard("Bone Breaker", 			Rarity.LEGENDARY, 1, Type.PHYSICAL, 0.80),
		new WeaknessCard("Blinding Spell", 			Rarity.LEGENDARY, 1, Type.MAGICAL, 0.78),
		new WeaknessCard("Soul Rot", 				Rarity.LEGENDARY, 1, Type.CORRUPT, 0.75),
		new StatusCard("Executioner’s Wrath", 		Rarity.LEGENDARY, 1, Type.PHYSICAL, 1),
		new StatusCard("Phoenix’s Wrath", 			Rarity.LEGENDARY, 1, Type.MAGICAL, 1),
		new StatusCard("Serpent's Wrath", 			Rarity.LEGENDARY, 1, Type.CORRUPT, 1)
	}
	};
	
	
	
	/**********************************
	 * displayEnemies
	 * This method displays all enemies in pages of 5 enemies
	 **********************************/
	public static void displayEnemies() {
		int choice;
		
		List<Enemy> enemyList = new ArrayList<>();
        for (Enemy[] locationGroup : enemies) {
            Collections.addAll(enemyList, locationGroup);
        }
        
        final int PAGE_SIZE = 5;
        int currentPage = 0;
        int totalPages = (int) Math.ceil((double) (enemyList.size()) / PAGE_SIZE);
        
        while(true) {
        	System.out.println("********************************************************************************");
            System.out.println("*                                  ENEMY LIST                                  *");
            System.out.println("*****                                                                      *****\n");
            
            int start = currentPage * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, enemyList.size());

            for (int i = start; i < end; i++) {
                System.out.println((i + 1) + ". " + enemyList.get(i).copy());
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
	
	/*****************************
	 * getEnemy
	 * @return - returns a random enemy
	 ****************************/
	public static Enemy getEnemy() {
		Enemy enemy = new UndeadEnemy();
		
		if(Commands.getRandomChance() < 0.3333) {
			enemy = enemies[0][Commands.getRandomInt(enemies[0].length) - 1].copy();
		}
		else if(Commands.getRandomChance() < 0.5) {
			enemy = enemies[1][Commands.getRandomInt(enemies[1].length) - 1].copy();
		}
		else {
			enemy = enemies[2][Commands.getRandomInt(enemies[2].length) - 1].copy();
		}
		
		return enemy;
	}
	
	/******************************************
	 * The array of all enemies, each row is a different type of enemy
	 ******************************************/
	private static final Enemy[][] enemies = {{
		new HumanEnemy("Elite Guard", 	  80,  0,  5, 10,  5, 0.25),
		new HumanEnemy("Royal Guard", 	  90,  0,  6,  8,  8, 0.30),
		new HumanEnemy("Royal Archer", 	  60,  0,  4, 12,  8, 0.40),
		new HumanEnemy("Lost Warrior", 	  70,  0,  5,  7, 10, 0.25),
		new HumanEnemy("Royal Brute",    120, 15,  6,  4,  4, 0.40)
	},{
		new UndeadEnemy("Zombie", 		  80,  0,  5,  7,  8, 0.20, 8),
		new UndeadEnemy("Skeleton",       60,  0,  5, 12,  6, 0.10, 10),
		new UndeadEnemy("Zombie Rhino",  110, 10,  5,  5,  4, 0.40, 10)
	},{
		new MagicalEnemy("Fire Axolotl",  60,  0,  5, 10,  5, 0.15, 1),
		new MagicalEnemy("Ice Sprite",    60,  0,  4,  7,  5, 0.30, 2),
		new MagicalEnemy("Wizard Spirit", 80,  0,  6,  9,  5, 0.10, 3),
		new MagicalEnemy("Golem",  		 110,  0,  10, 5,  3, 0.20, 1)
	}
	};
	
	
	
	/********************************
	 * displayItems
	 * This method displays all items in pages of 10 items
	 ********************************/
	public static void displayItems() {
		int choice;
		
		List<Item> itemList = new ArrayList<>();
        for (Item[] rarityGroup : items) {
            Collections.addAll(itemList, rarityGroup);
        }
        
        final int PAGE_SIZE = 10;
        int currentPage = 0;
        int totalPages = (int) Math.ceil((double) (itemList.size()) / PAGE_SIZE);
        
        while(true) {
        	System.out.println("********************************************************************************");
            System.out.println("*                                  ITEM LIST                                   *");
            System.out.println("*****                                                                      *****\n");
            
            int start = currentPage * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, itemList.size());

            for (int i = start; i < end; i++) {
                System.out.println((i + 1) + ". " + itemList.get(i));
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
	
	/***************************
	 * getItem
	 * @return - returns a random item based on rarity
	 ***************************/
	public static Item getItem() {
		double chance = Commands.getRandomChance();
		
		if(chance < 0.60) {
			return items[0][Commands.getRandomInt(items[0].length) - 1].copy();
		}
		else if(chance < 0.90) {
			return items[1][Commands.getRandomInt(items[1].length) - 1].copy();
		}
		else {
			return items[2][Commands.getRandomInt(items[2].length) - 1].copy();
		}
	}
	
	/*************************
	 * The array of all items
	 *************************/
	static Item[][] items = {{
		new BlockItem("Block Flask", 				ItemRarity.COMMON, 20, 1.10),
		new DamageItem("Damage Flask", 				ItemRarity.COMMON, 20, 1.10),
		new ManaItem("Mana Flask", 					ItemRarity.COMMON, 25, 1)
	},{
		new BlockItem("Block Potion", 				ItemRarity.RARE,   40, 1.20),
		new DamageItem("Damage Potion", 			ItemRarity.RARE,   40, 1.20),
		new ManaItem("Mana Potion", 				ItemRarity.RARE,   50, 2)
	},{
		new BlockItem("Potent Elixir Of Blocking", 	ItemRarity.EPIC,   80, 1.40),
		new DamageItem("Potent Elixir Of Damage",  	ItemRarity.EPIC,   80, 1.40),
		new ManaItem("Potent Elixir Of Mana", 	    ItemRarity.EPIC,  100, 3)	
	}
	};
	
	
	
	/********************************
	 * displayArtifacts
	 * This method displays all items in pages of 10 items
	 ********************************/
	public static void displayArtifacts() {
		int choice;
		
		List<Artifact> artifactList = new ArrayList<>();
        for (Artifact a : artifacts) {
            artifactList.add(a);
        }
        
        final int PAGE_SIZE = 10;
        int currentPage = 0;
        int totalPages = (int) Math.ceil((double) (artifactList.size()) / PAGE_SIZE);
        
        while(true) {
        	System.out.println("********************************************************************************");
            System.out.println("*                                ARTIFACT LIST                                 *");
            System.out.println("*****                                                                      *****\n");
            
            int start = currentPage * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, artifactList.size());

            for (int i = start; i < end; i++) {
                System.out.println((i + 1) + ". " + artifactList.get(i));
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
	
	public static void initiateArtifactList() {
		for(int i = 0; i < 10; i++) {
			Main.artifacts.add(getArtifact());
		}
	}
	
	public static Artifact getArtifact() {
		return artifacts[Commands.getRandomInt(artifacts.length) - 1].copy();
	}
	
	static Artifact[] artifacts = {
		new SaleArtifact("Trader's Coin", 		ArtifactType.COMMON, 0.95),
		new SaleArtifact("Gold Necklace", 		ArtifactType.COMMON, 0.9),
		new SaleArtifact("Crimson Amulet", 		ArtifactType.ANCIENT, 0.8),
		new AllArtifact("Emblem of Mastery", 	ArtifactType.COMMON, 1.2),
		new AllArtifact("Sigil of Power",	 	ArtifactType.COMMON, 1.25),
		new AllArtifact("Crown of Dominion", 	ArtifactType.ANCIENT, 1.4),
		new StrArtifact("Bracer of Might",     	ArtifactType.COMMON, 1.2),
		new StrArtifact("Dead Man's Ring",     	ArtifactType.COMMON, 1.25),
		new StrArtifact("Titan's Gauntlet", 	ArtifactType.ANCIENT, 1.4),
		new IntArtifact("Mindstone Pendant",	ArtifactType.COMMON, 1.2),
		new IntArtifact("Charm of Insight",		ArtifactType.COMMON, 1.25),
		new IntArtifact("Tome of Eternal Mind",	ArtifactType.ANCIENT, 1.4),
		new CorArtifact("Shadowbiner's token",	ArtifactType.COMMON, 1.2),
		new CorArtifact("Cursed Fang",			ArtifactType.COMMON, 1.25),	
		new CorArtifact("Bloodmoon Skull", 		ArtifactType.ANCIENT, 1.4),
		new ManaArtifact("Focus Gem",			ArtifactType.COMMON, 1),
		new ManaArtifact("Essence Stone",		ArtifactType.COMMON, 1)
	};
}