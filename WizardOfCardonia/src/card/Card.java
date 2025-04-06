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

    public Card(String name, Rarity rarity, int manaCost, Type type) {
        this.name = name;
        this.rarity = rarity;
        this.manaCost = manaCost;
        this.type = type;
    }
    
    public Card() {
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
    	
    	if(type == Type.CORRUPT && Commands.getRandomChance() < 0.25) {
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
}