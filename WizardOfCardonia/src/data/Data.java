package data;

import card.*;
import enemy.*;
import item.*;
import game.*;

public class Data {
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
		new WeaknessCard("PhysicalWeakness",Rarity.LEGENDARY, 1, Type.PHYSICAL, 0.70),
		new WeaknessCard("MagicalWeakness", Rarity.LEGENDARY, 1, Type.MAGICAL, 0.65)
	}
	};
	
	
	
	public static Enemy getEnemy() {
		return enemies[Main.location-1][Commands.getRandomInt(enemies[Main.location-1].length) - 1];
	}
	
	static Enemy[][] enemies = {{
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
		new BlockItem("Block Flask", 				ItemRarity.COMMON, 20, 0.10),
		new DamageItem("Damage Flask", 				ItemRarity.COMMON, 20, 0.10),
		new ManaItem("Mana Flask", 					ItemRarity.COMMON, 25, 1)
	},{
		new BlockItem("Block Potion", 				ItemRarity.RARE,   40, 0.20),
		new DamageItem("Damage Potion", 			ItemRarity.RARE,   40, 0.20),
		new ManaItem("Mana Potion", 				ItemRarity.RARE,   50, 2)
	},{
		new BlockItem("Potent Elixir Of Blocking", 	ItemRarity.EPIC,   80, 0.40),
		new DamageItem("Potent Elixir Of Damage",  	ItemRarity.EPIC,   80, 0.40),
		new ManaItem("Potent Elixir Of Mana", 	    ItemRarity.EPIC,  100, 3)	
	}
	};
}