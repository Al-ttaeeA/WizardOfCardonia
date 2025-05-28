/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package card;

public enum Rarity {
    COMMON(0, "COMMON"),
    UNCOMMON(1, "UNCOMMON"),
    RARE(2, "RARE"),
    EPIC(3, "EPIC"),
    LEGENDARY(4, "LEGENDARY");

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