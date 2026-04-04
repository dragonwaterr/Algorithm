import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        long B = Long.parseLong(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        HashMap<String, Long> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            Long val = Long.parseLong(st.nextToken());
            map.put(name, val);
        }
        
        for(int i = 0; i < m; i++) {
            String str = br.readLine();
            B-= map.get(str);
        }
        
        if(B < 0) {
            System.out.println("unacceptable");
        } else {
            System.out.println("acceptable");
        }
    }
}