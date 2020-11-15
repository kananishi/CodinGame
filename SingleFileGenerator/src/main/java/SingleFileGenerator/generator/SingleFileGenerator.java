package SingleFileGenerator.generator;

import java.io.IOException;

public final class SingleFileGenerator
{

    public static final void main(
        final String[] args )
    {
        try {
            run();
            System.out.println( "Sucess!!" );
        } catch( final IOException e ) {
            e.printStackTrace();
        }
    }

    private static final String sourceFolder = System.getProperty( "user.home" )
        + "\\Documents\\Work\\sources\\CodingGames\\";
    private static final String javaProject = "src\\main\\java\\";

    private static final String actualGame = "SpringChallenge20\\";
    private static final String gameFolder = "game\\";

    private static final String fileGenerator = "SingleFileGenerator\\";
    private static final String singleFileFolder = "\\SingleFileGenerator\\output\\Player.txt";

    /**
     * Concatena todas as classes da classesFolder num unico arquivo na
     * singleFileFolder
     *
     * @throws IOException
     */
    private static void run()
        throws IOException
    {
        final String gameDir = sourceFolder + actualGame + javaProject + gameFolder;
        final FilePrinter pw = FilePrinter.create( sourceFolder + fileGenerator + javaProject + singleFileFolder );

        FileSearcher.extractJavaFiles( gameDir )
            .stream()
            .forEach( file -> pw.copyFileToNewFile( file ) );
    }

}
