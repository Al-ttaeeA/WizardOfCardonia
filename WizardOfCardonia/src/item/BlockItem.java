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
	
	public void use() {
		game.Main.testDouble *= blockBuff;
	}
	
	public String toString() {
		return name + " [" + price + " G]\n\tBoosts ALL gained block by " + (blockBuff-1) + "% until the end of the battle";
	}
	
	public double getBlockBuff() {
		return blockBuff;
	}
}
