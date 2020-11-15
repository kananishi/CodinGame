package SingleFileGenerator.generator;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileSearcher
{
    /**
     * Concatena todas as classes da classesFolder num unico arquivo na
     * singleFileFolder
     *
     * @throws IOException
     */
    public static List<File> extractJavaFiles(
        final String directoryPath )
    {
        final File dir = new File( directoryPath );
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
