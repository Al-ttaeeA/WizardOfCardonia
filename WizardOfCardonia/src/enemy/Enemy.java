/***********************************************************************
 * Wizard of Cardonia
 * Copyright (C) 2025 Dr4Go5 Developers
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 ***********************************************************************/

package enemy;

import game.Commands;

public abstract class Enemy {
    protected final String name;
    protected int maxHealth;
    protected int health;
    protected int block;
    protected int blockAmount;
    protected int damageConstant;
    protected int damageVariable;
    protected final double specialChance;
    
    //Status effects
    protected boolean poison = false;
    protected boolean burn = false;
    protected boolean injured = false;
    
    private double damageModifier = 1;
    

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
    
    public abstract Enemy copy();
    
    public abstract void initialize();

    public abstract void attack();
    
    protected void innerAttack() {
    	int damage;
    	
    	if(poison) {
    		damage = Commands.skillMultiplier(4, card.Type.CORRUPT);
    		
    		takeDamage(damage);
    		
    		System.out.println("The enemy was poisoned and took " + damage + " Damage to poison!\n");
    	}
    	
    	if(burn) {
    		damage = Commands.skillMultiplier(5, card.Type.MAGICAL);
    		
    		takeDamage(damage);
    		
    		System.out.println("The enemy was burning and took " + damage + " Damage to burning!\n");
    		
    		if(Commands.getRandomChance() < 0.40) {
    			burn = false;
    			
    			System.out.println("The enemy also stopped burning!\n");
    		}
    	}
    	
    	if(injured) {
    		damageModifier = 0.75;
    		System.out.println("The enemy is injured so their damage is reduced by 25%!\n");
    	}
    	else {
    		damageModifier = 1;
    	}
    }
    
    public abstract String toString();
    
    public String printStats() {
    	String str = name + 
    			"\n\nHealth: " + health + " HP/ " + maxHealth + " HP" +
    			"\n\nBlock: " + block + " Damage" +
    			"\n\nDamage: " + getMinDamage() + " - " + getMaxDamage() + " Damage";
    	
    	return str;
    }
    
    public int getDamage() {
    	return (int) ((damageConstant + game.Commands.getRandomInt(damageVariable)) * game.Battle.enemyDamageMult * damageModifier);
    }
    
    public int getMinDamage() {
    	return damageConstant+1;
    }
    
    public int getMaxDamage() {
    	return damageConstant + damageVariable;
    }
    
    public void takeDamage(int damage) {
    	int diff = this.block - damage;
    	
    	if(diff < 0) {
    		this.block = 0;
    		this.health += diff;
    	}
    	else {
    		this.block = diff;
    	}
    	
    	if(this.health < 0) {
    		this.health = 0;
    	}
    }
    
    public void setPoison() {
    	poison = true;
    }
    
    public void setBurn() {
    	burn = true;
    }
    
    public void setInjured() {
    	injured = true;
    }
    
    public String getEffects() {
    	String str = "";
    	
    	if(injured) {
    		str += "[Injured] ";
    	}
    	if(burn) {
    		str += "[Burned] ";
    	}
    	if(poison) {
    		str += "[Poisoned]";
    	}
    	
    	return str;
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
