/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package card;

public enum Rarity {
    COMMON(0, "Common"),
    UNCOMMON(1, "Uncommon"),
    RARE(2, "Rare"),
    EPIC(3, "Epic"),
    LEGENDARY(4, "Legendary");

    private final int index;
    private final String name;

    Rarity(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }
    
    public String toString() {
    	return name;
    }
}