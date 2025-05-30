/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package enemy;

import game.Battle;

public class HumanEnemy extends Enemy{
	public HumanEnemy(String name, int health, int block, int blockAmount, int damageConstant, int damageVariable, double specialChance) {
		super(name, health, block, blockAmount, damageConstant, damageVariable, specialChance);
	}
	
	public HumanEnemy() {
		super();
	}
	
	public Enemy copy() {
		return new HumanEnemy(name, health, block, blockAmount, damageConstant, damageVariable, specialChance);
	}
	
	public void initialize() {
		maxHealth *= Battle.battleDifficulty;
		health *= Battle.battleDifficulty;
		block *= Battle.battleDifficulty;
		blockAmount *= Battle.battleDifficulty;
		damageConstant  *= Battle.battleDifficulty;
		damageVariable *= Battle.battleDifficulty;
	}
	
	public void action() {
		innerAction();
		
		if(health <= 0) {
			return;
		}
		
		if(game.Commands.getRandomChance() < specialChance) {
			int damage = this.getDamage();
			int diff = game.Battle.currentBlock - damage;
			
			if(diff < 0) {
				game.Battle.currentBlock = 0;
				game.Main.playCurrentHP += diff;
				System.out.println("The enemy attacks you for " + damage + ", going through all of your block and leaving you with " + game.Main.playCurrentHP + " HP!\n");
			}
			else {
				game.Battle.currentBlock = diff;
				System.out.println("The enemy attacks you for " + damage + ", you fully block the attack!\n");
			}
			
			block += blockAmount;
			System.out.println("The enemy also increases their block for a total of " + block + " Damage block!");
		}
		else if(game.Commands.getRandomChance() < 0.75) {
			int damage = this.getDamage();
			int diff = game.Battle.currentBlock - damage;
			
			if(diff < 0) {
				game.Battle.currentBlock = 0;
				game.Main.playCurrentHP += diff;
				
				if(game.Main.playCurrentHP <= 0) {
					game.Main.playCurrentHP = 0;
				}
				
				System.out.println("The enemy attacks you for " + damage + ", going through all of your block and leaving you with " + game.Main.playCurrentHP + " HP!");
			}
			else {
				game.Battle.currentBlock = diff;
				System.out.println("The enemy attacks you for " + damage + ", you fully block the attack!");
			}
		}
		else {
			block += blockAmount;
			System.out.println("The enemy increases their block for a total of " + block + " Damage block!");
		}
	}
	
	public String toString() {
		String str = name + 
				"\n   Base Max Health: " + maxHealth + " HP" +
				"\n   Starting block:  " + block + " Damage" + 
				"\n   Base Damage:     " + getMinDamage() + " - " + getMaxDamage() + " Damage" + 
				"\n   Has a " + String.format("%2.0f", specialChance*100) + "% chance to block and deal damage on the same turn\n";
		
		return str;
	}
}
