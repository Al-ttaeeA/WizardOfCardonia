package card;

public class DamageCard extends Card {
    private final int damage;
    private int newDamage;

    public DamageCard(String name, Rarity rarity, int energyCost, Type type, int damage) {
        super(name, rarity, energyCost, type);
        this.damage = damage;
        this.newDamage = damage;
    }

    public void use() {
    	newDamage = game.Commands.skillMultiplier(damage, type);
    	
        game.Main.testInt -= newDamage;
    }
    
    public String toString() {
    	newDamage = game.Commands.skillMultiplier(damage, type);
    	
    	return name + " (" + energyCost + " Energy), Deals " + newDamage + " damage";
    }

    public int getDamage() {
        return damage;
    }
}
