package card;

import game.Commands;

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

    public abstract void use();
    
    public abstract String toString();

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
    	
    	return constant + Commands.getRandomInt(variable);
    }
}