import java.util.*;
import java.io.*;

class SymbolTable {
    int no;
    String symbol;
    int address = -1;

    // Constructor
    public SymbolTable(int no, String symbol) {
        this.no = no;
        this.symbol = symbol;
    }

    public String display() {
        return no + " " + symbol + " " + address;
    }
}

class LiteralTable {
    int no;
    String literal;
    int address = -1;

    // Constructor
    public LiteralTable(int no, String literal) {
        this.no = no;
        this.literal = literal;
    }

    public String display() {
        return no + " " + literal + " " + address;
    }
}

class PoolTable {
    int pool;

    // Constructor
    public PoolTable(int pool) {
        this.pool = pool;
    }

    public String display() {
        return String.valueOf(pool);
    }
}

public class Pass1Assembler {
    public static void main(String args[]) throws Exception {
        int symbolNo = 0, literalNo = 0, lc = 0, poolTable = 0;

        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        String output = "";

        List<SymbolTable> symbolTable = new ArrayList<>();
        List<LiteralTable> literalTable = new ArrayList<>();
        List<PoolTable> poolTables = new ArrayList<>();
        poolTables.add(new PoolTable(poolTable));

        String[] mot = {"STOP", "ADD", "SUB", "MUL", "MOVER", "MOVEM", "COMP", "BC", "DIV", "READ", "PRINT"};
        String[] pot = {" ", "START", "END", "EQU", "ORIGIN", "LTORG"};
        String[] reg = {"", "AREG", "BREG", "CREG", "DREG"};

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] tokens = line.split("\t");

            int potIndex = Arrays.asList(pot).indexOf(tokens[0]);
            if (potIndex != -1) {
                if (potIndex == 2) {
                    output += line + "\t\t\t\t     (IS,1)\n";
                    for (int i = poolTable; i < literalTable.size(); i++) {
                        String literal = literalTable.get(i).literal;
                        output += "\t\t\t\t\t" + lc + ") (DL,2) (C," + literal + ")\n";
                        lc++;
                    }
                    break;
                }
                switch (potIndex) {
                    case 1:
                        try {
                            lc = Integer.parseInt(tokens[2]);
                        } catch (Exception e) {
                        }
                        output += line + "\t\t\t     (IS,0) (C," + lc + ")\n";
                        break;
                    case 4:
                        try {
                            lc = Integer.parseInt(tokens[2]);
                        } catch (Exception e) {
                        }
                        output += line + "\t\t\t     (IS,4) (C," + lc + ")\n";
                        break;
                    case 5:
                        for (int i = poolTable; i < literalTable.size(); i++) {
                            String literal = literalTable.get(i).literal;
                            if (i == poolTable)
                                output += line + "\t\t\t\t" + lc + ") (DL,2) (C," + literal + ")\n";
                            else
                                output += "\t\t\t\t\t" + lc + ") (DL,2) (C," + literal + ")\n";
                            lc++;
                        }
                        poolTable = literalTable.size() - 1;
                        poolTables.add(new PoolTable(poolTable));
                        poolTable = literalTable.size();
                        break;
                }
            } else {
                int motIndex = Arrays.asList(mot).indexOf(tokens[1]);
                int existsInSymbolTable = 0;

                if (!tokens[0].isEmpty()) {
                    SymbolTable symbol = new SymbolTable(symbolNo, tokens[0]);
                    symbol.address = lc;
                    symbolTable.add(symbol);
                    symbolNo++;
                }

                String motMethod = tokens[1];
                String[] splitOperand = tokens[2].split(",");

                int regIndex = Arrays.asList(reg).indexOf(splitOperand[0]);

                if (splitOperand[1].contains("=")) {
                    LiteralTable literal = new LiteralTable(literalNo, splitOperand[1]);
                    literalTable.add(literal);
                    output += line + "\t\t" + lc + ") (AD," + potIndex + ") (RG," + regIndex + ") (L," + literalNo + ")\n";
                    lc++;
                    literalNo++;
                } else {
                    for (int i = 0; i < symbolTable.size(); i++) {
                        existsInSymbolTable = symbolTable.get(i).symbol.equals(splitOperand[1]) ? 1 : 0;
                        if (existsInSymbolTable == 1) {
                            output += line + "\t\t\t" + lc + ") (AD," + potIndex + ") (RG," + regIndex + ") (S," + i + ")\n";
                            lc++;
                            break;
                        }
                    }
                    if (existsInSymbolTable == 0) {
                        SymbolTable symbol = new SymbolTable(symbolNo, splitOperand[1]);
                        symbolTable.add(symbol);
                        output += line + "\t\t\t" + lc + ") (AD," + potIndex + ") (RG," + regIndex + ") (S," + symbolNo + ")\n";
                        lc++;
                        symbolNo++;
                    }
                }
            } else if (!tokens[0].isEmpty()) {
                int changedAddress = -1;
                for (int i = 0; i < symbolTable.size(); i++) {
                    changedAddress = symbolTable.get(i).symbol.equals(tokens[0]) ? 1 : -1;
                    if (changedAddress == 1) {
                        if (tokens[1].equals("DS")) {
                            output += line + "\t\t\t" + lc + ") (DL,1) (C," + tokens[2] + ")\n";
                            lc += Integer.parseInt(tokens[2]);
                        } else {
                            output += line + "\t\t\t" + lc + ") (DL,2) (C," + tokens[2] + ")\n";
                            lc++;
                        }
                        break;
                    }
                }
            }
        }

        System.out.println(output);
        System.out.println();

        StringBuilder symbolTableOutput = new StringBuilder();
        for (SymbolTable symbol : symbolTable) {
            symbolTableOutput.append(symbol.display()).append("\n");
        }
        System.out.println();

        StringBuilder literalTableOutput = new StringBuilder();
        for (LiteralTable literal : literalTable) {
            literalTableOutput.append(literal.display()).append("\n");
        }
        System.out.println();

        StringBuilder poolTableOutput = new StringBuilder();
        for (PoolTable pool : poolTables) {
            poolTableOutput.append(pool.display()).append("\n");
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("intercode.txt"));
        writer.write(output);
        writer.close();

        BufferedWriter writer1 = new BufferedWriter(new FileWriter("symbol.txt"));
        writer1.write(symbolTableOutput.toString());
        writer1.close();

        BufferedWriter writer2 = new BufferedWriter(new FileWriter("literal.txt"));
        writer2.write(literalTableOutput.toString());
        writer2.close();

        BufferedWriter writer3 = new BufferedWriter(new FileWriter("pool.txt"));
        writer3.write(poolTableOutput.toString());
        writer3.close();
    }
}
