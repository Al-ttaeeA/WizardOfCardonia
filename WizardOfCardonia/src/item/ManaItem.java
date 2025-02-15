package item;

public class ManaItem extends Item{
	private final int mana;
	
	public ManaItem(String name, ItemRarity rarity, int price, int mana) {
		super(name, rarity, price);
		this.mana = mana;
	}
	
	public ManaItem() {
		super();
		this.mana = 0;
	}
	
	public Item copy() {
		return new ManaItem(name, rarity, price, mana);
	}
	
	public void use() {
		game.Main.testInt += mana;
	}
	
	public String toString() {
		return name + " [" + price + " G]\n\tIncreases max mana by " + mana + " MP until the end of the battle\n";
	}
	
	public int getMana() {
		return mana;
	}
}
