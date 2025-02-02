package card;

public abstract class Card {
    protected final String name;
    protected final Rarity rarity;
    protected final int energyCost;
    protected final Type type;

    public Card(String name, Rarity rarity, int energyCost, Type type) {
        this.name = name;
        this.rarity = rarity;
        this.energyCost = energyCost;
        this.type = type;
    }

    public abstract void use();
    
    public abstract String toString();

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public Type getType() {
        return type;
    }
}