package card;

public class HealCard extends Card {
    private final int heal;
    private int newHeal;

    public HealCard(String name, Rarity rarity, int energyCost, Type type, int heal) {
        super(name, rarity, energyCost, type);
        this.heal = heal;
        this.newHeal = heal;
    }

    public void use() {
    	newHeal = game.Commands.skillMultiplier(heal, type);
    	
        game.Main.testInt += newHeal;
    }
    
    public String toString() {
    	newHeal = game.Commands.skillMultiplier(heal, type);
    	
    	return name + " (" + energyCost + " Energy), Heals " + newHeal + " HP"; 
    }

    public int getHeal() {
        return heal;
    }
}
