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

package game;

import data.*;

public class Savegame {
	public static void newGame() {
		Main.playMaxHP = 100;
		Main.playCurrentHP = 100;
		Main.gold = 1000; //Change
		Main.battleCount = 1;
		Main.playIsDead = false;
		
		Main.xpLevel = 1;
		Main.currentXp = 0;
		Main.maxXp = 50;
		Main.skillpoints = 0;
		
		Main.intelligence = 1;
		Main.strength = 1;
		Main.arcana = 1;
		Main.corruptedness = 1;
		
		Main.permMult = 1.00;
		Main.permIntMult = 1.00;
		Main.permStrMult = 1.00;
		Main.permCorMult = 1.00;
		Main.permShopSale = 1.00;
		
		Main.permMaxMana = 3;
		Main.permHand = 5;
		
		Main.location = 1;
		
		Data.initiateDeck();
		Data.initiateArtifactList();
		
		
		System.out.println("New Game started!");
		Commands.pressEnter();
		
		System.out.println("Please Enter your username: ");
		Main.playName = Commands.inputString();
		
		story();
	}
	
	public static void loadGame() {
		System.out.println("This is a placeholder currently and there is no previous game sorry!");
		Commands.pressEnter();
		
		newGame();
	}
	
	public static void story() {
		System.out.println("Story goes here");
		Commands.pressEnter();
	}
}
