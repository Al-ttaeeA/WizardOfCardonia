/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package artifact;

public enum ArtifactType {
	COMMON(0, "Common"),
    ANCIENT(1, "Ancient");

    private final int index;
    private final String name;

    ArtifactType(int index, String name) {
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
