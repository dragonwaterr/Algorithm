import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, cnt;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        cnt = 0;
        
        hanoi(n, 1, 2, 3);
        
        System.out.println(cnt);
        System.out.println(sb);
    }
    
    static void hanoi(int k, int from, int mid, int to) {
        if(k == 1) {
            sb.append(from + " " + to).append("\n");
            cnt++;
            return;
        }
        
        hanoi(k-1, from, to, mid);
        
        sb.append(from + " " + to).append("\n");
        cnt++;
        
        hanoi(k-1, mid, from, to);
    }
}