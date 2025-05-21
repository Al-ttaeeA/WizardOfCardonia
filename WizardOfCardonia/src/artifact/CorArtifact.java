/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package artifact;

public class CorArtifact extends Artifact{
	private final double mult;
	
	public CorArtifact(String name, ArtifactType type, double mult) {
		super(2, name, type);
		this.mult = mult;
	}
	
	public CorArtifact() {
		super();
		this.mult = 1.00;
	}
	
	public Artifact copy() {
		return new CorArtifact(name, type, mult);
	}
	
	public void use() {
		game.Main.permCorMult *= mult;
	}
	
	public String toString() {
		return name + " [" + type + "]\n\tBoosts CORRUPT card effects by " + String.format("%2.0f", 100*(mult-1)) + "% permanently\n";
	}
	
	public double getMult() {
		return mult;
	}
}
