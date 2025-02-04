package enemy;

public abstract class Enemy {
    protected final String name;
    protected final int maxHealth;
    protected int health;
    protected int block;
    protected final int blockAmount;
    protected int damageConstant;
    protected int damageVariable;
    protected final double specialChance;

    public Enemy(String name, int health, int block, int blockAmount, int damageConstant, int damageVariable, double specialChance) {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.block = block;
        this.blockAmount = blockAmount;
        this.damageConstant = damageConstant;
        this.damageVariable = damageVariable;
        this.specialChance = specialChance;
    }
    
    public Enemy() {
    	this.name = "Empty Enemy";
    	this.maxHealth = 0;
    	this.health = 0;
    	this.block = 0;
    	this.blockAmount = 0;
    	this.damageConstant = 0;
    	this.damageVariable = 0;
    	this.specialChance = 0;
    }

    public abstract void attack();
    
    public String printStats() {
    	String str = name + 
    			"\n\nHealth: " + health + " HP/ " + maxHealth + " HP" +
    			"\n\nBlock: " + block + " Damage" +
    			"\n\nDamage: " + getMinDamage() + " - " + getMaxDamage() + " Damage";
    	
    	return str;
    }
    
    public int getDamage() {
    	return (int) ((damageConstant + game.Commands.getRandomInt(damageVariable)));
    }
    
    public int getMinDamage() {
    	return damageConstant+1;
    }
    
    public int getMaxDamage() {
    	return damageConstant + damageVariable;
    }
    
    public void takeDamage(int damage) {
    	health -= damage;
    }
    
    public String getName() {
        return name;
    }
    
    public int getMaxHealth() {
    	return maxHealth;
    }

    public int getHealth() {
        return health;
    }
    
    public int getBlock() {
    	return block;
    }
    
    public int getBlockAmount() {
    	return blockAmount;
    }

    public int getDamageConstant() {
        return damageConstant;
    }

    public int getDamageMultiplier() {
        return damageVariable;
    }

    public double getSpecialChance() {
        return specialChance;
    }
}
