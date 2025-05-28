/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package item;

public class BlockItem extends Item{
	private final double mult;
	
	public BlockItem(String name, ItemRarity rarity, int price, double mult) {
		super(2, name, rarity, price);
		this.mult = mult;
	}
	
	public BlockItem() {
		super();
		this.mult = 1;
	}
	
	public Item copy() {
		return new BlockItem(name, rarity, price, mult);
	}
	
	public void use() {
		game.Battle.blockMult *= mult;
		System.out.println("You increase ALL gained block by " + String.format("%2.0f", 100*(mult-1)) + "% until the end of the battle!");
	}
	
	public String toString() {
		return name + " [" + price + " G]\n\tBoosts ALL gained block by " + String.format("%2.0f", 100*(mult-1)) + "% until the end of the battle\n";
	}
	
	public double getMult() {
		return mult;
	}
	
	public String save() {
		String str = this.id + ",";
		str += this.name + ",";
		str += this.rarity + ",";
		str += this.price + ",";
		
		str += this.mult;
		
		return str;
	}
}
