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
}
