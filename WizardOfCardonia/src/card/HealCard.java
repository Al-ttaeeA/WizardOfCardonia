package card;

public class HealCard extends Card {
    private final int heal;
    private int newHeal;

    public HealCard(String name, Rarity rarity, int manaCost, Type type, int heal) {
        super(name, rarity, manaCost, type);
        this.heal = heal;
        this.newHeal = heal;
    }
    
    public HealCard() {
    	super();
    	this.heal = 0;
    	this.newHeal = 0;
    }
    
    public Card copy() {
    	return new HealCard(name, rarity, manaCost, type, heal);
    }

    public void use() {
    	newHeal = game.Commands.skillMultiplier(heal, type);
    	
        game.Main.testInt += newHeal;
    }
    
    public String toString() {
    	newHeal = game.Commands.skillMultiplier(heal, type);
    	
    	return name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tHeal " + newHeal + " HP\n"; 
    }

    public int getHeal(int num) {
    	if(num == 0) {
        	return heal;
        }
        else {
        	return newHeal;
        }
    }
}
