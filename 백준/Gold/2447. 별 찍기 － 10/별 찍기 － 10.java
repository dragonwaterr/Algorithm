import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // 별 찍기 - 10
    static int n, k;
    static char[][] stars;
    static int[][] dt = {{0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
    static StringBuilder sb;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        stars = new char[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(stars[i], ' ');
        }
        
        stars[1][1] = '*';
        copy(1);
        printStars();
    }
    
    
    static void copy(int curW) {
        
        while(curW < n) {
            for (int i = 0; i < 7; i++) {
                int sx = 1 + curW * dt[i][0];
                int sy = 1 + curW * dt[i][1];

                for (int j = sx; j < sx + curW; j++) {
                    for (int k = sy; k < sy + curW; k++) {
                        stars[j][k] = stars[j - sx + 1][k - sy + 1];
                    }
                }
            }
            curW *= 3;
        }

    }
    
    static void printStars() {
        sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                sb.append(stars[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}