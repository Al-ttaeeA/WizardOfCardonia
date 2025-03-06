package enemy;

import game.Main;
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
	
	public void attack() {
		if(game.Commands.getRandomChance() < specialChance) {
			game.Main.testInt -= this.getDamage();
			block += blockAmount;
		}
		else if(game.Commands.getRandomChance() < 0.75) {
			game.Main.testInt -= this.getDamage();
		}
		else {
			block += blockAmount;
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
