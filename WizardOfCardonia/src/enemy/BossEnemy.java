/*****************************************************
 * Part of Wizard of Cardonia
 * Licensed under the GNU General Public License v3.0
 * See LICENSE file for details.
 *****************************************************/

package enemy;

import card.Type;

import game.Main;
import game.Commands;
import game.Battle;

public class BossEnemy extends Enemy{
	private int healAmount;
	private Type resilience;
	
	public BossEnemy(String name, int health, int block, int blockAmount, int damageConstant, int damageVariable, int healAmount, Type resilience) {
		super(name, health, block, blockAmount, damageConstant, damageVariable, 0);
		this.healAmount = healAmount;
		this.resilience = resilience;
	}
	
	public BossEnemy() {
		super();
		this.healAmount = 0;
		this.resilience = Type.PHYSICAL;
	}
	
	public Enemy copy() {
		return new BossEnemy(name, health, block, blockAmount, damageConstant, damageVariable, healAmount, resilience);
	}
	
	public void initialize() {
		double bossDiff = Math.pow(1.20, Battle.battleDifficulty);
		
		maxHealth *= bossDiff;
		health *= bossDiff;
		block *= bossDiff;
		blockAmount *= bossDiff;
		damageConstant *= bossDiff;
		damageVariable *= bossDiff;
		healAmount *= bossDiff;
	}
	
	public void action() {
		innerAction();
		
		if(health <= 0) {
			return;
		}
		
		int totalWeight, heal = 0, attack = 0, blockW = 0, attackAndBlock = 0, healAndBlock = 0, attackAndHeal = 0;
		int randomChoice;
		
		//Checking health percentage
		if(this.health <= this.maxHealth / 5) {
			heal += 5;
			healAndBlock += 5;
			attackAndHeal += 5;
		}
		else if(this.health <= this.maxHealth / 4) {
			heal += 4;
			healAndBlock += 4;
			attackAndHeal += 4;
		}
		else if(this.health <= this.maxHealth / 3) {
			heal += 3;
			healAndBlock += 3;
			attackAndHeal += 3;
		}
		else if(this.health <= this.maxHealth / 2) {
			heal += 2;
			healAndBlock += 2;
			attackAndHeal += 2;
		}
		else {
			heal += 1;
			healAndBlock += 1;
			attackAndHeal += 1;
		}
		
		
		//Checking player health percentage
		if(Main.playCurrentHP >= Main.playMaxHP * 0.8) {
			attack += 6;
			attackAndBlock += 6;
			attackAndHeal += 6;
		}
		else if(Main.playCurrentHP >= Main.playMaxHP / 2) {
			attack += 4;
			attackAndBlock += 4;
			attackAndHeal += 4;
		}
		else if(Main.playCurrentHP >= Main.playMaxHP / 4) {
			attack += 2;
			attackAndBlock += 2;
			attackAndHeal += 2;
		}
		else {
			attack += 4;
			attackAndBlock += 4;
			attackAndHeal += 4;
		}
		
		
		//Checking for blocks
		if(this.block == 0) {
			blockW += 3;
			attackAndBlock += 3;
			healAndBlock += 3;
		}
		else if(this.block <= this.maxHealth / 10) {
			blockW += 2;
			attackAndBlock += 2;
			healAndBlock += 2;
		}
		else {
			blockW += 1;
			attackAndBlock += 1;
			healAndBlock += 1;
		}
		
		
		//If health is close to max, eliminate the possibility that healing occurs
		if(this.health >= this.maxHealth * 0.8 && health >= (maxHealth-healAmount)) {
			heal = 0;
			healAndBlock = 0;
			attackAndHeal = 0;
		}
		
		
		totalWeight = heal + attack + blockW + healAndBlock + attackAndBlock + attackAndHeal;
		
		randomChoice = Commands.getRandomInt(totalWeight);
		
		//If the choice is heal
		if(randomChoice <= heal) {
			heal();
			
			return;
		}
		else {
			randomChoice -= heal;
		}
		
		//If the choice is to attack
		if(randomChoice <= attack) {
			attack();
			
			return;
		}
		else {
			randomChoice -= attack;
		}
		
		//If the choice is to block
		if(randomChoice <= blockW) {
			block();
			
			return;
		}
		else {
			randomChoice -= blockW;
		}
		
		//If the choice is to heal and block
		if(randomChoice <= healAndBlock) {
			healAndBlock();
		
			return;
		}
		else {
			randomChoice -= healAndBlock;
		}
		
		//If the choice is to attack and block
		if(randomChoice <= attackAndBlock) {
			attackAndBlock();
			
			return;
		}
		else {
			randomChoice -= attackAndBlock;
		}
		
		//If the choice is to attack and heal
		attackAndHeal();
	}
	
