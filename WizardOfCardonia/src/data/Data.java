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
}
