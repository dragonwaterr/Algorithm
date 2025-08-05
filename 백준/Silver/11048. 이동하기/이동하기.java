import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 이동하기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] maze = new int[n+2][m+2];
        
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                maze[i][j] += Math.max(Math.max(maze[i-1][j], maze[i-1][j-1]), maze[i][j-1]);
            }
        }
        System.out.println(maze[n][m]);

    }
}
