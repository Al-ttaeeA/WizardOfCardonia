package card;

public enum Type {
	PHYSICAL(0),
	MAGICAL(1),
	CURSED(2);
	
	private final int index;

    Type(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
