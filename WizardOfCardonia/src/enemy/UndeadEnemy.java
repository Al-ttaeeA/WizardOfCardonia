/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package enemy;

import game.Main;
import game.Battle;

public class UndeadEnemy extends Enemy{
	private int healAmount;
	
	public UndeadEnemy(String name, int health, int block, int blockAmount, int damageConstant, int damageVariable, double specialChance, int healAmount) {
		super(name, health, block, blockAmount, damageConstant, damageVariable, specialChance);
		this.healAmount = healAmount;
	}
	
	public UndeadEnemy() {
		super();
		this.healAmount = 0;
	}
	
	public Enemy copy() {
		return new UndeadEnemy(name, health, block, blockAmount, damageConstant, damageVariable, specialChance, healAmount);
	}
	
	public void initialize() {
		maxHealth *= Battle.battleDifficulty;
		health *= Battle.battleDifficulty;
		block *= Battle.battleDifficulty;
		blockAmount *= Battle.battleDifficulty;
		damageConstant  *= Battle.battleDifficulty;
		damageVariable *= Battle.battleDifficulty;
		healAmount *= Battle.battleDifficulty;
	}
	
	public void attack() {
		if(game.Commands.getRandomChance() < specialChance && health < (maxHealth-healAmount)) {
			health += healAmount;
			System.out.println("The enemy heals for " + healAmount + " HP for a total of " + health + " HP!");
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
				System.out.println("The enemy attacks you for " + damage + ", leaving you with " + game.Battle.currentBlock + " Damage block!");
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
				"\n   Has a " + String.format("%2.0f", specialChance*100) + "% chance to heal " + healAmount + " HP\n";
		
		return str;
	}
}
