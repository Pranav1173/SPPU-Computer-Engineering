import java.util.LinkedHashMap;
import java.util.Map;

public class lru{
    public static void main(String[] args) {
        int frames = 4; // Number of frames
        int[] reference = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2}; // Reference string

        Map<Integer, Integer> buffer = new LinkedHashMap<>(frames, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > frames;
            }
        };

        int hit = 0;
        int fault = 0;

        for (int page : reference) {
            if (buffer.containsKey(page)) {
                hit++;
            } else {
                fault++;
            }
            buffer.put(page, 0);
        }

        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float) hit / reference.length);
        System.out.println("The number of Faults: " + fault);
    }
}
