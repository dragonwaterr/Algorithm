import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 깊콘이 넘쳐흘러
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] coupon = new int[n+1][2];

        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                coupon[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        coupon[n][1] = Integer.MAX_VALUE;
        Arrays.sort(coupon, (o1, o2) -> o1[1] == o2[1]
            ? o1[0] - o2[0]
            : o1[1] - o2[1]
        );

        long extend = 0;
        int max = 0;
        int plan = coupon[0][1];

        for (int i = 0; i < n; i++) {
            if(i != 0 && plan != coupon[i][1]) {
                plan = coupon[i][1];
                for(int j = i; coupon[j][1] == plan; j++) {
                    if(max > coupon[j][0]) {
                        int need = max - coupon[j][0];
                        int cnt = (need + 29) / 30;
                        extend += cnt;
                        coupon[j][0] += cnt * 30;
                    }
                }
            }

            if (coupon[i][0] < coupon[i][1]) {
                int need = coupon[i][1] - coupon[i][0];
                int cnt = (need + 29) / 30;
                extend += cnt;
                coupon[i][0] += cnt * 30;
            }
            max = Math.max(max, coupon[i][0]);
            plan = coupon[i][1];

        }
        System.out.println(extend);
    }
}
