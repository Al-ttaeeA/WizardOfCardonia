package item;

public class HealItem extends Item{
private final double mult;
	
	public HealItem(String name, ItemRarity rarity, int price, double mult) {
		super(name, rarity, price);
		this.mult = mult;
	}
	
	public HealItem() {
		super();
		this.mult = 1;
	}
	
	public Item copy() {
		return new HealItem(name, rarity, price, mult);
	}
	
	public void use() {
		game.Battle.healMult *= mult;
		System.out.println("You boost ALL heals by " + String.format("%2.0f", 100*(mult-1)) + "% until the end of the battle!");
	}
	
	public String toString() {
		return name + " [" + price + " G]\n\tBoosts ALL heals by " + String.format("%2.0f", 100*(mult-1)) + "% until the end of the battle\n";
	}
	
	public double getMult() {
		return mult;
	}
}
