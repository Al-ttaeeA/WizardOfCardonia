/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package item;

public class AllItem extends Item{
private final double mult;
	
	public AllItem(String name, ItemRarity rarity, int price, double mult) {
		super(name, rarity, price);
		this.mult = mult;
	}
	
	public AllItem() {
		super();
		this.mult = 1;
	}
	
	public Item copy() {
		return new AllItem(name, rarity, price, mult);
	}
	
	public void use() {
		game.Battle.battleMult *= mult;
		System.out.println("You boost ALL card effects by " + String.format("%2.0f", 100*(mult-1)) + "% until the end of the battle!");
	}
	
	public String toString() {
		return name + " [" + price + " G]\n\tBoosts ALL card effects by " + String.format("%2.0f", 100*(mult-1)) + "% until the end of the battle\n";
	}
	
	public double getMult() {
		return mult;
	}
}
