/***********************************************************************
 * Wizard of Cardonia
 * Copyright (C) 2025 A2Z Studios
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 ***********************************************************************/

package item;

import card.Rarity;
import game.*;

public abstract class Item {
	protected final String name;
	protected final ItemRarity rarity;
	protected final int price;
	
	protected final int id;
	
	public Item(int id, String name, ItemRarity rarity, int price) {
		this.id = id;
		this.name = name;
		this.rarity = rarity;
		this.price = price;
	}
	
	public Item() {
		this.id = 0;
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
	
	public int getId() {
    	return id;
    }
	
	public int getPrice() {
		return (int) (price * Main.permShopSale);
	}
	
	public abstract String save();
	
	public static Item loadItem(String line) {
		String[] tokens = line.split(",");
		
		String name = tokens[1];
		ItemRarity rarity = ItemRarity.valueOf(tokens[2]);
		int price = Integer.parseInt(tokens[3]);
		
		switch(Integer.parseInt(tokens[0])) {
		case 1:{
			double mult = Double.parseDouble(tokens[4]);
			return new AllItem(name, rarity, price, mult);
		}
		case 2:{
			double mult = Double.parseDouble(tokens[4]);
			return new BlockItem(name, rarity, price, mult);
		}
		case 3:{
			double mult = Double.parseDouble(tokens[4]);
			return new DamageItem(name, rarity, price, mult);
		}
		case 4:{
			double mult = Double.parseDouble(tokens[4]);
			return new HealItem(name, rarity, price, mult);
		}
		case 5:{
			int mana = Integer.parseInt(tokens[4]);
			return new ManaItem(name, rarity, price, mana);
		}
		}
		
		return new BlockItem();
	}
}
