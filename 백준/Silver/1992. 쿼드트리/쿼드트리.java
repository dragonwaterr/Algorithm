import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 쿼드트리
    static int n;
    static char[][] quad;
    static int[][] dt = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        quad = new char[n][n];

        for(int i = 0; i < n; i++) {
            quad[i] = br.readLine().toCharArray();
        }

        compress(n, 0, 0);
        System.out.println(sb);
    }

    static void compress(int width, int sx, int sy) {
        char pattern = quad[sx][sy];
        for(int i = sx; i < sx + width; i++) {
            for(int j = sy; j < sy + width; j++) {
                if(quad[i][j] != pattern) { // 4등분 
                    int nextWidth = width / 2;
                    sb.append("(");
                    for(int k = 0; k < 4; k++) {
                        compress(nextWidth, sx + nextWidth * dt[k][0], sy + nextWidth * dt[k][1]);
                    }
                    sb.append(")");
                    return;
                }
            }
        }
        sb.append(pattern);
    }

}