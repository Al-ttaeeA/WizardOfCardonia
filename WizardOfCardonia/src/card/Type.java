package card;

public enum Type {
	PHYSICAL(0, "Physicsl"),
	MAGICAL(1, "Magical"),
	CURSED(2, "Cursed");
	
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
