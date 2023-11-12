import java.util.*;

class Pass1 {
    public static void main(String[] args) {
        String[] code = {
            "START 100",
            "MOVER AREG,B",
            "ADD BREG,='6'",
            "MOVEM AREG,A",
            "SUB CREG,='1'",
            "LTORG",
            "ADD DREG,='5'",
            "X DS 1",
            "LTORG",
            "SUB AREG,='1'",
            "A DS 1",
            "B DC 1",
            "C DC 1",
            "END"
        };

        generateTables(code);
    }
    
    static void generateTables(String[] code) {
        Map<String, Integer> symbolTable = new HashMap<>();
        Map<String, Integer> literalTable = new HashMap<>();
        int address = 0;
        int startAddress = Integer.parseInt(code[0].split("\\s+")[1]);
        address = startAddress;

        for (String line : code) {
            String[] tokens = line.split("\\s+");

            if (tokens.length > 1) {
                String token = tokens[1].trim();
                String symbol = tokens[0].trim();

                if (token.equals("DS") || token.equals("DC")) {
                    if (!symbolTable.containsKey(symbol)) {
                        symbolTable.put(symbol, address);
                    }
                }
            }

            if (line.contains("=")) {
                String literal = line.substring(line.indexOf("='") + 2, line.lastIndexOf("'"));
                if (!literalTable.containsKey(literal)) {
                    literalTable.put(literal, address);
                }
            }
            if (line.contains("LTORG")) {
                address--;
            }

            address++;
        }

        System.out.println("\nSYMBOL TABLE");
        System.out.println("Symbol\t\tAddress\n");
        for (Map.Entry<String, Integer> entry : symbolTable.entrySet()) {
            System.out.println(entry.getKey() + "\t\t" + entry.getValue());
        }

        System.out.println("\nLITERAL TABLE");
        System.out.println("Literal\t\tAddress");
        for (Map.Entry<String, Integer> entry : literalTable.entrySet()) {
            System.out.println(entry.getKey() + "\t\t" + entry.getValue());
        }
    }
}
