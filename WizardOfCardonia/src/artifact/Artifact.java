package artifact;

public abstract class Artifact {
	protected final String name;
	protected final ArtifactType type;
	
	public Artifact(String name, ArtifactType type) {
		this.name = name;
		this.type = type;
	}
	
	public abstract Artifact copy();
	
	public abstract void use();
	
	public abstract String toString();
	
	public String getName() {
		return name;
	}
	
	public ArtifactType getType() {
		return type;
	}
	
	public int getPrice() {
		if(type == ArtifactType.COMMON) {
			return 100;
		}
		else {
			return 150;
		}
	}
}
