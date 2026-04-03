import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        if(n < 4 || n % 4 != 0) {
            System.out.println(-1);
            return;
        }
        
        
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < n/4; k++) {
            for(int j = 0; j < 4; j++) {
                for(int i = 0; i < n/4; i++) {
                    sb.append(sq[j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
    
    static final String[] sq = new String[]{
        "aaab", 
        "dabb", 
        "ddcb", 
        "dccc"
    };
}