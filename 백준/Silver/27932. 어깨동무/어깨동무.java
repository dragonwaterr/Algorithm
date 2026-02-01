import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if(n == 1) {
            System.out.println(0);
            return;
        }
        
        int[] heights = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        
        Integer[] diff = new Integer[n];
        diff[0] = Math.abs(heights[0] - heights[1]);
        diff[n - 1] = Math.abs(heights[n - 1] - heights[n - 2]);

        for (int i = 1; i < n - 1; i++) {
            diff[i] = Math.max(Math.abs(heights[i] - heights[i - 1]), Math.abs(heights[i] - heights[i + 1]));
        }

        Arrays.sort(diff, (o1, o2) -> o2 - o1);
        
        int h = diff[0];
        if(k > 0 && k < n)  {
            if(diff[k-1] != diff[k]) {
                h = diff[k];
            } else {
                h = diff[k-1];
            }
        } else if(k == n) { 
            h = 0;
        }
        System.out.println(h);
    }
}
