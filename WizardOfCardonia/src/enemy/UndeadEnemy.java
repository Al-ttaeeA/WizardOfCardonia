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
}
