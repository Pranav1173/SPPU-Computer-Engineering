import java.util.*;

class OptimalPageReplacement {
    static void optimalPage(int[] pg, int fn) {
        List<Integer> fr = new ArrayList<>();
        int hit = 0;

        for (int i = 0; i < pg.length; i++) {
            if (fr.contains(pg[i])) {
                hit++;
            } else {
                if (fr.size() < fn) {
                    fr.add(pg[i]);
                } else {
                    int replaceIndex = predict(pg, fr, i + 1);
                    fr.set(replaceIndex, pg[i]);
                }
            }
        }

        System.out.println("No. of hits = " + hit);
        System.out.println("No. of misses = " + (pg.length - hit));
    }

    static int predict(int[] pg, List<Integer> fr, int index) {
        int farthest = -1;
        int res = -1;
        for (int i = 0; i < fr.size(); i++) {
            int j;
            for (j = index; j < pg.length; j++) {
                if (fr.get(i) == pg[j]) {
                    if (j > farthest) {
                        farthest = j;
                        res = i;
                    }
                    break;
                }
            }
            if (j == pg.length) {
                return i;
            }
        }
        return (res == -1) ? 0 : res;
    }

    public static void main(String[] args) {
        int pg[] = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2 };
        int fn = 4;
        optimalPage(pg, fn);
    }
}
