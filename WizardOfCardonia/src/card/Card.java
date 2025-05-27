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

package card;

import game.Battle;
import game.Commands;
import game.Main;

public abstract class Card {
    protected final String name;
    protected final Rarity rarity;
    protected final int manaCost;
    protected final Type type;
    
    protected final int id;

    public Card(int id, String name, Rarity rarity, int manaCost, Type type) {
        this.id = id;
    	this.name = name;
        this.rarity = rarity;
        this.manaCost = manaCost;
        this.type = type;
    }
    
    public Card() {
    	this.id = 0;
    	this.name = "Empty Card";
    	this.rarity = Rarity.COMMON;
    	this.manaCost = 0;
    	this.type = Type.PHYSICAL;
    }
    
    public abstract Card copy();

    public abstract boolean use();
    
    public abstract String toString();
    
    protected void innerUse() {
    	System.out.println("You use " + this.name + " costing you " + this.manaCost + " Mana!\n");
    	
    	if(type == Type.CORRUPT && Commands.getRandomChance() < 0.10) {
    		Main.corruptedness += 1;
    		System.out.println("The corruptness spreads...\n");
    	}
    }
    
    protected boolean doMana() {
    	if(Battle.currentMana < manaCost) {
    		System.out.println("You do not have enough Mana to play this card!");
    		Commands.pressEnter();
    		return false;
    	}
    	
    	Battle.currentMana -= manaCost;
    	
    	return true;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getManaCost() {
        return manaCost;
    }

    public Type getType() {
        return type;
    }
    
    public int getId() {
    	return id;
    }
    
    public int getPrice() {
    	int constant = 0;
    	int variable = 0;
    	
    	switch(rarity) {
    	case COMMON:{
    		constant = 10;
    		variable = 15;
    		break;
    	}
    	case UNCOMMON:{
    		constant = 25;
    		variable = 15;
    		break;
    	}
    	case RARE:{
    		constant = 40;
    		variable = 30;
    		break;
    	}
    	case EPIC:{
    		constant = 70;
    		variable = 30;
    		break;
    	}
    	case LEGENDARY:{
    		constant = 100;
    		variable = 50;
    		break;
    	}
    	}
    	
    	return (int) ((constant + Commands.getRandomInt(variable)) * Main.permShopSale);
    }
    
    
    
    public abstract String save();
    
    public static Card loadCard(String line) {
    	String[] tokens = line.split(",");
    	
    	String name = tokens[1];
    	Rarity rarity = Rarity.valueOf(tokens[2]);
    	int manaCost = Integer.parseInt(tokens[3]);
    	Type type = Type.valueOf(tokens[4]);
    	
    	switch(Integer.parseInt(tokens[0])) {
    	case 1:{
    		int block = Integer.parseInt(tokens[5]);
    		return new BlockCard(name, rarity, manaCost, type, block);
    	}
    	case 2:{
    		int block = Integer.parseInt(tokens[5]);
    		int heal = Integer.parseInt(tokens[6]);
    		return new BlockHealCard(name, rarity, manaCost, type, block, heal);
    	}
		case 3:{
			int damage = Integer.parseInt(tokens[5]);
			int block = Integer.parseInt(tokens[6]);
			return new DamageBlockCard(name, rarity, manaCost, type, damage, block);
		}
		case 4:{
			int damage = Integer.parseInt(tokens[5]);
			return new DamageCard(name, rarity, manaCost, type, damage);
		}
		case 5:{
			int cardCount = Integer.parseInt(tokens[5]);
			return new DrawCard(name, rarity, manaCost, type, cardCount);
		}
		case 6:{
			int heal = Integer.parseInt(tokens[5]);
			return new HealCard(name, rarity, manaCost, type, heal);
		}
		case 7:{
			int mana = Integer.parseInt(tokens[5]);
			return new ManaCard(name, rarity, manaCost, type, mana);
		}
		case 8:{
			double effectChance = Double.parseDouble(tokens[5]);
			return new StatusCard(name, rarity, manaCost, type, effectChance);
		}
		case 9:{
			double weakness = Double.parseDouble(tokens[5]);
			return new WeaknessCard(name, rarity, manaCost, type, weakness);
		}
    	}
    	
    	return new BlockCard();
    }
}