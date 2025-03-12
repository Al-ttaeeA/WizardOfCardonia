package item;

public class BlockItem extends Item{
	private final double blockBuff;
	
	public BlockItem(String name, ItemRarity rarity, int price, double blockBuff) {
		super(name, rarity, price);
		this.blockBuff = blockBuff;
	}
	
	public BlockItem() {
		super();
		this.blockBuff = 1;
	}
	
	public Item copy() {
		return new BlockItem(name, rarity, price, blockBuff);
	}
	
	public void use() {
		game.Battle.blockMult *= blockBuff;
		System.out.println("You increase ALL gained block by " + String.format("%2.0f", 100*(blockBuff-1)) + "% until the end of the battle!");
	}
	
	public String toString() {
		return name + " [" + price + " G]\n\tBoosts ALL gained block by " + String.format("%2.0f", 100*(blockBuff-1)) + "% until the end of the battle\n";
	}
	
	public double getBlockBuff() {
		return blockBuff;
	}
}
