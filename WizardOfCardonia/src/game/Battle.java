package game;

import data.*;
import card.*;
import item.*;
import enemy.*;
import java.util.*;

public class Battle {
	private static int currentMana, maxMana;
	
	private static double battleMult;
	
	public static double battleDifficulty;
	private static int battleGold;
	private static int battleCardCount;
	
	private static ArrayList<Card> tempDeck = new ArrayList<>();
	
	private static Enemy currentEnemy;
	
	private static void iniateTempDeck() {
		for(int i = 0; i < Main.deck.size(); i++) {
			tempDeck.add(Main.deck.get(i).copy());
		}
	}
	
	public static void Battle(){
		iniateTempDeck();
		
		currentMana = 3;
		maxMana = 3;
		
		battleMult = 1.00;
		
		switch(Main.location) {
		case 1:{
			battleDifficulty = Commands.getRandomDouble(1.00, 1.20);
			
			if(Commands.getRandomChance() < 0.80) battleCardCount = 1;
			else battleCardCount = 2;
			
			break;
		}
		case 2:{
			battleDifficulty = Commands.getRandomDouble(1.20, 2.00);
			
			if(Commands.getRandomChance() < 0.50) battleCardCount = 1;
			else if(Commands.getRandomChance() < 0.60) battleCardCount = 2;
			else battleCardCount = 3;
			
			break;
		}
		case 3:{
			battleDifficulty = Commands.getRandomDouble(2.00, 3.50);
			
			if(Commands.getRandomChance() < 0.50) battleCardCount = 2;
			else if(Commands.getRandomChance() < 0.25) battleCardCount = 3;
			else battleCardCount = 4;
			
			break;
		}
		}
		battleGold = (int) (battleDifficulty * 100);
		
		currentEnemy = Data.getEnemy();
		currentEnemy.initialize();
		
		System.out.println("Battle runs");
		
		Main.playCurrentHP = 0;
		
		Commands.pressEnter();
	}
}
