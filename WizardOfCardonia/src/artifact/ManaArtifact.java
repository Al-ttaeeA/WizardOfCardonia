/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package artifact;

public class ManaArtifact extends Artifact{
	private final int mana;
	
	public ManaArtifact(String name, ArtifactType type, int mana) {
		super(5, name, type);
		this.mana = mana;
	}
	
	public ManaArtifact() {
		super();
		this.mana = 0;
	}
	
	public Artifact copy() {
		return new ManaArtifact(name, type, mana);
	}
	
	public void use() {
		game.Main.permMaxMana += mana;
	}
	
	public String toString() {
		return name + " [" + type + "]\n\tIncreases Max Mana by " + mana + "MP permanently\n";
	}
	
	public String save() {
		String str = this.id + ",";
		str += this.name + ",";
		str += this.type + ",";
		
		str += this.mana;
		
		return str;
	}
}
