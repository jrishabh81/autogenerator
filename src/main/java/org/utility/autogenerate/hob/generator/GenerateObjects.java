package org.utility.autogenerate.hob.generator;


import java.util.ArrayList;
import java.util.List;


public class GenerateObjects {
    public static void main(String[] args) {
        try {
            List<String> extraImportPackages = getExtraImportPackages(args);
            new GenerateObjects().generate(extraImportPackages, args[args.length - 1]);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static List<String> getExtraImportPackages(String[] args) {
        List<String> strings = new ArrayList<>(args.length - 1);
        for (int i = 0; i < args.length - 1; i++) {
            String arg = args[i];
            strings.add("import " + arg + ";\n");
        }
        return strings;
    }

    private void generate(List<String> extraImportPackages, String arg) {



    }


}
