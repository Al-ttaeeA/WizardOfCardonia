/***********************************************************************
 * Wizard of Cardonia
 * Copyright (C) 2025 A2Z Studios
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
		Main.battleCount = 14;
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
		
		System.out.print("\nPlease Enter your username: ");
		Main.playName = Commands.inputString();
		
		// story();
	}
	
	public static void loadGame() {
		System.out.println("This is a placeholder currently and there is no previous game sorry!");
		Commands.pressEnter();
		
		newGame();
	}
	
	public static void story() {
		String text = """
	            Long ago, the land of Cardonia pulsed with ancient magic—wild, untamed,
	            and unreachable by human hands. From enchanted forests to monstrous
	            beasts, the world was a beautiful yet dangerous place.

	            For centuries, humanity banded together, struggling to survive against
	            nature’s magical fury. Until one fateful day, everything changed.

	            Three visionaries —Plensor, Mejashi, and Cruden— discovered a way to wield
	            magic through enchanted cards and relics. With this newfound power, they
	            taught others, built great schools of magic, and brought hope to all.

	            But magic has a price.

	            The very power they harnessed began to twist and corrupt. Their creations
	            turned dark. The great mages, once heroes, were consumed—now shadows of
	            their former selves, spreading ruin across the land.

	            Now, only a few untainted mages remain. Armed with the very cards forged
	            by their fallen mentors, they embark on a final journey...

	            To stop the corruption.
	            To restore Cardonia.
	            To become the next legend.
	            """;
		
		int delayMillis = 20;
		
		for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(c == '.' || c == '\n' ? delayMillis * 10 : delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
		
		Commands.pressEnter();
		
		System.out.println("""
				You start your adventure as one of the few surviving mages!!!
				""");
	}
}
