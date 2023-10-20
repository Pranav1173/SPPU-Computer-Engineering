import java.io.*;
import java.util.*;

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

public class Pass2MP {
    public static int searchmnt(Vector<MNT> a, String b) {
        int i, pos = -1;
        for (i = 0; i < a.size(); i++) {
            MNT x = a.get(i);
            if (x.name.equals(b)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

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

        System.out.println("\nPASS 2\n");

        // Process intermediate code
        for (String instruction : isc) {
            String[] temp = instruction.split("\\s+");
            int pos1 = searchmnt(mnt, temp[0]);
            if (pos1 == -1) {
                System.out.println(instruction);
            } else {
                MNT x = mnt.get(pos1);
                int mdt_ind = x.mdtind;
                String ala1[] = temp[1].split(",");
                StringBuffer ss = new StringBuffer();
                for (int j = mdt_ind; j < mdt.size(); j++) {
                    boolean flag = false;
                    String def = mdt.get(j).def;
                    if (def.equals("MEND")) {
                        break;
                    } else {
                        for (int k = 0; k < def.length(); k++) {
                            if (!flag && def.charAt(k) != '#') {
                                System.out.print(def.charAt(k));
                            } else if (!flag && def.charAt(k) == '#') {
                                flag = true;
                            } else if (flag && def.charAt(k) == ',') {
                                int pos = Integer.parseInt(ss.toString());
                                System.out.print(ala1[pos - 1]);
                                ss.delete(0, ss.length());
                                flag = false;
                            } else if (flag) {
                                ss.append(def.charAt(k));
                            }
                        }
                        if (ss.length() > 0) {
                            int pos = Integer.parseInt(ss.toString());
                            System.out.print(ala1[pos - 1]);
                            ss.delete(0, ss.length());
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}
