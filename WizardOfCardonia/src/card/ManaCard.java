package card;

public class ManaCard extends Card{
	private final int mana;
	
	public ManaCard(String name, Rarity rarity, int manaCost, Type type, int mana) {
		super(name, rarity, manaCost, type);
		this.mana = mana;
	}
	
	public ManaCard() {
		super();
		this.mana = 0;
	}
	
	public Card copy() {
		return new ManaCard(name, rarity, manaCost, type, mana);
	}
	
	public void use() {
		game.Battle.currentMana += mana;
	}
	
	public String toString() {
		return name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tGain " + mana + " mana\n";
	}
}
