import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final String output = "Problem ";
    static final int INF = 156250;

    static int[][] minCoin;
    static int[][] graph;
    
    static class Link {
        int x, y;

        public Link(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        int rep = 1;

        while(true) {
            int n = Integer.parseInt(br.readLine());

            if(n == 0) break;

            minCoin = new int[n][n];
            graph = new int[n][n];

            for(int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    minCoin[i][j] = INF;
                }
            }

            sb.append(output).append(rep++).append(": ");
            sb.append(bfs(n)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    static int bfs(int n) {
        int[][] dt = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        ArrayDeque<Link> dq = new ArrayDeque<>();
        dq.add(new Link(0, 0));
        minCoin[0][0] = graph[0][0];

        while(!dq.isEmpty()) {
            Link cur = dq.poll();

            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dt[i][0];
                int ny = cur.y + dt[i][1];

                if(nx < 0 || nx > n-1 || ny < 0 || ny > n-1) continue;
                if(minCoin[nx][ny] <= minCoin[cur.x][cur.y] + graph[nx][ny]) continue;

                minCoin[nx][ny] = minCoin[cur.x][cur.y] + graph[nx][ny];
                dq.add(new Link(nx, ny));
            }
        }

        return minCoin[n-1][n-1];
    }
}