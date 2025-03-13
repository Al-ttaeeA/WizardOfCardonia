/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package card;

public class WeaknessCard extends Card{
	private final double weakness;
	
	public WeaknessCard(String name, Rarity rarity, int manaCost, Type type, double weakness) {
		super(name, rarity, manaCost, type);
		this.weakness = weakness;
	}
    
	public WeaknessCard() {
		super();
		this.weakness = 0;
	}
	
	public Card copy() {
		return new WeaknessCard(name, rarity, manaCost, type, weakness);
	}
	
	public boolean use() {
		if(doMana()) {
			game.Battle.enemyDamageMult *= weakness;
			
			innerUse();
			System.out.println("You weaken the enemy's attacks by " + String.format("%.2f", 100*(1-weakness)) + "%!");
			
    		return true;
    	}
    	
    	return false;
	}
	
	public String toString() {
		return name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tWeaken the enemy's damage by " + String.format("%.2f", 100*(1-weakness)) + "%\n";
	}
	
	public double getDmgDebuff(int num) {
		return weakness;
    }
}
