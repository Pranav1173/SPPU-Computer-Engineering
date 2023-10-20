import java.io.*;

class Pass1 {
    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("C:\\Users\\prana\\Java\\Input.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        int lineCount = 0, locationCounter = 0, symTabLine = 0, opTabLine = 0, litTabLine = 0, poolTabLine = 0;
        final int MAX = 100;
        String[][] symbolTable = new String[MAX][3];
        String[][] opCodeTable = new String[MAX][3];
        String[][] literalTable = new String[MAX][2];
        int[] poolTable = new int[MAX];
        int literalTableAddress = 0;

        while ((line = bufferedReader.readLine()) != null) {
            String[] tokens = line.split("\t");
            if (lineCount == 0) {
                locationCounter = Integer.parseInt(tokens[1]);
                for (String token : tokens)
                    System.out.print(token + "\t");
                System.out.println();
            } else {
                for (String token : tokens)
                    System.out.print(token + "\t");
                System.out.println();
                if (!tokens[0].equals("")) {
                    symbolTable[symTabLine][0] = tokens[0];
                    symbolTable[symTabLine][1] = Integer.toString(locationCounter);
                    symbolTable[symTabLine][2] = "1";
                    symTabLine++;
                } else if (tokens[1].equalsIgnoreCase("DS") || tokens[1].equalsIgnoreCase("DC")) {
                    symbolTable[symTabLine][0] = tokens[0];
                    symbolTable[symTabLine][1] = Integer.toString(locationCounter);
                    symbolTable[symTabLine][2] = "1";
                    symTabLine++;
                }
                if (tokens.length == 3 && tokens[2].charAt(0) == '=') {
                    literalTable[litTabLine][0] = tokens[2];
                    literalTable[litTabLine][1] = Integer.toString(locationCounter);
                    litTabLine++;
                } else if (tokens[1] != null) {
                    opCodeTable[opTabLine][0] = tokens[1];
                    if (tokens[1].equalsIgnoreCase("START") || tokens[1].equalsIgnoreCase("END")
                            || tokens[1].equalsIgnoreCase("ORIGIN") || tokens[1].equalsIgnoreCase("EQU")
                            || tokens[1].equalsIgnoreCase("LTORG")) {
                        opCodeTable[opTabLine][1] = "AD";
                        opCodeTable[opTabLine][2] = "R11";
                    } else if (tokens[1].equalsIgnoreCase("DS") || tokens[1].equalsIgnoreCase("DC")) {
                        opCodeTable[opTabLine][1] = "DL";
                        opCodeTable[opTabLine][2] = "R7";
                    } else {
                        opCodeTable[opTabLine][1] = "IS";
                        opCodeTable[opTabLine][2] = "(04,1)";
                    }
                    opTabLine++;
                }
            }
            lineCount++;
            locationCounter++;
        }
        System.out.println(" SYMBOL TABLE ");
        System.out.println("--------------------------");
        System.out.println("SYMBOL\tADDRESS\tLENGTH");
        System.out.println("--------------------------");
        for (int i = 0; i < symTabLine; i++)
            System.out.println(symbolTable[i][0] + "\t" + symbolTable[i][1] + "\t" + symbolTable[i][2]);
        System.out.println("--------------------------");
        System.out.println(" OPCODE TABLE ");
        System.out.println("----------------------------");
        System.out.println("MNEMONIC\tCLASS\tINFO");
        System.out.println("----------------------------");
        for (int i = 0; i < opTabLine; i++)
            System.out.println(opCodeTable[i][0] + "\t\t" + opCodeTable[i][1] + "\t" + opCodeTable[i][2]);
        System.out.println("----------------------------");
        System.out.println(" LITERAL TABLE ");
        System.out.println("-----------------");
        System.out.println("LITERAL\tADDRESS");
        System.out.println("-----------------");
        for (int i = 0; i < litTabLine; i++)
            System.out.println(literalTable[i][0] + "\t" + literalTable[i][1]);
        System.out.println("------------------");
        for (int i = 0; i < litTabLine; i++) {
            if (literalTable[i][0] != null && literalTable[i + 1][0] != null) {
                if (i == 0) {
                    poolTable[poolTabLine] = i + 1;
                    poolTabLine++;
                } else if (Integer.parseInt(literalTable[i][1]) < (Integer.parseInt(literalTable[i + 1][1]) - 1)) {
                    poolTable[poolTabLine] = i + 2;
                    poolTabLine++;
                }
            }
        }
        System.out.println(" POOL TABLE ");
        System.out.println("-----------------");
        System.out.println("LITERAL NUMBER");
        System.out.println("-----------------");
        for (int i = 0; i < poolTabLine; i++)
            System.out.println(poolTable[i]);
        System.out.println("------------------");
        bufferedReader.close();
    }
}
