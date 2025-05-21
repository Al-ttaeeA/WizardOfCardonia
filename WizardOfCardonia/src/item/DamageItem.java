/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package item;

public class DamageItem extends Item{
	private final double mult;
	
	public DamageItem(String name, ItemRarity rarity, int price, double mult) {
		super(3, name, rarity, price);
		this.mult = mult;
	}
	
	public DamageItem() {
		super();
		this.mult = 1;
	}
	
	public Item copy() {
		return new DamageItem(name, rarity, price, mult);
	}
	
	public void use() {
		game.Battle.attackMult *= mult;
		System.out.println("You boost ALL damage by " + String.format("%2.0f", 100*(mult-1)) + "% until the end of the battle!");
	}
	
	public String toString() {
		return name + " [" + price + " G]\n\tBoosts ALL damage by " + String.format("%2.0f", 100*(mult-1)) + "% until the end of the battle\n";
	}
	
	public double getMult() {
		return mult;
	}
}
