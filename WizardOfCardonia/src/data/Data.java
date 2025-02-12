package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import card.*;
import enemy.*;
import item.*;
import game.*;

public class Data {
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
	
	public static Card getRandomCard() {
		double chance = Commands.getRandomChance();
		
		if(chance < 0.49) {
			return cards[0][Commands.getRandomInt(cards[0].length) - 1];
		}
		else if(chance < 0.79) {
			return cards[1][Commands.getRandomInt(cards[1].length) - 1];
		}
		else if(chance < 0.94) {
			return cards[2][Commands.getRandomInt(cards[2].length) - 1];
		}
		else if(chance < 0.99) {
			return cards[3][Commands.getRandomInt(cards[3].length) - 1];
		}
		else {
			return cards[4][Commands.getRandomInt(cards[4].length) - 1];
		}
	}
	
	static Card[][] cards = {{
		new DamageCard("Stab", 				Rarity.COMMON, 1, Type.PHYSICAL, 10),
		new DamageCard("Jab", 				Rarity.COMMON, 2, Type.PHYSICAL, 18),			
		new DamageCard("Fireball", 			Rarity.COMMON, 1, Type.MAGICAL, 12),
		new DamageCard("Dark Shroud", 		Rarity.COMMON, 1, Type.CORRUPT, 15),
		new BlockCard("Shield", 			Rarity.COMMON, 1, Type.PHYSICAL, 5),
		new BlockCard("Spectral Shield", 	Rarity.COMMON, 1, Type.MAGICAL, 4),
		new BlockCard("Shadow Seal", 		Rarity.COMMON, 1, Type.CORRUPT, 8)
	},{
		new DamageCard("Slash", 			Rarity.UNCOMMON, 2, Type.PHYSICAL, 25),
		new DamageCard("Ice Shards", 		Rarity.UNCOMMON, 2, Type.MAGICAL, 28),
		new DamageCard("Blood Slash", 		Rarity.UNCOMMON, 2, Type.CORRUPT, 35)
	},{
		new HealCard("Heal Orb",			Rarity.RARE, 1, Type.MAGICAL, 10),
		new HealCard("Cursed Replenish",	Rarity.RARE, 1, Type.MAGICAL, 15)
	},{
		new ManaCard("Mana Replenish", 		Rarity.EPIC, 0, Type.MAGICAL, 1),
		new ManaCard("Shadow Power", 		Rarity.EPIC, 0, Type.MAGICAL, 2)
	},{
		new WeaknessCard("Bone Breaker", 	Rarity.LEGENDARY, 1, Type.PHYSICAL, 0.80),
		new WeaknessCard("Blinding Spell", 	Rarity.LEGENDARY, 1, Type.MAGICAL, 0.75)
	}
	};
	
	
	
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
                System.out.println((i + 1) + ". " + enemyList.get(i));
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
	
	private static final Enemy[][] enemies = {{
		new HumanEnemy("Elite Guard", 	  80,  0,  3,  8,  5, 0.25),
		new HumanEnemy("Royal Guard", 	  90,  0,  4,  6,  8, 0.30),
		new HumanEnemy("Royal Archer", 	  60,  0,  2, 10,  8, 0.40),
		new HumanEnemy("Lost Warrior", 	  70,  0,  3,  5, 10, 0.25),
		new HumanEnemy("Royal Brute",    120, 15,  4,  2,  4, 0.40)
	},{
		new UndeadEnemy("Zombie", 		  80,  0,  3,  5,  8, 0.20, 8),
		new UndeadEnemy("Skeleton",       60,  0,  3, 10,  6, 0.10, 10),
		new UndeadEnemy("Zombie Rhino",  110, 10,  3,  3,  4, 0.40, 10)
	},{
		new MagicalEnemy("Fire Axolotl",  60,  0,  3,  8,  5, 0.30, 0.20),
		new MagicalEnemy("Ice Sprite",    60,  0,  2,  5,  5, 0.60, 0.20),
		new MagicalEnemy("Wizard Spirit", 80,  0,  4,  7,  5, 0.20, 0.50),
		new MagicalEnemy("Golem",  		 110,  0,  8,  3,  3, 0.40, 0.30)
	}
	};
	
	
	
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
	
	public static Item getItem() {
		double chance = Commands.getRandomChance();
		
		if(chance < 0.60) {
			return items[0][Commands.getRandomInt(items[0].length) - 1];
		}
		else if(chance < 0.90) {
			return items[1][Commands.getRandomInt(items[1].length) - 1];
		}
		else {
			return items[2][Commands.getRandomInt(items[2].length) - 1];
		}
	}
	
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
}