	private void heal() {
		health += healAmount;
		System.out.println("The enemy heals for " + healAmount + " HP for a total of " + health + " HP!");
	}
	
	private void attack() {
		int damage = this.getDamage();
		int diff = game.Battle.currentBlock - damage;
		
		if(diff < 0) {
			game.Battle.currentBlock = 0;
			game.Main.playCurrentHP += diff;
			
			if(game.Main.playCurrentHP <= 0) {
				game.Main.playCurrentHP = 0;
			}
			
			System.out.println("The enemy attacks you for " + damage + ", going through all of your block and leaving you with " + game.Main.playCurrentHP + " HP!");
		}
		else {
			game.Battle.currentBlock = diff;
			System.out.println("The enemy attacks you for " + damage + ", you fully block the attack!");
		}
	}
	
	private void block() {
		block += blockAmount;
		System.out.println("The enemy increases their block for a total of " + block + " Damage block!");
	}
	
	private void healAndBlock() {
		health += healAmount;
		System.out.println("The enemy heals for " + healAmount + " HP for a total of " + health + " HP!\n");
		
		block += blockAmount;
		System.out.println("The enemy also increases their block for a total of " + block + " Damage block!");
	}
	
	private void attackAndBlock() {
		int damage = this.getDamage();
		int diff = game.Battle.currentBlock - damage;
		
		if(diff < 0) {
			game.Battle.currentBlock = 0;
			game.Main.playCurrentHP += diff;
			System.out.println("The enemy attacks you for " + damage + ", going through all of your block and leaving you with " + game.Main.playCurrentHP + " HP!\n");
		}
		else {
			game.Battle.currentBlock = diff;
			System.out.println("The enemy attacks you for " + damage + ", you fully block the attack!\n");
		}
		
		block += blockAmount;
		System.out.println("The enemy also increases their block for a total of " + block + " Damage block!");
	}
	
	private void attackAndHeal() {
		int damage = this.getDamage();
		int diff = game.Battle.currentBlock - damage;
		
		if(diff < 0) {
			game.Battle.currentBlock = 0;
			game.Main.playCurrentHP += diff;
			System.out.println("The enemy attacks you for " + damage + ", going through all of your block and leaving you with " + game.Main.playCurrentHP + " HP!\n");
		}
		else {
			game.Battle.currentBlock = diff;
			System.out.println("The enemy attacks you for " + damage + ", you fully block the attack!\n");
		}
		
		health += healAmount;
		System.out.println("The enemy also heals for " + healAmount + " HP for a total of " + health + " HP!");
	}
	
	public String toString() {
		String str = name + 
				"\n   Base Max Health: " + maxHealth + " HP" +
				"\n   Starting block:  " + block + " Damage" + 
				"\n   Base Damage:     " + getMinDamage() + " - " + getMaxDamage() + " Damage" + 
				"\n   This is a BOSS, and can deal damage, block, heal, and deal damage and block at the same time";
		
		return str;
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
	
	public Type getResilience() {
		return this.resilience;
	}
}
