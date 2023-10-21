import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class lifo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int frames, hit = 0, fault = 0, ref_len;
        int reference[];
        Stack<Integer> stack = new Stack<>();
        
        System.out.println("Please enter the number of Frames:");
        frames = Integer.parseInt(br.readLine());
        System.out.println("Please enter the length of the Reference string:");
        ref_len = Integer.parseInt(br.readLine());
        reference = new int[ref_len];

        System.out.println("Please enter the reference string:");
        for (int i = 0; i < ref_len; i++) {
            reference[i] = Integer.parseInt(br.readLine());
        }

        System.out.println();
        
        for (int i = 0; i < ref_len; i++) {
            if (stack.contains(reference[i])) {
                hit++;
                stack.removeElement(reference[i]);
            } else {
                fault++;
                if (stack.size() == frames) {
                    stack.pop();
                }
            }
            stack.push(reference[i]);
            
            System.out.print("Frames: ");
            for (Integer page : stack) {
                System.out.print(page + " ");
            }
            System.out.println();
        }

        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float) hit / ref_len);
        System.out.println("The number of Faults: " + fault);
    }
}
