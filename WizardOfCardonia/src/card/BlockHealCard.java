package card;

import game.Main;

public class BlockHealCard extends Card{
	private final int block;
	private int newBlock;
	private final int heal;
	private int newHeal;
	
	public BlockHealCard(String name, Rarity rarity, int manaCost, Type type, int block, int heal) {
		super(name, rarity, manaCost, type);
		this.block = block;
        this.newBlock = block;
		this.heal = heal;
        this.newHeal = heal;
	}
	
	public BlockHealCard() {
		super();
		this.block = 0;
        this.newBlock = 0;
		this.heal = 0;
        this.newHeal = 0;
	}
	
	public Card copy() {
		return new BlockHealCard(name, rarity, manaCost, type, block, heal);
	}
	
	public boolean use() {
		newBlock = game.Commands.skillMultiplier(block, type);
    	newBlock = (int) (newBlock * game.Battle.battleMult);
    	newBlock = (int) (newBlock * game.Battle.blockMult);
    	
    	newHeal = game.Commands.skillMultiplier(heal, type);
    	newHeal = (int) (newHeal * game.Battle.battleMult);
    	newHeal = (int) (newHeal * game.Battle.healMult);
    	
    	if(doMana()) {
    		game.Battle.currentBlock += newBlock;
    		
    		innerUse();
    		System.out.println("You gain " + newBlock + " Damage block for a total of " + game.Battle.currentBlock + " Damage!\n");
    		
    		game.Main.playCurrentHP += newHeal;
    		
    		if(game.Main.playCurrentHP > game.Main.playMaxHP) game.Main.playCurrentHP = game.Main.playMaxHP;
    		
    		System.out.println("And you heal for " + newHeal + " HP for a total of " + Main.playCurrentHP + " HP!");
    		
    		return true;
    	}
    	
    	return false;
	}
	
	public String toString() {
		newBlock = game.Commands.skillMultiplier(block, type);
    	newBlock = (int) (newBlock * game.Battle.battleMult);
    	newBlock = (int) (newBlock * game.Battle.blockMult);
    	
    	newHeal = game.Commands.skillMultiplier(heal, type);
    	newHeal = (int) (newHeal * game.Battle.battleMult);
    	newHeal = (int) (newHeal * game.Battle.healMult);
    	
    	String str = name + " (" + manaCost + " Mana) [" + rarity + "] {" + type + "}\n\tGain " + newBlock + " block AND Heal " + newHeal + " HP\n";
    	
    	return str;
	}
}
