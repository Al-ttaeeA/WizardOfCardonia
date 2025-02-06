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
		return name + " [" + price + " G]\n\tBoosts ALL gained block by " + String.format("%2.0f", 100*(blockBuff-1)) + "% until the end of the battle\n";
	}
	
	public double getBlockBuff() {
		return blockBuff;
	}
}
