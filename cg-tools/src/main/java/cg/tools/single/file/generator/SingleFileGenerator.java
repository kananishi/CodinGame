package cg.tools.single.file.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

final class SingleFileGenerator
{
    private static final String sourceFolder = System.getProperty( "user.home" )
        + "\\Documents\\Work\\sources\\CodingGames\\";
    private static final String resources = "cg-tools\\src\\main\\resources\\";
    private static final String actualGame = "code-vs-zombies\\";
    private static final String singleFileFolder = "single\\file\\generator\\output\\Player.java";
    
    public static final void main(
	        final String[] args )
	    {
	        try {
	            SingleFileGenerator.run();
	            System.out.println( "Sucess!!" );
	        } catch( final IOException e ) {
	            e.printStackTrace();
	        }
	    }

    /**
     * Concatena todas as classes da classesFolder num unico arquivo na
     * singleFileFolder
     *
     * @throws IOException
     */
    static void run()
        throws IOException
    {
        final FilePrinter pw = FilePrinter.create( sourceFolder + resources + singleFileFolder );

        List<File> extractJavaFiles = FileSearcher.extractJavaFiles( sourceFolder + actualGame );
		extractJavaFiles
            .stream()
            .forEach( file -> pw.copyFileToNewFile( file ) );
    }

}
