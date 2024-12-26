import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    // 탑
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] tops = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < n+1; i++) {
            tops[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        sb.append(0).append(" ");

        for(int i = 2; i < n+1; i++) {

            while(tops[stack.peek()] < tops[i]) {
                stack.pop();
                if(stack.isEmpty()) {
                    sb.append(0).append(" ");
                    break;
                }
            }

            if(!stack.isEmpty())
                sb.append(stack.peek()).append(" ");

            stack.push(i);
        }

        System.out.println(sb);
    }
}