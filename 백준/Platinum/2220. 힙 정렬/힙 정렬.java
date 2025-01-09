import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] maxheap = new int[n+1];
        
        if(n == 1) {
            System.out.println(1);
            return;
        }
        
        for(int i = 1; i < n; i++) {
            maxheap[i + 1] = 1;
            maxheap[i] = i + 1;

            int idx = i;
            while(idx > 1) {
                maxheap[idx / 2] = maxheap[idx / 2] ^ maxheap[idx] ^ (maxheap[idx] = maxheap[idx / 2]);
                idx /= 2;
            }
        }

        for(int i = 1; i <= n; i++) {
            sb.append(maxheap[i]).append(" ");
        }
        System.out.println(sb);
    }
}