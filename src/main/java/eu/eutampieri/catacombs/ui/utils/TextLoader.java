package eu.eutampieri.catacombs.ui.utils;

import java.util.Scanner;

public class TextLoader {
	
	public static String loadText(String path) {
		try (Scanner scan = new Scanner(TextLoader.class.getResourceAsStream(path))){
			String file = "";
			while (scan.hasNext()) {
				file += scan.next() + " ";
			}
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return null;
		
	}
}
