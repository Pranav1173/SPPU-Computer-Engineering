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

public class sfj {
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

        int count = 0, time = 0;
        double avgWT = 0, avgTAT = 0;
        System.out.println("\nPRNo\tBT\tAT\tCT\tTAT\tWT");
        System.out.println("==============================================");

        while (count < numProcess) {
            int shortest = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < numProcess; i++) {
                if (process[i].AT <= time && process[i].remBT < min && process[i].remBT > 0) {
                    shortest = i;
                    min = process[i].remBT;
                }
            }
            if (shortest == -1) {
                time++;
                continue;
            }
            process[shortest].remBT--;

            if (process[shortest].remBT == 0) {
                count++;
                process[shortest].CT = time + 1;
                process[shortest].TAT = process[shortest].CT - process[shortest].AT;
                process[shortest].WT = process[shortest].TAT - process[shortest].BT;
                avgWT += process[shortest].WT;
                avgTAT += process[shortest].TAT;
                process[shortest].display();
            }
            time++;
        }

        avgTAT /= numProcess;
        avgWT /= numProcess;
        System.out.println("Average Waiting Time: " + avgWT);
        System.out.println("Average TAT: " + avgTAT);
    }

    public static void main(String[] args) {
        sfj sjf1 = new sfj();
        sjf1.execute();
    }
}
