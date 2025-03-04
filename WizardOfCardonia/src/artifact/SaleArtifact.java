package artifact;

public class SaleArtifact extends Artifact{
	private final double mult;
	
	public SaleArtifact(String name, ArtifactType type, double mult) {
		super(name, type);
		this.mult = mult;
	}
	
	public SaleArtifact() {
		super();
		this.mult = 1.00;
	}
	
	public Artifact copy() {
		return new SaleArtifact(name, type, mult);
	}
	
	public void use() {
		game.Main.permShopSale *= mult;
	}
	
	public String toString() {
		return name + " [" + type + "]\n\tCards and Items the merchant offers become " + String.format("%2.0f", 100*(1-mult)) + "% cheaper after this merchant\n";
	}
	
	public double getMult() {
		return mult;
	}
}
