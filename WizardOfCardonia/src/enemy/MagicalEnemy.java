package enemy;

import game.Main;
import game.Battle;

public class MagicalEnemy extends Enemy{
	private final double mult;
	
	public MagicalEnemy(String name, int health, int block, int blockAmount, int damageConstant, int damageVariable, double specialChance, double increaseMult) {
		super(name, health, block, blockAmount, damageConstant, damageVariable, specialChance);
		this.mult = increaseMult;
	}
	
	public MagicalEnemy() {
		super();
		this.mult = 1;
	}
	
	public Enemy copy() {
		return new MagicalEnemy(name, health, block, blockAmount, damageConstant, damageVariable, specialChance, mult);
	}
	
	public void initialize() {
		maxHealth *= Battle.battleDifficulty;
		health *= Battle.battleDifficulty;
		block *= Battle.battleDifficulty;
		blockAmount *= Battle.battleDifficulty;
		damageConstant *= Battle.battleDifficulty;
		damageVariable *= Battle.battleDifficulty;
	}
	
	public void attack() {
		if(game.Commands.getRandomChance() < specialChance) {
			blockAmount *= mult;
			damageConstant *= mult;
			damageVariable *= mult;
			
			System.out.println("The enemy chooses to buff themselves! Their damage and gained block increases!");
		}
		else if(game.Commands.getRandomChance() < 0.75) {
			int damage = (int) this.getDamage();
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
			System.out.println("The enemy blocks for " + blockAmount + " for a total of " + block + " Damage block!");
		}
	}
	
	public String toString() {
		String str = name + 
				"\n   Base Max Health: " + maxHealth + " HP" +
				"\n   Starting block:  " + block + " Damage" + 
				"\n   Base Damage:     " + getMinDamage() + " - " + getMaxDamage() + " Damage" + 
				"\n   Has a " + String.format("%2.0f", specialChance*100) + "% chance to increase damage and gained block by " + String.format("%2.0f", mult*100) + "%\n";
		
		return str;
	}
}
