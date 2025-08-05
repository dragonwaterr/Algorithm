import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 점프
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        long[][] path = new long[n][n];
        path[0][0] = 1;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if(path[i][j] == 0 || map[i][j] == 0) {
                    continue;
                }

                int jump = map[i][j];
                if(i + jump < n) { 
                    path[i + jump][j] += path[i][j];
                }
                if(j + jump < n) { 
                    path[i][j + jump] += path[i][j];
                }
            }
        }

        System.out.println(path[n-1][n-1]);
    }
}