package item;

import game.*;

public abstract class Item {
	protected final String name;
	protected final ItemRarity rarity;
	protected final int price;
	
	public Item(String name, ItemRarity rarity, int price) {
		this.name = name;
		this.rarity = rarity;
		this.price = price;
	}
	
	public Item() {
		this.name = "Empty item";
		this.rarity = ItemRarity.COMMON;
		this.price = 0;
	}
	
	public abstract Item copy();
	
	public abstract void use();
	
	public abstract String toString();
	
	public String getName() {
		return name;
	}
	
	public ItemRarity getRarity() {
		return rarity;
	}
	
	public int getPrice() {
		return (int) (price * Main.permShopSale);
	}
}
