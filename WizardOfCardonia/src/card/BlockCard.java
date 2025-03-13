/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package card;

public class BlockCard extends Card{
	private final int block;
	private int newBlock;

    public BlockCard(String name, Rarity rarity, int manaCost, Type type, int block) {
        super(name, rarity, manaCost, type);
        this.block = block;
        this.newBlock = block;
    }
    
    public BlockCard() {
    	super();
    	this.block = 0;
    	this.newBlock = 0;
    }
    
    public Card copy() {
    	return new BlockCard(name, rarity, manaCost, type, block);
    }

    public boolean use() {
    	newBlock = game.Commands.skillMultiplier(block, type);
    	newBlock = (int) (newBlock * game.Battle.battleMult);
    	newBlock = (int) (newBlock * game.Battle.blockMult);
    	
    	if(doMana()) {
    		game.Battle.currentBlock += newBlock;
    		
    		innerUse();
    		System.out.println("You gain " + newBlock + " Damage block for a total of " + game.Battle.currentBlock + " Damage!");
    		
    		return true;
    	}
    	
    	return false;
    }
    
    public String toString() {
    	newBlock = game.Commands.skillMultiplier(block, type);
    	newBlock = (int) (newBlock * game.Battle.battleMult);
    	newBlock = (int) (newBlock * game.Battle.blockMult);
    	
    	return name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tGain " + newBlock + " block\n";
    }

    public int getHeal(int num) {
    	if(num == 0) {
        	return block;
        }
        else {
        	return newBlock;
        }
    }
}
