package game;

import data.*;
import card.*;
import item.*;
import enemy.*;
import java.util.*;

public class Main {
	public static int testInt;
	public static double testDouble;
	public static int testIntelligence;
	public static int testStrength;
	public static int testArcana;
	public static int testCorruptness;
	
	public static int location;
	
	public static void main(String[] args) {
		Commands.clearScreen();
		
		int choice;
		
		System.out.println("1 for cards, 2 for enemies, 3 for items");
		choice = Commands.inputInt(1, 3);
		
		switch(choice) {
		case 1:{
			Data.displayCards();
			break;
		}
		case 2:{
			Data.displayEnemies();
			break;
		}
		case 3:{
			Data.displayItems();
			break;
		}
		}
		
	}
}

