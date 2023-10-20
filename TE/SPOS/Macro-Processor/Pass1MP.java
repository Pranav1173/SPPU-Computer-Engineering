import java.util.*;
import java.io.*;

class MDT {
    int index;
    String def;

    MDT(int i, String a) {
        this.index = i;
        this.def = a;
    }
}

class MNT {
    int index, mdtind;
    String name;
    Vector<String> ala;

    MNT(int i, String a, int ind, Vector<String> b) {
        this.index = i;
        this.name = a;
        this.mdtind = ind;
        this.ala = b;
    }
}

public class Pass1MP {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("inputmp.txt"));
        Vector<MDT> mdt = new Vector<>();
        Vector<MNT> mnt = new Vector<>();
        Vector<String> isc = new Vector<>();
        String line;
        boolean macroDefinition = false;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty())
                continue;

            if (line.contains("MACRO")) {
                macroDefinition = true;
                line = reader.readLine();
                String macroName = line.split("\\s+")[0];
                Vector<String> arguments = new Vector<>();
                while (!line.contains("MEND")) {
                    String[] tokens = line.split("\\s+");
                    for (String token : tokens) {
                        if (token.startsWith("&") && !token.contains("=")) {
                            arguments.addElement(token);
                        }
                    }
                    mdt.addElement(new MDT(mdt.size() + 1, line));
                    line = reader.readLine();
                }
                mnt.addElement(new MNT(mnt.size() + 1, macroName, mdt.size() - arguments.size(), arguments));
            } else {
                isc.addElement(line);
            }
        }

        System.out.println("\nPASS 1\n");
        System.out.println("MDT");
        for (MDT t : mdt) {
            System.out.println(t.index + " " + t.def);
        }

        System.out.println("\nMNT");
        for (MNT t : mnt) {
            System.out.print(t.index + " " + t.name + " " + t.mdtind + "\tALA: ");
            for (String arg : t.ala) {
                System.out.print(arg + " ");
            }
            System.out.println();
        }

        System.out.println("\nIntermediate code");
        for (String instruction : isc) {
            System.out.println(instruction);
        }
    }
}
