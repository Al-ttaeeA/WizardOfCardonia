package enemy;

public class BossEnemy extends Enemy{
	private static int healAmount;
	
	public BossEnemy(String name, int health, int block, int blockAmount, int damageConstant, int damageVariable, int healAmount) {
		super(name, health, block, blockAmount, damageConstant, damageVariable, 0);
		this.healAmount = healAmount;
	}
	
	public BossEnemy() {
		super();
		this.healAmount = 0;
	}
	
	public Enemy copy() {
		return new BossEnemy(name, health, block, blockAmount, damageConstant, damageVariable, healAmount);
	}
	
	public void initialize() {
		//Empty
	}
	
	public void attack() {
		//Ai algorithm to be implemented
	}
	
	public String toString() {
		String str = name + 
				"\n   Base Max Health: " + maxHealth + " HP" +
				"\n   Starting block:  " + block + " Damage" + 
				"\n   Base Damage:     " + getMinDamage() + " - " + getMaxDamage() + " Damage" + 
				"\n   This is a BOSS, and can deal damage, block, heal, and deal damage and block at the same time";
		
		return str;
	}
}
