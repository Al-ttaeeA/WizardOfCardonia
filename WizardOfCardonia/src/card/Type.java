/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package card;

public enum Type {
	PHYSICAL(0, "PHYSICAL"),
	MAGICAL(1, "MAGICAL"),
	CORRUPT(2, "CORRUPT");
	
	private final int index;
	private final String name;
	
    Type(int index, String name) {
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
