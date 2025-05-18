/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package card;

import game.Battle;
import game.Main;

public class DamageBlockCard extends Card{
	private final int damage;
	private int newDamage;
	
	private final int block;
	private int newBlock;
	
	public DamageBlockCard(String name, Rarity rarity, int manaCost, Type type, int damage, int block) {
		super(name, rarity, manaCost, type);
		this.damage = damage;
		this.newDamage = damage;
		this.block = block;
		this.newBlock = block;
	}
	
	public DamageBlockCard() {
		super();
		this.damage = 0;
		this.newDamage = 0;
		this.block = 0;
		this.newBlock = 0;
	}
	
	public Card copy() {
		return new DamageBlockCard(name, rarity, manaCost, type, damage, block);
	}
	
	public boolean use() {
		newDamage = game.Commands.skillMultiplier(damage, type);
    	newDamage = (int) (newDamage * game.Battle.battleMult);
    	newDamage = (int) (newDamage * game.Battle.attackMult);
    	
    	newBlock = game.Commands.skillMultiplier(block, type);
    	newBlock = (int) (newBlock * game.Battle.battleMult);
    	newBlock = (int) (newBlock * game.Battle.blockMult);
    	
    	if(doMana()) {
    		innerUse();
    		
    		//Only if its boss
    		if(Main.battleCount % 5 == 0 && Main.battleCount <= 15) {
    			if(Battle.currentEnemy.getResilience().equals(this.type)) {
    				if(this.type == Type.CORRUPT) { //Corrupt resilience bosses are much more resilient
    					newDamage = (int) (newDamage * 0.2);
    				}
    				else {
    					newDamage = (int) (newDamage * 0.5);
    				}
    				
    				System.out.println(Battle.currentEnemy.getName() + " is resilient to " + type + " damage! Damage reduced to " + newDamage + "!\n");
    			}
    		}
    		
    		game.Battle.currentEnemy.takeDamage(newDamage);
    		
    		System.out.println("You attack for " + newDamage + " Damage leaving the enemy with " + game.Battle.currentEnemy.getHealth() + " HP and " + game.Battle.currentEnemy.getBlock() + " Damage block!\n");
    		
    		
    		game.Battle.currentBlock += newBlock;
    		
    		System.out.println("And you gain " + newBlock + " Damage block for a total of " + game.Battle.currentBlock + " Block!");
    		
    		return true;
    	}
    	
    	return false;
	}
	
	public String toString() {
		newDamage = game.Commands.skillMultiplier(damage, type);
    	newDamage = (int) (newDamage * game.Battle.battleMult);
    	newDamage = (int) (newDamage * game.Battle.attackMult);
    	
    	newBlock = game.Commands.skillMultiplier(block, type);
    	newBlock = (int) (newBlock * game.Battle.battleMult);
    	newBlock = (int) (newBlock * game.Battle.blockMult);
    	
    	String str = name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tDeal " + newDamage + " damage AND Gain " + newBlock + " block\n";
    	
    	return str;
	}
}
