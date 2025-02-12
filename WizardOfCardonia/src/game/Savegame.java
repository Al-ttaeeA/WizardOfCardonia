package game;

public class Savegame {
	public static void newGame() {
		System.out.println("New Game started!");
		Commands.pressEnter();
		
		story();
	}
	
	public static void loadGame() {
		System.out.println("Game Loaded!");
		Commands.pressEnter();
	}
	
	public static void story() {
		System.out.println("Story goes here");
		Commands.pressEnter();
	}
}
