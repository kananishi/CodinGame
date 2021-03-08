package cg.tools.single.file.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FilePrinter
{

    private final PrintWriter fout;

    private static final String defaultImport = "import java.util.*;\n"
        + "import java.io.*;\n"
        + "import java.math.*;\n"
        + "import static java.util.stream.Collectors.toList;";

    public FilePrinter(
        final PrintWriter pw )
        throws IOException
    {
        fout = pw;
    }

    public static FilePrinter create(
        final String filePath )
    {

        PrintWriter pw = null;
        try {
            final File fout = new File( filePath );
            pw = new PrintWriter( new FileWriter( fout ) );
            pw.print( defaultImport );
            return new FilePrinter( pw );
        } catch( final IOException e ) {
            e.printStackTrace();
            throw new IllegalArgumentException( "Invalid file" );
        }
    }

    boolean copyFileToNewFile(
        final File file )
    {
        try {
            final Scanner in = new Scanner( file );
            while( in.hasNextLine() ) {
                final String line = in.nextLine();
                if( ! ignoreOnCompile( line ) ) {
                    if( isPublicClass( line ) ) {
                        // como nos jogos nao eh possivel ter uma classe,
                        // interface ou enum public eh preciso retirar a
                        // declaracao public
                        fout.println( line.replace( "public", "" ) );
                        continue;
                    }
                    fout.println( line );
                    fout.flush();
                }
            }
            in.close();
            return true;
        } catch( final Exception e ) {
            return false;
        }
    }

    /**
     * Retorna false se a linha for package, import, ou comentario
     */
    private static boolean ignoreOnCompile(
        final String line )
    {
        return line.startsWith( "package" ) || line.startsWith( "import" ) || line.startsWith( "//" )
            || line.startsWith( "/**" ) || line.startsWith( "*" ) || line.startsWith( " *" ) || line.startsWith( "*/" );
    }

    /**
     * Retorna true se a linha for a declaracao de uma classe ou uma interface
     * publica
     */
    private static boolean isPublicClass(
        final String line )
    {
        return line.contains( "public" )
            && ( line.contains( "class" ) || line.contains( "interface" ) || line.contains( "enum" ) );
    }

    public void close()
    {
        fout.flush();
        fout.close();
    }
}
