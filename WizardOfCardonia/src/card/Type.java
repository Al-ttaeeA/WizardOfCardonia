package card;

public enum Type {
	PHYSICAL(0, "Physical"),
	MAGICAL(1, "Magical"),
	CORRUPT(2, "Corrupt");
	
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
