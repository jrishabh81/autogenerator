package org.utility.autogenerate.hob;

import org.simpleframework.xml.core.Persister;
import org.utility.autogenerate.hob.models.ObjectMappings;

import java.io.PrintStream;

public class Test {
    public static void main(String[] args) throws Exception {
        String s = "<objectMapping objectName=\"Car\" objectPrefix=\"ca\">\n" +
                "        <propertyMappings>\n" +
                "            <propertyMapping propertyName=\"CarProperty\" datatype=\"STRING\" isMandatory=\"true\"\n" +
                "                             isOneToOneNestedObject=\"false\"\n" +
                "                             propertyInstanceName=\"object\" nocopy=\"false\" loadNestedObject=\"false\" maxLength=\"255\"\n" +
                "                             isForeignKey=\"false\" prefixRequired=\"false\" isOrderColumn=\"false\"/>\n" +
                "        </propertyMappings>\n" +
                "\n" +
                "    </objectMapping>";
        Persister persister
                = new Persister();
//        ObjectMapping read = persister.read(ObjectMapping.class, s);
//        System.out.println(read);
//
//
        ObjectMappings objectMappings = persister.read(ObjectMappings.class, Test.class.getResourceAsStream("/sample.xml"));
        persister.write(objectMappings, System.out);

    }
}
