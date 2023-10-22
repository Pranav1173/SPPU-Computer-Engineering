import java.util.*;

public class lifo {
    public static void main(String[] args) {
        // Predefined number of frames
        int n = 3;

        // Predefined reference string
        int[] referenceString = {3,1,2,1,5,4,1,3};
        int p, f = 0, h = 0;
        int[] q = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int page : referenceString) {
            if (!stack.contains(page)) {
                if (stack.size() == n) {
                    int removedPage = stack.pop();
                }
				f++;
                stack.push(page);
            } 
            else {
                h++;
            }
        }

        double hitRatio = (double) h / (h + f);
        System.out.println("Faults: " + f);
        System.out.println("Hits: " + h);
        System.out.println("Hit Ratio: " + hitRatio);
    }
}
