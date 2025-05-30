/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package card;

import game.Main;

public class HealCard extends Card {
    private final int heal;
    private int newHeal;

    public HealCard(String name, Rarity rarity, int manaCost, Type type, int heal) {
        super(6, name, rarity, manaCost, type);
        this.heal = heal;
        this.newHeal = heal;
    }
    
    public HealCard() {
    	super();
    	this.heal = 0;
    	this.newHeal = 0;
    }
    
    public Card copy() {
    	return new HealCard(name, rarity, manaCost, type, heal);
    }

    public boolean use() {
    	newHeal = heal;
    	newHeal = (int) (newHeal * game.Battle.battleMult);
    	newHeal = (int) (newHeal * game.Battle.healMult);
    	
    	if(doMana()) {
    		game.Main.playCurrentHP += newHeal;
    		
    		if(game.Main.playCurrentHP > game.Main.playMaxHP) {
    			newHeal = newHeal - (game.Main.playCurrentHP - game.Main.playMaxHP);
    			game.Main.playCurrentHP = game.Main.playMaxHP;
    		}
    		
    		innerUse();
    		System.out.println("You heal for " + newHeal + " HP for a total of " + Main.playCurrentHP + " HP!");
    		
    		return true;
    	}
    	
    	return false;
    }
    
    public String toString() {
    	newHeal = heal;
    	newHeal = (int) (newHeal * game.Battle.battleMult);
    	newHeal = (int) (newHeal * game.Battle.healMult);
    	
    	return name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tHeal " + newHeal + " HP\n"; 
    }

    public int getHeal(int num) {
    	if(num == 0) {
        	return heal;
        }
        else {
        	return newHeal;
        }
    }
    
    public String save() {
    	String str = this.id + ",";
    	str += this.name + ",";
    	str += this.rarity + ",";
    	str += this.manaCost + ",";
    	str += this.type + ",";
    	
    	str += this.heal;
    	
    	return str;
    }
}
