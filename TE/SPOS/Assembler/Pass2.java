import java.io.*;
import java.util.HashMap;

public class Pass2 {
    public static void main(String[] args) throws IOException {
        BufferedReader intermediateReader = new BufferedReader(new FileReader("C:\\Users\\prana\\Java\\Intermediate.txt"));
        BufferedReader symtabReader = new BufferedReader(new FileReader("C:\\Users\\prana\\Java\\Symtab.txt"));
        BufferedReader littabReader = new BufferedReader(new FileReader("C:\\Users\\prana\\Java\\Littab.txt"));
        FileWriter pass2Output = new FileWriter("C:\\Users\\prana\\Java\\Pass2output.txt");

        HashMap<Integer, String> symSymbol = new HashMap<>();
        HashMap<Integer, String> litSymbol = new HashMap<>();
        HashMap<Integer, String> litAddr = new HashMap<>();

        String line;
        int symtabPointer = 1, littabPointer = 1, offset;

        while ((line = symtabReader.readLine()) != null) {
            String[] word = line.split("\t\t\t");
            if (word.length > 1) {
                symSymbol.put(symtabPointer++, word[1]);
            }
        }

        while ((line = littabReader.readLine()) != null) {
            String[] word = line.split("\t\t");
            if (word.length > 1) {
                litSymbol.put(littabPointer, word[0]);
                litAddr.put(littabPointer++, word[1]);
            }
        }

        while ((line = intermediateReader.readLine()) != null) {
            if (line.length() >= 6 && line.substring(1, 6).equalsIgnoreCase("IS,00")) {
                pass2Output.write("+ 00 0 000\n");
            } else if (line.length() >= 3 && line.substring(1, 3).equalsIgnoreCase("IS")) {
                pass2Output.write("+ " + line.substring(4, 6) + " ");
                if (line.length() >= 10 && line.charAt(9) == ')') {
                    pass2Output.write(line.charAt(8) + " ");
                    offset = 3;
                } else {
                    pass2Output.write("0 ");
                    offset = 0;
                }
                if (line.length() >= 11 + offset) {
                    if (line.charAt(8 + offset) == 'S') {
                        int symbolIndex = Integer.parseInt(line.substring(10 + offset, line.length() - 1));
                        String symbol = symSymbol.get(symbolIndex);
                        if (symbol != null) {
                            pass2Output.write(symbol + "\n");
                        }
                    } else if (line.charAt(8 + offset) == 'L') {
                        int literalIndex = Integer.parseInt(line.substring(10 + offset, line.length() - 1));
                        String literal = litAddr.get(literalIndex);
                        if (literal != null) {
                            pass2Output.write(literal + "\n");
                        }
                    }
                }
            } else if (line.length() >= 6 && line.substring(1, 6).equalsIgnoreCase("DL,01")) {
                String s1 = line.substring(10, line.length() - 1);
                String s2 = "0".repeat(3 - s1.length()) + s1;
                pass2Output.write("+ 00 0 " + s2 + "\n");
            } else {
                pass2Output.write("\n");
            }
        }

        pass2Output.close();
        intermediateReader.close();
        symtabReader.close();
        littabReader.close();
    }
}
