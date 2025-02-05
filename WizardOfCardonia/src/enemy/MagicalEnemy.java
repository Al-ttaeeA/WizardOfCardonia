package enemy;

public class MagicalEnemy extends Enemy{
	private double currentMult;
	private final double increaseMult;
	
	public MagicalEnemy(String name, int health, int block, int blockAmount, int damageConstant, int damageVariable, double specialChance, double increaseMult) {
		super(name, health, block, blockAmount, damageConstant, damageVariable, specialChance);
		this.increaseMult = increaseMult;
		this.currentMult = 2;
	}
	
	public MagicalEnemy() {
		super();
		this.increaseMult = 0;
		this.currentMult = 1;
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
	
	public int getMinDamage() {
    	return (int) ((damageConstant+1) * currentMult);
    }
	
	public int getMaxDamage() {
    	return (int) ((damageConstant + damageVariable) * currentMult);
    }
}
