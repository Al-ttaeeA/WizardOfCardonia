/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package artifact;

public class HandArtifact extends Artifact{
	private final int cards;
	
	public HandArtifact(String name, ArtifactType type, int cards) {
		super(3, name, type);
		this.cards = cards;
	}
	
	public HandArtifact() {
		super();
		this.cards = 0;
	}
	
	public Artifact copy() {
		return new HandArtifact(name, type, cards);
	}
	
	public void use() {
		game.Main.permHand += cards;
	}
	
	public String toString() {
		return name + " [" + type + "]\n\tIncreases cards in hand at the start of your turn by " + cards + " Card(s)\n";
	}
}
