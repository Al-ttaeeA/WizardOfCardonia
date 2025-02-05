package game;

import data.*;
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
		Card test;
		int common=0, uncommon=0, rare=0, epic=0, legendary=0;
		
		for(int i = 0; i < 10000; i++) {
			test = Data.getRandomCard();
			
			if(test.getRarity() == Rarity.COMMON) {
				common++;
			}
			else if(test.getRarity() == Rarity.UNCOMMON) {
				uncommon++;
			}
			else if(test.getRarity() == Rarity.RARE) {
				rare++;
			}
			else if(test.getRarity() == Rarity.EPIC) {
				epic++;
			}
			else {
				legendary++;
			}
		}
		
		System.out.println(common + "   " + uncommon + "   " + rare + "   " + epic + "   " + legendary);
	}
}