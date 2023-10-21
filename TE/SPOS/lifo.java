import java.util.*;

public class lifo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Prompt the user for the number of frames
        System.out.print("Enter the number of frames: ");
        int n = sc.nextInt();
        
        // Initialize variables
        int p, i = 0, f = 0, h = 0;
        int[] q = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // Prompt the user for the reference string
        System.out.println("Enter the reference string (enter -1 to stop):");
        
        // Read reference string until the user enters -1
        while ((p = sc.nextInt()) != -1) {
            if (!stack.contains(p)) {
                if (stack.size() == n) {
                    int removedPage = stack.pop();
                    f++;
                }
                stack.push(p);
            } else {
                stack.removeElement(p);
                stack.push(p);
                h++;
            }
        }
        
        double hitRatio = (double) h / (h + f);
        System.out.println("Faults: " + f);
        System.out.println("Hits: " + h);
        System.out.println("Hit Ratio: " + hitRatio);
    }
}
