package generator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.event.ListSelectionEvent;


public final class SingleFileGenerator {

	private static final String sourceFolder = System.getProperty("user.home") + "\\Documents\\Work\\sources\\CodingGames\\";
	private static final String javaProject = "src\\main\\java\\";
	
	private static final String actualGame = "CodeOfIceAndFire\\";
	private static final String gameFolder = "game\\";
	
	private static final String fileGenerator = "FileGenerator\\";
	private static final String singleFileFolder = "\\generated\\singleFile\\Player.txt";

	private static final String defaultImport = "import java.util.*;\n"
			+ "import java.io.*;\n"
			+ "import java.math.*;\n"
			+ "import static java.util.stream.Collectors.toList;";

	public static final void main(final String[] args) {
		try {
			run();
			System.out.println("Sucess!!");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Concatena todas as classes da classesFolder num unico arquivo na singleFileFolder
	 *
	 * @throws IOException
	 */
	private static void run() throws IOException {
		final File fout = new File(sourceFolder + fileGenerator + javaProject + singleFileFolder);
		final PrintWriter pw = new PrintWriter(new FileWriter(fout));
		pw.print(defaultImport);

		final File dir = new File(sourceFolder + actualGame + javaProject + gameFolder);
		concatenateDirectoryFiles(dir, pw);
		pw.flush();
		pw.close();
	}

	private static void concatenateDirectoryFiles(final File dir, final PrintWriter fout) {
		if(dir.isDirectory()) {
			for(File file : dir.listFiles()) {
				concatenateDirectoryFiles(file, fout);
			}
		}
		if(dir.isFile()) {
			copyFileToNewFile(dir, fout);
		}
		return;
	}

	private static void copyFileToNewFile(final File file, final PrintWriter fout) {
		try {
			final Scanner in = new Scanner(file);
			while(in.hasNextLine()) {
				final String line = in.nextLine();
				if(!ignoreOnCompile(line)) {
					fout.println(line);
				}
			}
			in.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Retorna false se a linha for package, import, ou comentario
	 */
	private static boolean ignoreOnCompile(final String line) {
		return line.startsWith("package")
				|| line.startsWith("import")
				|| line.startsWith("//")
				|| line.startsWith("/**")
				|| line.startsWith("*")
				|| line.startsWith("*/");
	}

}
