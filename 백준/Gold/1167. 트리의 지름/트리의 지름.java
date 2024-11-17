import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    // 트리의 지름 (1167번)
    static int N;
    static ArrayList<int[]>[] arr;
    static boolean[] visit;
    static int max = 0;
    static int deep_node = 0;

    static void dfs(int start, int sum) {
        visit[start] = true;

        for(int[] now : arr[start]) {
            if(!visit[now[0]]) { // now[0] = start 노드와 연결된 노드의 번호 / now[1] = 그 가중치
                dfs(now[0], sum + now[1]);
            }
        }

        if(max < sum) {
            max = sum;
            deep_node = start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new ArrayList[N+1];

        for(int i = 1; i < N+1; i++) {
            arr[i] = new ArrayList<>();
        }

        visit = new boolean[N+1];

        for(int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);

            for(int j = 1; j < input.length-1; j += 2) {
                int b = Integer.parseInt(input[j]);
                int c = Integer.parseInt(input[j+1]);

                arr[a].add(new int[]{b, c});
                arr[b].add(new int[]{a, c});

                if(Integer.parseInt(input[j+2]) == -1) break;
            }
        }
        
        dfs(1, 0); // 시작노드 1 (루트노드)

        Arrays.fill(visit, false);
        dfs(deep_node, 0); // 시작노드 deep_node(루트에서 가장 먼 노드)

        System.out.println(max);
    }
}