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
        for (int i = 0; i < n; i++) {
            while(coupon[i][0] < coupon[i][1]) {
                extend++;
                coupon[i][0] += 30;
            }
            
            if(coupon[i][1] == coupon[i+1][1]) continue;

            int idx = i+1;
            int samePlan = coupon[idx][1];
            while(idx < n && coupon[idx][1] == samePlan) { 
                while(coupon[i][0] > coupon[idx][0]) {
                    extend++;
                    coupon[idx][0] += 30;
                }
                idx++;
            }
            
            Arrays.parallelSort(coupon, i, idx, (o1, o2) -> o1[0] - o2[0]);

        }
        System.out.println(extend);
    }
}
