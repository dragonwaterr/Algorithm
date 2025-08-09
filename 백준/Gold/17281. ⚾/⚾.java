import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // ⚾
    static int n, answer; // 이닝 수, 최대 득점
    static int[][] hitting; // 타격 결과
    static int[] seq; // 타순

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        answer = 0;
        hitting = new int[n][9];
        seq = new int[9];
        boolean[] visited = new boolean[9];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                hitting[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        perm(0, visited);
        System.out.println(answer);
    }

    static void perm(int order, boolean[] visited) { 
        if(order == 3) { 
            perm(order + 1, visited);
            return;
        }
        if(order == 9) {
            playBall(); 
            return;
        }
        for(int i = 1; i < 9; i++) {
            if(!visited[i]) {
                visited[i] = true;
                seq[order] = i; 
                perm(order + 1, visited);
                visited[i] = false;
            }
        }
    }

    static void playBall() {
        int score = 0; 
        int hitterNum = 0; 

        for(int i = 0; i < n; i++) { 
            boolean[] base = new boolean[3]; 
            int out = 0; 

            while(true) { 
                int player = seq[hitterNum++]; 
                int result = hitting[i][player]; 
                int diff = cal(base, result); 
                score += diff;
                hitterNum %= 9;

                if(result == 0 && ++out == 3) {
                    break;
                }
            }
        }
        answer = Math.max(answer, score);
    }

    static int cal(boolean[] base, int result) {
        int score = 0;
        if(result == 4) {
            for (int i = 0; i < 3; i++) {
                score = base[i] ? score+1 : score;
            }
            Arrays.fill(base, false);
            return score + 1;
        }
        if(result == 3) {
            for (int i = 0; i < 3; i++) {
                score = base[i] ? score+1 : score;
                base[i] = false;
            }
            base[2] = true;
            return score;
        }
        if(result == 2) {
            score = base[2] ? score + 1 : score;
            score = base[1] ? score + 1 : score;
            base[2] = base[0];
            base[0] = false;
            base[1] = true;
            return score;
        }
        if(result == 1) {
            score = base[2] ? score + 1 : score;
            base[2] = base[1];
            base[1] = base[0];
            base[0] = true;
            return score;
        }
        return 0;
    }
}