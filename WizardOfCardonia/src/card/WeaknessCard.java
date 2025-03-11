package card;

public class WeaknessCard extends Card{
	private final double weakness;
	
	public WeaknessCard(String name, Rarity rarity, int manaCost, Type type, double weakness) {
		super(name, rarity, manaCost, type);
		this.weakness = weakness;
	}
    
	public WeaknessCard() {
		super();
		this.weakness = 0;
	}
	
	public Card copy() {
		return new WeaknessCard(name, rarity, manaCost, type, weakness);
	}
	
	public void use() {
		game.Battle.enemyDamageMult *= weakness;
	}
	
	public String toString() {
		return name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tWeaken the enemy's damage by " + String.format("%.2f", 100*(1-weakness)) + "%\n";
	}
	
	public double getDmgDebuff(int num) {
		return weakness;
    }
}
