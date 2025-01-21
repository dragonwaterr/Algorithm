import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 종이의 개수
    static int n;
    static int[][] paper;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        paper = new int[n+1][n+1];
        answer = new int[3];
        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        slice(n, 1, 1);
        sb.append(answer[0]).append("\n").append(answer[1]).append("\n").append(answer[2]);
        System.out.println(sb);
    }

    static void slice(int width, int sx, int sy) {
        int pattern = paper[sx][sy];
        boolean toSlice = false;
        
        for(int i = sx; i < sx + width; i++) {
            for(int j = sy; j < sy + width; j++) {
                if(paper[i][j] != pattern) {
                    toSlice = true;
                    break;
                }
            }
        }

         // 9 등분
        if(toSlice) { 
            int nextWidth = width/3;
            for(int k = 0; k < 3; k++) {
                for(int l = 0; l < 3; l++) {
                    slice(nextWidth, sx + k*nextWidth, sy + l*nextWidth);
                }
            }
            return; // 9 등분을 했다면 현재 색종이 탐색은 종료
        }
        
        answer[pattern + 1]++; 
    }
    
}