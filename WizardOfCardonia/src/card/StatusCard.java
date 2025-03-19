/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package card;

import game.Commands;
import game.Battle;

public class StatusCard extends Card{
	private final double effectChance;
	
	public StatusCard(String name, Rarity rarity, int manaCost, Type type, double effectChance) {
		super(name, rarity, manaCost, type);
		this.effectChance = effectChance;
	}
	
	public StatusCard() {
		super();
		this.effectChance = 0;
	}
	
	public Card copy() {
		return new StatusCard(name, rarity, manaCost, type, effectChance);
	}
	
	public boolean use() {
		if(doMana()) {
    		innerUse();
    		
    		if(Commands.getRandomChance() < effectChance) {
    			switch(type) {
	    		case Type.PHYSICAL:{
	    			Battle.currentEnemy.setInjured();
	    			
	    			System.out.println("You injure the enemy making their attacks weaker by 25%!");
	    			
	    			break;
	    		}
	    		case Type.MAGICAL:{
	    			Battle.currentEnemy.setBurn();
	    			
	    			System.out.println("You burn the enemy making them take damage over time with a chance of stopping!");
	    			
	    			break;
	    		}
	    		case Type.CORRUPT:{
	    			Battle.currentEnemy.setPoison();
	    			
	    			System.out.println("You poison the enemy making them take damage over time!");
	    			
	    			break;
	    		}
	    		}
    		}
    		else {
    			System.out.println("Unfortunately, your card does nothing!");
    		}
    		
    		return true;
    	}
    	
    	return false;
	}
	
	public String toString() {
		String str = name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}";
		String effect = " ";
		
		switch(type) {
		case Type.PHYSICAL:{
			effect = "Injure";
			
			break;
		}
		case Type.MAGICAL:{
			effect = "Burn";
			
			break;
		}
		case Type.CORRUPT:{
			effect = "Poison";
			
			break;
		}
		}
		
		if(effectChance == 1) {
			str += "\n\t" + effect + " the enemy!\n";
		}
		else {
			str += "\n\tHas a " + String.format("%2.0f", 100*(1-effectChance)) + "% chance to " + effect + " the enemy!\n";
		}
		
		return str;
	}
	
	public double getEffectChance() {
		return effectChance;
	}
}
