package eu.eutampieri.catacombs.ui.utils;

import eu.eutampieri.catacombs.ui.DungeonGame;
import eu.eutampieri.catacombs.ui.Game;

import java.util.Scanner;

public final class TextLoader {

	private static Game game = new DungeonGame();
	private TextLoader() {

	}

	public static String loadText(final String path) {
		try (Scanner scan = new Scanner(TextLoader.class.getResourceAsStream(path))){
			final StringBuilder sb = new StringBuilder();
			final String file = "";
			while (scan.hasNext()) {
				final String str = scan.next() + " ";
				sb.insert(file.length(), str);

			}
			return file;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			game.stop();
		}
		
		return null;
		
	}
}
