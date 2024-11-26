import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    public int findChampion(int n, int[][] edges) {
        int[] in = new int[n];
        int[] out = new int[n];
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            g.get(e[1]).add(e[0]);
            in[e[0]]++;
            out[e[1]]++;
        }

        boolean metZeroOut = false;
        for (int x : out) {
            if (x == 0 && !metZeroOut) {
                metZeroOut = true;
            } else if (x == 0 && metZeroOut) {
                return -1;
            }
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }
        int res = -1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> nei = g.get(cur);

            for (int i = 0; i < nei.size(); i++) {
                in[nei.get(i)]--;
                if (in[nei.get(i)] == 0) {
                    q.add(nei.get(i));
                }
            }


            res = cur;
        }

        return res;
    }
}