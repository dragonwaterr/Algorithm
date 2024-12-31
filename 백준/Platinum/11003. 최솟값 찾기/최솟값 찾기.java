import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    // 최솟값 찾기
    static class node {
        int val;
        int idx;
        public node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayDeque<node> dq = new ArrayDeque<>();

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        br.close();

        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            while(!dq.isEmpty() && dq.peekLast().val > num) {
                dq.pollLast();
            }
            dq.addLast(new node(num, i));
            if(dq.peekFirst().idx < i-l+1) {
                dq.poll();
            }
            sb.append(dq.peekFirst().val).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}