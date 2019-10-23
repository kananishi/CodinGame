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

	private static final String sourceFolder = "C:\\Users\\Guilherme\\Documents\\Work\\sources";
	private static final String classesFolder = "\\CodeVsZombies\\src\\main\\java\\game";
	private static final String singleFileFolder = "\\CodeVsZombies\\src\\main\\java\\generated\\single\\file\\Player.java";

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
	 * @author Guilherme Nishi Kanashiro
	 * @throws IOException
	 */

	private static void run() throws IOException {
		final File fout = new File(sourceFolder + singleFileFolder);
		final PrintWriter pw = new PrintWriter(new FileWriter(fout));
		pw.print(defaultImport);

		final Path dir = Paths.get(sourceFolder, classesFolder);
		final DirectoryStream<Path> files = Files.newDirectoryStream(dir);
		for(final Path path : files) {
			final Scanner in = new Scanner(path.toFile());
			while(in.hasNextLine()) {
				final String line = in.nextLine();
				if(isCode(line)) {
					pw.println(line);
				}
			}
		}
		pw.flush();
		pw.close();
	}

	/**
	 * Retorna false se a linha for package, import, ou comentario
	 * @return
	 */
	private static boolean isCode(final String line) {
		return !line.startsWith("package")
				&& !line.startsWith("import")
				&& !line.startsWith("//")
				&& !line.startsWith("/**")
				&& !line.startsWith("*")
				&& !line.startsWith("*/");
	}



}
