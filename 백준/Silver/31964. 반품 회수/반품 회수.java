import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] pos = new int[N+1];
        for(int i = 1; i <= N; i++) {
            pos[i] =  Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int[] time = new int[N+1];
        for(int i = 1; i <= N; i++) {
            time[i] =  Integer.parseInt(st.nextToken());
        }
        
        int min = 0;
        min = Math.max(pos[N], time[N]);
        
        for(int i = N; i >= 1; i--) {
            int arrivalTime = min + pos[i] - pos[i-1];
            min = Math.max(arrivalTime, time[i-1]);
        }
        System.out.println(min);
    }
    
}