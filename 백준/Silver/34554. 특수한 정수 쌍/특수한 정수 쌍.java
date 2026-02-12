import java.io.*;
import java.util.*;

public class Main {

    static final int A = 1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        boolean[] isPrime = new boolean[10002];
        Arrays.fill(isPrime, true);
        makePrimeArray(isPrime);
        
        
        int tc = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < tc; i++) {
            int n = Integer.parseInt(br.readLine());
            int B = A + n; // 2
            if(isPrime[B]) {
                sb.append(1).append("\n").append(A + " " + B).append("\n");
                continue;
            }
            sb.append("0").append("\n");
        }
        System.out.println(sb);
        
    }
    
    static void makePrimeArray(boolean[] isPrime) {
        for(int i = 2; i <= 5001; i++) {
            int q = 2;
            while(i * q <= 10001) {
                isPrime[i*q] = false;
                q++;
            }
        }
    }
}
