import java.util.Arrays;
import java.util.Scanner;

class Process {
    String name;
    int BT, AT, CT, TAT, WT, priority;

    Process(String name, int BT, int AT, int priority) {
        this.name = name;
        this.BT = BT;
        this.AT = AT;
        this.priority = priority;
    }

    void display() {
        System.out.println(name + "\t" + BT + "\t" + AT + "\t" + CT + "\t" + TAT + "\t" + WT + "\t" + priority);
    }
}

public class PriorityNonPreemptive {
    private Scanner sc;

    public void execute() {
        sc = new Scanner(System.in);

        System.out.print("Enter Number of Processes: ");
        int numProcess = sc.nextInt();
        Process[] process = new Process[numProcess];

        for (int i = 0; i < numProcess; i++) {
            System.out.print("P(" + (i + 1) + "): Burst time & priority: ");
            int bt = sc.nextInt();
            int priority = sc.nextInt();
            process[i] = new Process("P" + (i + 1), bt, 0, priority);
        }

        Arrays.sort(process, (p1, p2) -> p2.priority - p1.priority);

        int sum = 0;
        double avgWT = 0, avgTAT = 0;
        System.out.println("\nPRNo\tBT\tAT\tCT\tTAT\tWT\tPR");
        System.out.println("==============================================");

        for (int i = 0; i < numProcess; i++) {
            sum += process[i].BT;
            process[i].CT = sum;
            process[i].TAT = process[i].CT - process[i].AT;
            process[i].WT = process[i].TAT - process[i].BT;

            avgWT += process[i].WT;
            avgTAT += process[i].TAT;

            process[i].display();
        }

        avgTAT /= numProcess;
        avgWT /= numProcess;
        System.out.println("Average Waiting Time: " + avgWT);
        System.out.println("Average TAT: " + avgTAT);
    }

    public static void main(String[] args) {
        PriorityNonPreemptive priorityNonPreemptive = new PriorityNonPreemptive();
        priorityNonPreemptive.execute();
    }
}
