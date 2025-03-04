package artifact;

public class AllArtifact extends Artifact{
	private final double mult;
	
	public AllArtifact(String name, ArtifactType type, double mult) {
		super(name, type);
		this.mult = mult;
	}
	
	public AllArtifact() {
		super();
		this.mult = 1.00;
	}
	
	public Artifact copy() {
		return new AllArtifact(name, type, mult);
	}
	
	public void use() {
		game.Main.permMult *= mult;
	}
	
	public String toString() {
		return name + " [" + type + "]\n\tBoosts ALL card effects by " + String.format("%2.0f", 100*(mult-1)) + "% permanently\n";
	}
	
	public double getMult() {
		return mult;
	}
}
