package card;

public class DrawCard extends Card{
	private final int cardCount;
	
	public DrawCard(String name, Rarity rarity, int manaCost, Type type, int cardCount) {
		super(5, name, rarity, manaCost, type);
		this.cardCount = cardCount;
	}
	
	public DrawCard() {
		super();
		this.cardCount = 0;
	}
	
	public Card copy() {
		return new DrawCard(name, rarity, manaCost, type, cardCount);
	}
	
	public boolean use() {
		if(doMana()) {
			innerUse();
			
			if(!game.Battle.draw()) {
				return false;
			}
			else {
				System.out.println("You draw " + cardCount + " card(s) to your hand!");
			}
			
    		return true;
    	}
    	
    	return false;
	}
	
	public String toString() {
		return name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tDraw " + cardCount + " card(s)\n";
	}
}
