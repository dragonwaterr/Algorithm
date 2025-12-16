import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            String name = br.readLine();
            cnt = name.charAt(0) == 'C' ? cnt+1 : cnt;
        }
        System.out.println(cnt);
    }
}