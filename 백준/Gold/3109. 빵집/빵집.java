import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 빵집
    static int r, c, answer;
    static char[][] graph;
    static boolean[][] visited;

    static final int[] diretion = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        answer = 0;

        graph = new char[r + 1][c + 1];
        visited = new boolean[r + 1][c + 1];

        for (int i = 1; i <= r; i++) {
            System.arraycopy(br.readLine().toCharArray(), 0, graph[i], 1, c);
        }

        for(int i = 1; i <= r; i++) {
            dfs(i,1);
        }

        System.out.println(answer);
    }

    static boolean dfs(int cx, int cy) {
        visited[cx][cy] = true;

        if(cy == c) { 
            answer++;
            return true;
        }

        for(int i = 0; i < 3; i++) {
            int nx = cx + diretion[i];
            int ny = cy + 1;

            if(isValidPath(nx, ny) && dfs(nx, ny)) {
                return true;
            }
        }

        return false;
    }

    static boolean isValidPath(int nx, int ny) {
        if(outOfBounds(nx, ny)) return false;
        if(graph[nx][ny] == 'x') return false;
        if(visited[nx][ny]) return false;
        return true;
    }

    static boolean outOfBounds(int nx, int ny) {
        return nx > r || nx < 1 || ny > c || ny < 1;
    }
}
