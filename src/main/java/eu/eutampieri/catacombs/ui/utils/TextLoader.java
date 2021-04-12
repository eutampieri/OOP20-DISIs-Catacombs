package eu.eutampieri.catacombs.ui.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;

public final class TextLoader {

	private TextLoader() {

	}

	public static Optional<String> loadText(final Path path) {
		try (Scanner scan = new Scanner(path, StandardCharsets.UTF_8)) {
			final StringBuilder sb = new StringBuilder();
			while (scan.hasNext()) {
				final String str = scan.next() + " ";
				sb.append(str);

		  }
			return Optional.of(sb.toString());
		} catch (IOException e) {
			return Optional.empty();
		}
	}
}
