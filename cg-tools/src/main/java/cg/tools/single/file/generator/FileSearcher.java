package cg.tools.single.file.generator;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileSearcher
{
	
	private static final String javaProject = "src\\main\\java\\";
    private static final String gameFolder = "game\\";

    /**
     * Concatena todas as classes da classesFolder num unico arquivo na
     * singleFileFolder
     *
     * @throws IOException
     */
    public static List<File> extractJavaFiles(
        final String directoryPath )
    {
    	final String gameDir = directoryPath + javaProject + gameFolder;
        final File dir = new File( gameDir );
        return recursiveFilesSearch( dir );
    }

    private static List<File> recursiveFilesSearch(
        final File dir )
    {
        if( dir.isFile() ) {
            return Arrays.asList( dir );
        }

        return Arrays.asList( dir.listFiles() ).parallelStream()
            .flatMap( d -> recursiveFilesSearch( d ).stream() )
            .collect( Collectors.toList() );
    }
}
