package game;

import card.*;
import item.*;

public class Main {
	public static int testInt;
	public static double testDouble;
	public static int testIntelligence;
	public static int testStrength;
	public static int testArcana;
	public static int testCorruptness;
	
	public static void main(String[] args) {
		Card dmgTest = new DamageCard();
		Card healTest = new HealCard();
		Card blockTest = new BlockCard();
		Card weaknessTest = new WeaknessCard();
		Card manaTest = new ManaCard();
		
		Item damageTest = new DamageItem();
		Item blckTest = new BlockItem();
		
		System.out.println("Hello World");
		
		Commands.inputInt(1, 2);
		
		System.out.println("Hello World 2");
		
		Commands.pressEnter();
		
		System.out.println(dmgTest);
		System.out.println(healTest);
		System.out.println(blockTest);
		System.out.println(weaknessTest);
		System.out.println(manaTest);
		
		Commands.pressEnter();
		
		System.out.println(damageTest);
		System.out.println(blckTest);
		
		Commands.pressEnter();
	}
}