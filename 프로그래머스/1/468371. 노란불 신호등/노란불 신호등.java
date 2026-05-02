import java.util.*;

class Solution {
    public int solution(int[][] signals) {
        HashSet<String> set = new HashSet<>();

        int c = 0;

        while (true) {
            c++;

            int[] form = new int[signals.length];
            boolean yellow = true;

            for (int i = 0; i < signals.length; i++) {
                int[] post = signals[i];

                int T = post[0] + post[1] + post[2];
                int l = 1 + post[0];
                int r = post[0] + post[1];

                form[i] = c % T;

                if (!(l <= form[i] && form[i] <= r)) {
                    yellow = false;
                }
            }

            if (yellow) {
                return c;
            }

            String key = Arrays.toString(form);

            if (set.contains(key)) {
                return -1;
            }

            set.add(key);
        }
    }
}