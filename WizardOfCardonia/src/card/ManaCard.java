/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package card;

public class ManaCard extends Card{
	private final int mana;
	
	public ManaCard(String name, Rarity rarity, int manaCost, Type type, int mana) {
		super(7, name, rarity, manaCost, type);
		this.mana = mana;
	}
	
	public ManaCard() {
		super();
		this.mana = 0;
	}
	
	public Card copy() {
		return new ManaCard(name, rarity, manaCost, type, mana);
	}
	
	public boolean use() {
		if(doMana()) {
			game.Battle.currentMana += mana;
			
			innerUse();
			System.out.println("You gain " + mana + " Mana for a total of " + game.Battle.currentMana + " Mana for this round!");
			
    		return true;
    	}
    	
    	return false;
	}
	
	public String toString() {
		return name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tGain " + mana + " mana\n";
	}
	
	public String save() {
    	String str = this.id + ",";
    	str += this.name + ",";
    	str += this.rarity + ",";
    	str += this.manaCost + ",";
    	str += this.type + ",";
    	
    	str += this.mana;
    	
    	return str;
    }
}
