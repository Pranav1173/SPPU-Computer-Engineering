import java.util.*;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        int n = 3; // Number of frames
        int[] referenceString = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1}; // Predefined reference string
        List<Integer> frames = new ArrayList<>();
        Queue<Integer> remainingReferences = new LinkedList<>();
        for (int page : referenceString) {
            remainingReferences.add(page);
        }

        int page, faults = 0, hits = 0;

        while (!remainingReferences.isEmpty()) {
            page = remainingReferences.poll();
            if (!frames.contains(page)) {
                faults++;
                if (frames.size() == n) {
                    int farthest = -1;
                    int index = -1;
                    for (int i = 0; i < n; i++) {
                        int nextPageIndex = findNextIndex(remainingReferences, frames.get(i));
                        if (nextPageIndex == -1) {
                            index = i;
                            break;
                        }
                        if (nextPageIndex > farthest) {
                            farthest = nextPageIndex;
                            index = i;
                        }
                    }
                    frames.set(index, page);
                } else {
                    frames.add(page);
                }
            } else {
                hits++;
            }
        }

        double hitRatio = (double) hits / (hits + faults);
        System.out.println("Faults: " + faults);
        System.out.println("Hits: " + hits);
        System.out.println("Hit Ratio: " + hitRatio);
    }

    private static int findNextIndex(Queue<Integer> queue, int element) {
        int index = 0;
        for (int item : queue) {
            if (item == element) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
