package eu.eutampieri.catacombs.ui.utils;


import java.util.Optional;
import java.util.Scanner;


public final class TextLoader {

	private TextLoader() {

	}

	public static Optional<String> loadText(final String path) {
		try (Scanner scan = new Scanner(TextLoader.class.getResourceAsStream(path), "utf-8")){
			final StringBuilder sb = new StringBuilder();
			final String file = "";
			while (scan.hasNext()) {
				final String str = scan.next() + " ";
				sb.insert(file.length(), str);

			}
			return Optional.of(sb.toString());
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}
}
