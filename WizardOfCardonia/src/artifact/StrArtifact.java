package artifact;

public class StrArtifact extends Artifact{
	private final double mult;
	
	public StrArtifact(String name, ArtifactType type, double mult) {
		super(name, type);
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
}
