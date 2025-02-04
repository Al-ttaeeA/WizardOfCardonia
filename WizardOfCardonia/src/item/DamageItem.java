package item;

public class DamageItem extends Item{
	private final double dmgBuff;
	
	public DamageItem(String name, ItemRarity rarity, double dmgBuff) {
		super(name, rarity);
		this.dmgBuff = dmgBuff;
	}
	
	public DamageItem() {
		super();
		this.dmgBuff = 1;
	}
	
	public void use() {
		game.Main.testDouble *= dmgBuff;
	}
	
	public String toString() {
		return name + "\n\tBoost ALL damage by " + (dmgBuff-1) + "% until the end of the battle";
	}
	
	public double getDmgBuff() {
		return dmgBuff;
	}
}
