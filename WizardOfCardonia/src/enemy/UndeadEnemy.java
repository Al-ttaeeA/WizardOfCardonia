package enemy;

import game.Main;

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
		maxHealth *= Main.testDiffMult;
		health *= Main.testDiffMult;
		block *= Main.testDiffMult;
		blockAmount *= Main.testDiffMult;
		damageConstant  *= Main.testDiffMult;
		damageVariable *= Main.testDiffMult;
		healAmount *= Main.testDiffMult;
	}
	
	public void attack() {
		if(game.Commands.getRandomChance() < specialChance) {
			health += healAmount;
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
				"\n   Has a " + String.format("%2.0f", specialChance*100) + "% chance to heal " + healAmount + " HP\n";
		
		return str;
	}
}
