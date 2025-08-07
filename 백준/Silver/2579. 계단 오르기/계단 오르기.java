import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int[] A;
    static int[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        A = new int[N + 1];
        D = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        // 시작점
        D[1] = A[1];

        // 2번째 계단의 최대값은 항상 1번째 계단, 2번째 계단을 밟는 경우입니다.
        if (N >= 2) {
            D[2] = A[1] + A[2];
        }

        for (int i = 3; i <= N; i++) {
            // 머리로는 이해가 가는데 이걸 내가 생각해내야지..
            D[i] = Math.max(D[i - 2], D[i - 3] + A[i - 1]) + A[i];
        }
        System.out.println(D[N]);
    }
}