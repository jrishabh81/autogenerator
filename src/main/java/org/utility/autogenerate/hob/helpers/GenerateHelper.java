package org.utility.autogenerate.hob.helpers;

import java.io.File;
import java.net.URL;

public class GenerateHelper
{
    public static File getSourceDirectory(URL source ) throws Exception
    {
        // assume resource is \<Product>\build_ide\META-INF\metadata.xml and we want \<Product>\src
        return new File( new File( source.toURI() ).getParentFile().getParentFile().getParentFile().getParentFile(), "src/main/java" );
    }
}
