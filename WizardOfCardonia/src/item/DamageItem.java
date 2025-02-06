package item;

public class DamageItem extends Item{
	private final double dmgBuff;
	
	public DamageItem(String name, ItemRarity rarity, int price, double dmgBuff) {
		super(name, rarity, price);
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
		return name + " [" + price + " G]\n\tBoost ALL damage by " + (dmgBuff-1) + "% until the end of the battle";
	}
	
	public double getDmgBuff() {
		return dmgBuff;
	}
}
