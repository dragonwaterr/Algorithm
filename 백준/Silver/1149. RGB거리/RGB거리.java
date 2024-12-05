import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        cost = new int[N][3];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        // 2행 ~ 마지막행까지 반복
        // 한 싸이클 : 한 행을 윗 행과 비교해 3칸 각각 최소값 정하기
        for (int i = 1; i < N; i++)
            MinCost(N, i);

        Arrays.sort(cost[N-1]);

        bw.write(cost[N-1][0] + "\n");
        bw.flush();
    }


    static void MinCost(int N, int TARGET) { // cost배열을 최소합 배열로 변환 -> 마지막 행 최솟값이 전체 최솟값.
        
        int[][] dx = {{1, 2}, {0, 2}, {0, 1}};

        for (int i = 0; i < 3; i++)
            cost[TARGET][i] += Math.min(cost[TARGET-1][dx[i][0]], cost[TARGET-1][dx[i][1]]);
    }
}