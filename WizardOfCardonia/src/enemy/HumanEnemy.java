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
			game.Main.testInt += blockAmount;
		}
		else if(game.Commands.getRandomChance() < 0.75) {
			game.Main.testInt -= this.getDamage();
		}
		else {
			game.Main.testInt += blockAmount;
		}
	}
}
