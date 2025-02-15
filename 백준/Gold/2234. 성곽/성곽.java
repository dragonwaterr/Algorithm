import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    // 성곽
    static class Tile {
        int x, y;
        boolean[] walls; // 서 북 동 남
        
        public Tile(int x, int y, int binary) {
            this.x = x;
            this.y = y;
            walls = new boolean[4];
            getWalls(binary);
        }

        void getWalls(int binary) {
            for(int i = 3; i >= 0; i--) {
                int num = (int)Math.pow(2, i);
                if(binary / num == 1) {
                    this.walls[i] = true;
                    binary -= num;
                }
            }
        }
    }

    static int m, n;
    static int global_label = 0;
    static Tile[][] graph;
    static int[][] labeled_graph;
    static boolean[][] visited;
    static HashMap<Integer, Integer> roomSize = new HashMap<>();

    static int[][] dt = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int answer1, answer2, answer3;

    static void dfs(Tile t) {
        roomSize.put(global_label, roomSize.getOrDefault(global_label, 0) + 1);

        visited[t.x][t.y] = true;
        labeled_graph[t.x][t.y] = global_label;

        for(int i = 0; i < 4; i++) {
            if(t.walls[i]) continue;

            int nx = t.x + dt[i][0];
            int ny = t.y + dt[i][1];

            if(nx < 0 || nx > m-1 || ny < 0 || ny > n-1) continue;
            if(visited[nx][ny]) continue;

            dfs(graph[nx][ny]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new Tile[m][n];
        labeled_graph = new int[m][n];
        visited = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int binary = Integer.parseInt(st.nextToken());
                graph[i][j] = new Tile(i, j, binary);
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    answer1++;
                    global_label++;
                    dfs(graph[i][j]);
                }
            }
        }

        System.out.println(answer1);

        // answer2 = ; // hashmap.get(1 ~ global_label) 하면서 최대값 저장
        for(int i = 1; i <= global_label; i++) {
            answer2 = Math.max(answer2, roomSize.get(i));
        }
        System.out.println(answer2);

        // answer3 = ; // m*n 을 돌면서 현재칸과 인접한 칸의 labeled_graph 값이 다르면 더하고 최대값 갱신
        answer3 = answer2;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < 4; k++) {
                    int nx = i + dt[k][0];
                    int ny = j + dt[k][1];
                    if(nx < 0 || nx > m-1 || ny < 0 || ny > n-1) continue;
                    if(labeled_graph[i][j] != labeled_graph[nx][ny]) {
                        answer3 = Math.max(answer3,
                            roomSize.get(labeled_graph[i][j]) + roomSize.get(labeled_graph[nx][ny]));
                    }
                }
            }
        }
        System.out.println(answer3);
    }
}