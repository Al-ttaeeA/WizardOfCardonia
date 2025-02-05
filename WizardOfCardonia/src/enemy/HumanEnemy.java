package enemy;

public class HumanEnemy extends Enemy{
	public HumanEnemy(String name, int health, int block, int blockAmount, int damageConstant, int damageVariable, double specialChance) {
		super(name, health, block, blockAmount, damageConstant, damageVariable, specialChance);
	}
	
	public HumanEnemy() {
		super();
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
				"\nBase Max Health: " + maxHealth + " HP" +
				"\nStarting block:  " + block + " Damage" + 
				"\nBase Damage:     " + getMinDamage() + " - " + getMaxDamage() + " Damage" + 
				"\nHas a " + String.format("%2.0f", specialChance*100) + "% chance to block and deal damage on the same turn\n";
		
		return str;
	}
}
