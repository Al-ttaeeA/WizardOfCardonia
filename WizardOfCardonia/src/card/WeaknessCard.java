package card;

public class WeaknessCard extends Card{
	private final double weakness;
	private double newWeakness;
	
	public WeaknessCard(String name, Rarity rarity, int manaCost, Type type, double weakness) {
		super(name, rarity, manaCost, type);
		this.weakness = weakness;
		this.newWeakness = weakness;
	}
    
	public WeaknessCard() {
		super();
		this.weakness = 0;
		this.newWeakness = 0;
	}
	
	public void use() {
		newWeakness = game.Commands.skillMultiplier(weakness, type);
		
		game.Main.testInt = (int) Math.ceil(game.Main.testInt * newWeakness);
	}
	
	public String toString() {
		newWeakness = game.Commands.skillMultiplier(weakness, type);
		
		return name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tWeaken the enemy's damage by " + 100*(1-newWeakness) + "%\n";
	}
	
	public double getDmgDebuff(int num) {
		if(num == 0) {
			return weakness;
        }
        else {
        	return newWeakness;
        }
	}
}
