import java.util.Arrays;
import java.util.Scanner;

class Process {
    String name;
    int BT; // Burst Time
    int AT; // Arrival Time
    int CT; // Completion Time
    int TAT; // Turnaround Time
    int WT; // Waiting Time

    Process(String name, int BT, int AT) {
        this.name = name;
        this.BT = BT;
        this.AT = AT;
    }

    void display() {
        System.out.println(name + "\t" + BT + "\t" + AT + "\t" + CT + "\t" + TAT + "\t" + WT);
    }
}

public class fcfs {
    private Scanner sc;

    public void execute() {
        sc = new Scanner(System.in);

        System.out.println("Enter Number of Processes:");
        int numProcess = sc.nextInt();
        Process[] process = new Process[numProcess];

        for (int i = 0; i < numProcess; i++) {
            System.out.println("P(" + (i + 1) + "): Enter Arrival time & Burst time");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            process[i] = new Process("P" + (i + 1), bt, at);
        }

        Arrays.sort(process, (p1, p2) -> p1.AT - p2.AT);

        double avgWT = 0, avgTAT = 0;
        System.out.println("\nPRNo\tBT\tAT\tCT\tTAT\tWT");
        System.out.println("______________________________________________________________________");
        int sum = 0;
        for (int i = 0; i < numProcess; i++) {
            sum = process[i].CT = sum + process[i].BT;
            process[i].TAT = process[i].CT - process[i].AT;
            process[i].WT = process[i].TAT - process[i].BT;

            avgWT = avgWT + process[i].WT;
            avgTAT = avgTAT + process[i].TAT;

            process[i].display();
        }
        avgTAT = (double) avgTAT / numProcess;
        avgWT = (double) avgWT / numProcess;
        System.out.println("Average Waiting Time: " + avgWT);
        System.out.println("Average TAT: " + avgTAT);
    }

    public static void main(String[] args) {
        new fcfs().execute();
    }
}
