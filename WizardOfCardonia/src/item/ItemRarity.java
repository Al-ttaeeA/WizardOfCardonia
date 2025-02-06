package item;

public enum ItemRarity {
    COMMON(0, "Common"),
    RARE(1, "Rare"),
    EPIC(3, "Epic");

    private final int index;
    private final String name;

    ItemRarity(int index, String name) {
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