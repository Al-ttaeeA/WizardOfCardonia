/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package item;

public enum ItemRarity {
    COMMON(0, "Common"),
    RARE(1, "Rare"),
    EPIC(2, "Epic");

    private final int index;
    private final String name;

    ItemRarity(int index, String name) {
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