import java.util.*;

public class fifo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Prompt the user for the number of frames
        System.out.print("Enter the number of frames: ");
        int n = sc.nextInt();
        
        // Initialize variables
        int p, i = 0, f = 0, h = 0;
        int[] q = new int[n];
        Set<Integer> s = new HashSet<>();
        
        // Prompt the user for the reference string
        System.out.println("Enter the reference string (enter -1 to stop):");
        
        // Read reference string until the user enters -1
        while ((p = sc.nextInt()) != -1) {
            if (!s.contains(p)) {
                if (s.size() == n) {
                    s.remove(q[i % n]);
                }
                s.add(q[i % n] = p);
                i++;
                f++;
            } else {
                h++;
            }
        }
        
        double hitRatio = (double) h / (h + f);
        System.out.println("Faults: " + f);
        System.out.println("Hits: " + h);
        System.out.println("Hit Ratio: " + hitRatio);
    }
}
