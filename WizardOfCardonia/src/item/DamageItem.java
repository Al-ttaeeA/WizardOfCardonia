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
	
	public Item copy() {
		return new DamageItem(name, rarity, price, dmgBuff);
	}
	
	public void use() {
		game.Main.testDouble *= dmgBuff;
	}
	
	public String toString() {
		return name + " [" + price + " G]\n\tBoosts ALL damage by " + String.format("%2.0f", 100*(dmgBuff-1)) + "% until the end of the battle\n";
	}
	
	public double getDmgBuff() {
		return dmgBuff;
	}
}
