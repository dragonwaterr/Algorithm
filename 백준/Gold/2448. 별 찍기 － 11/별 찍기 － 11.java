import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 별 찍기 - 11
    static char[][] stars;
    static int n;

    static void recur(int k, int start_y, int start_x) {
        if(k == 0) {
            stars[start_y][start_x] = '*';
            stars[start_y + 1][start_x - 1] = '*';
            stars[start_y + 1][start_x + 1] = '*';
            stars[start_y + 2][start_x + -2] = '*';
            stars[start_y + 2][start_x + -1] = '*';
            stars[start_y + 2][start_x + 0] = '*';
            stars[start_y + 2][start_x + 1] = '*';
            stars[start_y + 2][start_x + 2] = '*';
            return;
        }
        
        recur(k-1, start_y, start_x);
        recur(k-1, start_y + (3*(int)Math.pow(2, k-1)), start_x - (3*(int)Math.pow(2, k-1)));
        recur(k-1, start_y + (3*(int)Math.pow(2, k-1)), start_x + (3*(int)Math.pow(2, k-1)));
    }

    static void printStars() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 2*n-1; j++) {
                if(stars[i][j] == '\u0000') sb.append(" ");
                else sb.append(stars[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int baseLog(double x, int base) {
        return (int) (Math.log10(x) / Math.log10(base));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        stars = new char[n][2*n-1];
        int k = baseLog((double) n /3, 2);

        recur(k, 0, n-1);
        printStars();
    }
}