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
import java.io.*;
import java.util.*;

import card.Card;
import item.Item;
import artifact.Artifact;

public class Savegame {
	private static File mainFolder;
	private static File mainFile;
	private static File[] savefiles = new File[5];
	
	public static int activeSave;
	
	private static ArrayList<SaveSlot> saves = new ArrayList<SaveSlot>();
	
	
	
	public static void initiate() {
		try {
            String userHome = System.getProperty("user.home");
            String saveFolderPath = userHome + File.separator + "Documents" + File.separator + "WizardOfCardonia";

            mainFolder = new File(saveFolderPath);
            if (!mainFolder.exists()) {
                mainFolder.mkdirs();
            }
            
            mainFile = new File(saveFolderPath, "savegameInfo.txt");
            if(!mainFile.exists()) {
            	mainFile.createNewFile();
            	
            	PrintWriter pw = new PrintWriter(mainFile);
            	
            	for(int i = 0; i < 5; i++) {
            		pw.println("Empty,0,0");
            	}
            	
            	pw.close();
            }

            for(int i = 0; i < 5; i++) {
            	savefiles[i] = new File(mainFolder, "savegame" + (i+1) + ".txt");
            	
            	if(!savefiles[i].exists()) {
            		savefiles[i].createNewFile();
            	}
            }
            
            Scanner scanFile = new Scanner(mainFile);
            
            while(scanFile.hasNextLine()) {
            	String line = scanFile.nextLine();
            	String[] tokens = line.split(",");
            	
            	saves.add(new SaveSlot(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
            }
            
            scanFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static boolean newGame() {
		boolean isValid = false;
		int choice;
		int innerChoice;
		
		do {
			System.out.println("Save Slots:\n");
			printSaves();
			System.out.println("Choose a save slot to overwrite or 0 to exit");
			choice = Commands.inputInt(0, 5);
			
			if(choice == 0) {
				return false;
			}
			
			if(!saves.get(choice-1).isEmpty()) {
				System.out.println("Are you sure you want to overwrite this slot?\n");
				System.out.println("This save data will be lost forever\n");
				System.out.println("1. Go back\n\n2. Overwrite");
				innerChoice = Commands.inputInt(1, 2);
				
				if(innerChoice == 2) {
					isValid = true;
				}
			}
			else {
				isValid = true;
			}
		} while(!isValid);
		
		
		
		activeSave = choice;
		
		Main.playMaxHP = 100;
		Main.playCurrentHP = 100;
		Main.gold = 0; //Change
		Main.battleCount = 0;
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
		
		Main.deck.clear();
		Main.inventory.clear();
		Main.artifacts.clear();
		
		Data.initiateDeck();
		Data.initiateArtifactList();
		
		for(int i = 0; i < 3; i++) {
			Main.inventory.add(Data.getItem());
		}
		
		System.out.println("New Game started!");
		
		System.out.print("\nPlease Enter your username: ");
		Main.playName = Commands.inputString();
		
		story();
		
		return true;
	}
	
	public static boolean loadGame() {
		boolean isValid = false;
		int choice;
		
		do {
			System.out.println("Save Slots:\n");
			printSaves();
			System.out.println("Choose a save slot to load or 0 to exit");
			choice  = Commands.inputInt(0, 5);
			
			if(choice == 0) {
				return false;
			}
			
			if(saves.get(choice-1).isEmpty()) {
				System.out.println("Save slot is empty!!!");
				Commands.pressEnter();
			}
			else {
				isValid = true;
			}
		} while(!isValid);
		
		activeSave = choice;
		
		load();
		
		System.out.println("Game loaded successfully! Welcome back " + Main.playName + "!!!");
		Commands.pressEnter();
		
		return true;
	}
	
	public static void story() {
		String text = """
	            Long ago, the land of Cardonia pulsed with ancient magic-wild, untamed,
	            and unreachable by human hands. From enchanted forests to monstrous
	            beasts, the world was a beautiful yet dangerous place.

	            For centuries, humanity banded together, struggling to survive against
	            nature's magical fury. Until one fateful day, everything changed.

	            Three visionaries -Plensor, Mejashi, and Cruden- discovered a way to wield
	            magic through enchanted cards and relics. With this newfound power, they
	            taught others, built great schools of magic, and brought hope to all.

	            But magic has a price.

	            The very power they harnessed began to twist and corrupt. Their creations
	            turned dark. The great mages, once heroes, were consumed-now shadows of
	            their former selves, spreading ruin across the land.

	            Now, only a few untainted mages remain. Armed with the very cards forged
	            by their fallen mentors, they embark on a final journey...

	            To stop the corruption.
	            To restore Cardonia.
	            To become the next legend.
	            """;
		
		Commands.typeWriterEffect(text);
		
		Commands.pressEnter();
		
		Commands.typeWriterEffect("""
				You start your adventure as one of the few surviving mages!!!
				""");
	}
	
	public static void save() {
		saves.remove(activeSave-1);
		saves.add(activeSave-1, new SaveSlot(Main.playName, Main.xpLevel, Main.gold));
		printMain();
		
		try {
			FileWriter currentSave = new FileWriter(savefiles[activeSave - 1]);
			PrintWriter pw = new PrintWriter(currentSave);
			
			pw.println(Main.playName + "," + Main.playMaxHP + "," + Main.playCurrentHP + "," + Main.gold + "," + Main.battleCount);
			
			pw.println(Main.xpLevel + "," + Main.currentXp + "," + Main.maxXp + "," + Main.skillpoints);
			
			pw.println(Main.intelligence + "," + Main.strength + "," + Main.arcana + "," + Main.corruptedness);
			
			pw.println(Main.permMult + "," + Main.permIntMult + "," + Main.permStrMult + "," + Main.permCorMult + "," + Main.permShopSale);
			
			pw.println(Main.permMaxMana + "," + Main.permHand + "," + Main.location);
			
			int size = Main.deck.size();
			
			for(int i = 0; i < size; i++) {
				pw.println(Main.deck.get(i).save());
			}
			
			pw.println("---");
			
			size = Main.inventory.size();
			
			for(int i = 0; i < size; i++) {
				pw.println(Main.inventory.get(i).save());
			}
			
			pw.println("---");
			
			size = Main.artifacts.size();
			
			for(int i = 0; i < size; i++) {
				pw.println(Main.artifacts.get(i).save());
			}
			
			pw.println("---");
			
			pw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		try {
			String line;
			String[] tokens = new String[10];
			
			Scanner scan = new Scanner(savefiles[activeSave - 1]);
			
			line = scan.nextLine();
			tokens = line.split(",");
			Main.playName = tokens[0];
			Main.playMaxHP = Integer.parseInt(tokens[1]);
			Main.playCurrentHP = Integer.parseInt(tokens[2]);
			Main.gold = Integer.parseInt(tokens[3]);
			Main.battleCount = Integer.parseInt(tokens[4]);
			
			line = scan.nextLine();
			tokens = line.split(",");
			Main.xpLevel = Integer.parseInt(tokens[0]);
			Main.currentXp = Integer.parseInt(tokens[1]);
			Main.maxXp = Integer.parseInt(tokens[2]);
			Main.skillpoints = Integer.parseInt(tokens[3]);
			
			line = scan.nextLine();
			tokens = line.split(",");
			Main.intelligence = Integer.parseInt(tokens[0]);
			Main.strength = Integer.parseInt(tokens[1]);
			Main.arcana = Integer.parseInt(tokens[2]);
			Main.corruptedness = Integer.parseInt(tokens[3]);
			
			line = scan.nextLine();
			tokens = line.split(",");
			Main.permMult = Double.parseDouble(tokens[0]);
			Main.permIntMult = Double.parseDouble(tokens[1]);
			Main.permStrMult = Double.parseDouble(tokens[2]);
			Main.permCorMult = Double.parseDouble(tokens[3]);
			Main.permShopSale = Double.parseDouble(tokens[4]);
			
			line = scan.nextLine();
			tokens = line.split(",");
			Main.permMaxMana = Integer.parseInt(tokens[0]);
			Main.permHand = Integer.parseInt(tokens[1]);
			Main.location = Integer.parseInt(tokens[2]);
			
			Main.deck.clear();
			Main.inventory.clear();
			Main.artifacts.clear();
			
			line = scan.nextLine();
			while(!line.equals("---")) {
				Main.deck.add(Card.loadCard(line));
				line = scan.nextLine();
			}
			
			line = scan.nextLine();
			while(!line.equals("---")) {
				Main.inventory.add(Item.loadItem(line));
				line = scan.nextLine();
			}
			
			line = scan.nextLine();
			while(!line.equals("---")) {
				Main.artifacts.add(Artifact.loadArtifact(line));
				line = scan.nextLine();
			}
			
			scan.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete() {
		saves.remove(activeSave-1);
		saves.add(activeSave-1, new SaveSlot());
		printMain();
		
		try {
			FileWriter currentSave = new FileWriter(savefiles[activeSave - 1]);
			PrintWriter pw = new PrintWriter(currentSave);
			
			pw.println("");
			
			pw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printSaves() {
		for(int i = 0; i < 5; i++) {
			System.out.println((i+1) + ". " + saves.get(i) + " \n");
		}
	}
	
	public static void printMain() {
		try {
			FileWriter mainFileWriter = new FileWriter(mainFile);
			PrintWriter pw = new PrintWriter(mainFileWriter);
			
			for(int i = 0; i < 5; i++) {
				pw.println(saves.get(i).save());
			}
			
			pw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class SaveSlot{
		String name;
		int level;
		int gold;
		
		SaveSlot(String name, int level, int gold){
			this.name = name;
			this.level = level;
			this.gold = gold;
		}
		
		SaveSlot(){
			this.name = "Empty";
			this.level = 0;
			this.gold = 0;
		}
		
		public String save() {
			return name + "," + level + "," + gold;
		}
		
		public boolean isEmpty() {
			if(this.name.equals("Empty")) {
				return true;
			}
			return false;
		}
		
		public String toString() {
			if(this.name.equals("Empty")) {
				return "Empty Save Slot";
			}
			
			return name + ", Level: " + level + ", Gold: " + gold;
		}
	}
}
