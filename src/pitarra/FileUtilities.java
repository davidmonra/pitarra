package pitarra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class FileUtilities {

	public static final String readFile(String pathAndFilename) {
		File file = new File(pathAndFilename);

		if (file.canRead()) {
			Scanner scan;
			String fileContents = "";

			try {
				scan = new Scanner(file);
			} catch (FileNotFoundException e) {
				return null;
			}

			while (scan.hasNextLine())
				fileContents += scan.nextLine() + "\n";

			return fileContents;
		} else {
			return null;
		}
	}

	// static class is never instantiated
	private FileUtilities() {

	}
}
