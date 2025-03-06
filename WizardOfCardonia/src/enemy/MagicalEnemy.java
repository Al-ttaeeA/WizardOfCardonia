package enemy;

import game.Main;
import game.Battle;

public class MagicalEnemy extends Enemy{
	private double currentMult;
	private final double increaseMult;
	
	public MagicalEnemy(String name, int health, int block, int blockAmount, int damageConstant, int damageVariable, double specialChance, double increaseMult) {
		super(name, health, block, blockAmount, damageConstant, damageVariable, specialChance);
		this.increaseMult = increaseMult;
		this.currentMult = 1;
	}
	
	public MagicalEnemy() {
		super();
		this.increaseMult = 0;
		this.currentMult = 1;
	}
	
	public Enemy copy() {
		return new MagicalEnemy(name, health, block, blockAmount, damageConstant, damageVariable, specialChance, increaseMult);
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
			currentMult += increaseMult;
		}
		else if(game.Commands.getRandomChance() < 0.75) {
			game.Main.testInt -= (int) (this.getDamage() * currentMult);
		}
		else {
			block += (int) (blockAmount * currentMult);
		}
	}
	
	public String toString() {
		String str = name + 
				"\n   Base Max Health: " + maxHealth + " HP" +
				"\n   Starting block:  " + block + " Damage" + 
				"\n   Base Damage:     " + getMinDamage() + " - " + getMaxDamage() + " Damage" + 
				"\n   Has a " + String.format("%2.0f", specialChance*100) + "% chance to increase damage and gained block by " + String.format("%2.0f", increaseMult*100) + "%\n";
		
		return str;
	}
	
	public int getMinDamage() {
    	return (int) ((damageConstant+1) * currentMult);
    }
	
	public int getMaxDamage() {
    	return (int) ((damageConstant + damageVariable) * currentMult);
    }
}
