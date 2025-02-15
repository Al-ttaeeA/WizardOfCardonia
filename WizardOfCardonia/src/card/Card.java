package card;

public abstract class Card {
    protected final String name;
    protected final Rarity rarity;
    protected final int manaCost;
    protected final Type type;

    public Card(String name, Rarity rarity, int manaCost, Type type) {
        this.name = name;
        this.rarity = rarity;
        this.manaCost = manaCost;
        this.type = type;
    }
    
    public Card() {
    	this.name = "Empty Card";
    	this.rarity = Rarity.COMMON;
    	this.manaCost = 0;
    	this.type = Type.PHYSICAL;
    }
    
    public abstract Card copy();

    public abstract void use();
    
    public abstract String toString();

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getManaCost() {
        return manaCost;
    }

    public Type getType() {
        return type;
    }
}