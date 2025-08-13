import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr;
    static int N;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        br.close();
        arrange(0);
        System.out.println(count);
    }

    static void arrange(int depth) {

        if(depth == N) {
            count++;
            return;
        }

        for(int i = 0; i < N; i++) {
            arr[depth] = i;

            if(check(depth)) {
                arrange(depth + 1);
            }
        }
    }

    static boolean check(int depth) {
        for (int i = 0; i < depth; i++) {
            if (arr[depth] == arr[i]) {
                return false;
            } 
            if (depth - i == Math.abs(arr[depth] - arr[i])) {
                return false;
            }
        }
        return true;
    }
}