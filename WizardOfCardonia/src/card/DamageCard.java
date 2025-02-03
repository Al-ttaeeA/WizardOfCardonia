package card;

public class DamageCard extends Card {
    private final int damage;
    private int newDamage;

    public DamageCard(String name, Rarity rarity, int manaCost, Type type, int damage) {
        super(name, rarity, manaCost, type);
        this.damage = damage;
        this.newDamage = damage;
    }
    
    public DamageCard() {
    	super();
    	this.damage = 0;
    	this.newDamage = 0;
    }

    public void use() {
    	newDamage = game.Commands.skillMultiplier(damage, type);
    	
        game.Main.testInt -= newDamage;
    }
    
    public String toString() {
    	newDamage = game.Commands.skillMultiplier(damage, type);
    	
    	return name + " (" + manaCost + " Mana) [" + rarity + "]\n\tDeal " + newDamage + " damage\n";
    }

    public int getDamage(int num) {
        if(num == 0) {
        	return damage;
        }
        else {
        	return newDamage;
        }
    }
}
