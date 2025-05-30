import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
                
        size = (1 << (int)(Math.log(n) / Math.log(2)) + 1) * 2 - 1;
        seg = new int[size + 1];
            
        int leafStart = size/2 + 1;
        st = new StringTokenizer(br.readLine());
        for(int i = leafStart; i < leafStart + n; i++) {
            seg[i] = Integer.parseInt(st.nextToken());
        }
        
        segInit(1);
        
        StringBuilder sb = new StringBuilder();
        for(int i = leafStart + m - 1; i < leafStart + n - m + 1; i++) {
            int brightest = getMaxOfRange(i - m + 1, i + m - 1);
            sb.append(brightest).append(" ");
        }
        
        System.out.println(sb.toString());
    }
    
    static int n, m, size;
    static int[] seg;
    
    static int segInit(int x) {
        if(x > size/2) return seg[x];
        return seg[x] = getMax(segInit(x*2), segInit(x*2 + 1));
    }
    
    static int getMaxOfRange(int left, int right) {
        int max = 0;
        while(left <= right) {
            max = getMax(max, getMax(seg[left], seg[right]));
            if(left % 2 == 1) max = getMax(max, seg[left++]);
            if(right % 2 == 0) max = getMax(max, seg[right--]);
            left/=2;
            right/=2;
        }
        return max;
    }
    
    static int getMax(int a, int b) {
        return Math.max(a, b);
    }
}