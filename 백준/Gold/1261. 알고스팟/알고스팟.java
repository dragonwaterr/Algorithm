import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int row, col, crash;
        public Node(int row, int col, int crash) {
            this.row = row;
            this.col = col;
            this.crash = crash;
        }
    }

    static int[][] graph;
    static char[][] wall_info;

    static int bfs(int dx, int dy) {
        int[][] dt = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(1, 1, 0));
        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.row == dx && cur.col == dy) continue;

            for(int i = 0; i < 4; i++) {
                int nx = cur.row + dt[i][0];
                int ny = cur.col + dt[i][1];

                if(1 > nx || nx > dx || 1 > ny || ny > dy) continue;

                if (wall_info[nx][ny] == '0' && graph[nx][ny] > cur.crash) {
                    q.add(new Node(nx, ny, cur.crash));
                    graph[nx][ny] = Math.min(graph[nx][ny], cur.crash);
                    continue;
                }

                if(wall_info[nx][ny] != '0' && graph[nx][ny] > cur.crash + 1) {
                    q.add(new Node(nx, ny, cur.crash + 1));
                    graph[nx][ny] = Math.min(graph[nx][ny], cur.crash+1);
                }
            }
        }

        return graph[dx][dy];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        wall_info = new char[h+2][w+2];
        for(int i = 1; i <= h; i++) {
            String line = br.readLine();
            for(int k = 1; k <= w; k++) {
                wall_info[i][k] = line.charAt(k-1);
            }
        }

        int INF = Integer.MAX_VALUE;
        graph = new int[h+2][w+2];
        for(int i = 1; i <= h; i++)
            Arrays.fill(graph[i], INF);
        graph[1][1] = 0;

        bw.write(String.valueOf(bfs(h, w)));
        bw.flush();
        br.close();
        bw.close();
    }
}