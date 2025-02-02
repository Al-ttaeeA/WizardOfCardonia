package card;

public class BlockCard extends Card{
	private final int block;
	private int newBlock;

    public BlockCard(String name, Rarity rarity, int energyCost, Type type, int block) {
        super(name, rarity, energyCost, type);
        this.block = block;
        this.newBlock = block;
    }

    public void use() {
    	newBlock = game.Commands.skillMultiplier(block, type);
    	
        game.Main.testInt += newBlock;
    }
    
    public String toString() {
    	newBlock = game.Commands.skillMultiplier(block, type);
    	
    	return name + " (" + energyCost + " Energy), Blocks " + newBlock + " damage";
    }

    public int getHeal() {
        return block;
    }
}
