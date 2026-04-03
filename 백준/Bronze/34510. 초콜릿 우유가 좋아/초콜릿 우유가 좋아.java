import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        long n = Long.parseLong(br.readLine());
        
        long h = 0L;
        if((n+2) % 2 == 0) {
            h = (n/2)*b + (n*c);
        } else {
            h = a + ((n/2) + 1)*b + (n*c);
        }
        System.out.println(h);
    }
}

// (n+2) % 2 == 0 -> (n/2) * B + n*C 
// (n+2) % 1 == 0 -> A + ((n/2) + 1)*B + n*C 