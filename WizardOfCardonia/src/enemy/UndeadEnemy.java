package enemy;

public class UndeadEnemy extends Enemy{
	private final int healAmount;
	
	public UndeadEnemy(String name, int health, int block, int blockAmount, int damageConstant, int damageVariable, double specialChance, int healAmount) {
		super(name, health, block, blockAmount, damageConstant, damageVariable, specialChance);
		this.healAmount = healAmount;
	}
	
	public UndeadEnemy() {
		super();
		this.healAmount = 0;
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
				"\nBase Max Health: " + maxHealth + " HP" +
				"\nStarting block:  " + block + " Damage" + 
				"\nBase Damage:     " + getMinDamage() + " - " + getMaxDamage() + " Damage" + 
				"\nHas a " + String.format("%2.0f", specialChance*100) + "% chance to heal " + healAmount + " HP\n";
		
		return str;
	}
}
