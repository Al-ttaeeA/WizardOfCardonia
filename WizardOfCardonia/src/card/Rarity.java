package card;

public enum Rarity {
    COMMON(0),
    UNCOMMON(1),
    RARE(2),
    EPIC(3),
    LEGENDARY(4);

    private final int index;

    Rarity(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}