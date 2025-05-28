/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package artifact;

public class StrArtifact extends Artifact{
	private final double mult;
	
	public StrArtifact(String name, ArtifactType type, double mult) {
		super(7, name, type);
		this.mult = mult;
	}
	
	public StrArtifact() {
		super();
		this.mult = 1.00;
	}
	
	public Artifact copy() {
		return new StrArtifact(name, type, mult);
	}
	
	public void use() {
		game.Main.permStrMult *= mult;
	}
	
	public String toString() {
		return name + " [" + type + "]\n\tBoosts PHYSICAL card effects by " + String.format("%2.0f", 100*(mult-1)) + "% permanently\n";
	}
	
	public double getMult() {
		return mult;
	}
	
	public String save() {
		String str = this.id + ",";
		str += this.name + ",";
		str += this.type + ",";
		
		str += this.mult;
		
		return str;
	}
}
