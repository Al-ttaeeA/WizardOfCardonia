/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package card;

public class DamageCard extends Card {
    private final int damage;
    private int newDamage;

    public DamageCard(String name, Rarity rarity, int manaCost, Type type, int damage) {
        super(name, rarity, manaCost, type);
        this.damage = damage;
        this.newDamage = damage;
    }
    
    public DamageCard() {
    	super();
    	this.damage = 0;
    	this.newDamage = 0;
    }
    
    public Card copy() {
    	return new DamageCard(name, rarity, manaCost, type, damage);
    }

    public boolean use() {
    	newDamage = game.Commands.skillMultiplier(damage, type);
    	newDamage = (int) (newDamage * game.Battle.battleMult);
    	newDamage = (int) (newDamage * game.Battle.attackMult);
    	
    	if(doMana()) {
    		game.Battle.currentEnemy.takeDamage(newDamage);
    		
    		innerUse();
    		System.out.println("You attack for " + newDamage + " Damage leaving the enemy with " + game.Battle.currentEnemy.getHealth() + " HP and " + game.Battle.currentEnemy.getBlock() + " Damage block!");
    		
    		return true;
    	}
    	
    	return false;
    }
    
    public String toString() {
    	newDamage = game.Commands.skillMultiplier(damage, type);
    	newDamage = (int) (newDamage * game.Battle.battleMult);
    	newDamage = (int) (newDamage * game.Battle.attackMult);
    	
    	return name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tDeal " + newDamage + " damage\n";
    }

    public int getDamage(int num) {
        if(num == 0) {
        	return damage;
        }
        else {
        	return newDamage;
        }
    }
}
