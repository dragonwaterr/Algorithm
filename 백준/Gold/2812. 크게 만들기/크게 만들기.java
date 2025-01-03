import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 크게 만들기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String num = br.readLine();

        int ptr = 0;
        int[] stack = new int[n];
        Arrays.fill(stack, -1);
        stack[ptr++] = num.charAt(0) - '0';

        int cnt = k;
        int idx = 1;
        
        Loop:
        for (; idx < num.length(); idx++) {
            int cur = num.charAt(idx) - '0';
            while (stack[0] != -1 && stack[ptr - 1] < cur) {
                stack[--ptr] = -1;
                cnt--;
                if (cnt == 0) break Loop;
            }
            stack[ptr++] = cur;
        }
        
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < ptr - cnt && stack[i] != -1) {
            sb.append(stack[i++]);
        }
        for (; idx < num.length(); idx++) {
            sb.append(num.charAt(idx)); 
        }
        System.out.println(sb);
    }
}