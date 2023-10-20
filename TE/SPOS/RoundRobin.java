import java.util.Scanner;

class Process {
    String name;
    int BT, AT, CT, TAT, WT, remBT;

    Process(String name, int BT, int AT) {
        this.name = name;
        this.BT = this.remBT = BT;
        this.AT = AT;
    }

    void display() {
        System.out.println(name + "\t" + BT + "\t" + AT + "\t" + CT + "\t" + TAT + "\t" + WT);
    }
}

public class RoundRobin {
    private Scanner sc = new Scanner(System.in);

    public void execute() {
        System.out.print("Enter Number of Processes: ");
        int numProcess = sc.nextInt();
        Process[] process = new Process[numProcess];

        for (int i = 0; i < numProcess; i++) {
            System.out.print("P(" + (i + 1) + "): Arrival time & Burst time: ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            process[i] = new Process("P" + (i + 1), bt, at);
        }

        int quantum = getInput("Enter Quantum Time: ");
        double avgWT = 0, avgTAT = 0;
        int time = 0;
        System.out.println("\nPRNo\tBT\tAT\tCT\tTAT\tWT");
        System.out.println("==============================================");

        boolean done;
        do {
            done = true;
            for (Process p : process) {
                if (p.remBT > 0 && p.AT <= time) {
                    done = false;
                    int executionTime = Math.min(quantum, p.remBT);
                    time += executionTime;
                    p.remBT -= executionTime;
                    if (p.remBT == 0) {
                        p.CT = time;
                        p.TAT = p.CT - p.AT;
                        p.WT = p.TAT - p.BT;
                        avgWT += p.WT;
                        avgTAT += p.TAT;
                        p.display();
                    }
                }
            }
        } while (!done);

        avgTAT /= numProcess;
        avgWT /= numProcess;
        System.out.println("Average Waiting Time: " + avgWT);
        System.out.println("Average TAT: " + avgTAT);
    }

    private int getInput(String prompt) {
        System.out.print(prompt);
        return sc.nextInt();
    }

    public static void main(String[] args) {
        RoundRobin roundRobin = new RoundRobin();
        roundRobin.execute();
    }
}
