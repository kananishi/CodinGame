package convert.classes.into.singleFile;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class GenearateSingleFileFromClasses {

	private static final String sourceFolder = "C:\\Users\\Guilherme\\Documents\\Work\\sources\\CodingGames\\";
	private static final String javaProject = "src\\main\\java\\";
	
	private static final String actualGame = "CodeVsZombies\\";
	private static final String gameFolder = "game\\";
	
	private static final String fileGenerator = "FileGenerator\\";
	private static final String singleFileFolder = "\\generated\\singleFile\\Player.txt";

	private static final String defaultImport = "import java.util.*;\n"
			+ "import java.io.*;\n"
			+ "import java.math.*;\n"
			+ "import static java.util.stream.Collectors.toList;";

	public static void main(final String[] args) {
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

		final Path dir = Paths.get(sourceFolder, actualGame, javaProject, gameFolder);
		final DirectoryStream<Path> files = Files.newDirectoryStream(dir);
		for(final Path path : files) {
			final Scanner in = new Scanner(path.toFile());
			while(in.hasNextLine()) {
				final String line = in.nextLine();
				if(ignoreOnCompile(line)) {
					pw.println(line);
				}
			}
		}
		pw.flush();
		pw.close();
	}

	/**
	 * Retorna false se a linha for package, import, ou comentario
	 */
	private static boolean ignoreOnCompile(final String line) {
		return !line.startsWith("package")
				&& !line.startsWith("import")
				&& !line.startsWith("//")
				&& !line.startsWith("/**")
				&& !line.startsWith("*")
				&& !line.startsWith("*/");
	}



}
