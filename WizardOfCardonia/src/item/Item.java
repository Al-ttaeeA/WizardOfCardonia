package item;

public abstract class Item {
	protected final String name;
	protected final ItemRarity rarity;
	
	public Item(String name, ItemRarity rarity) {
		this.name = name;
		this.rarity = rarity;
	}
	
	public Item() {
		this.name = "Empty item";
		this.rarity = ItemRarity.COMMON;
	}
	
	public abstract void use();
	
	public abstract String toString();
	
	public String getName() {
		return name;
	}
	
	public ItemRarity getRarity() {
		return rarity;
	}
}
