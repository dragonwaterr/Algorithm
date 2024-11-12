import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 교수님은 기다리지 않는다
    static int n, m;
    static int[] parent;
    static long[] dist;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 초기화 메소드
    static boolean init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if(n == 0 && m == 0) return false;

        parent = new int[n+1];
        dist = new long[n+1];
        for(int i = 1; i < n+1; i++) {
            parent[i] = i;
        }

        return true;
    }

    // 처리 메소드
    static void run() throws IOException {

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(order.equals("!")) {
                inData(a, b, Integer.parseInt(st.nextToken()));
            } else {
                outData(a, b);
            }
        }
    }

    // ! 처리
    static void inData(int a, int b, int w) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return;

        // b 집합의 루트가 a 집합 루트를 가리키게 하면 
        // 기존 b 집합에 속한 노드들과 a 집합에 속한 노드들의 무게 차를 구할 수 있다
        dist[bRoot] = dist[a] - dist[b] + w;
        parent[bRoot] = aRoot;
    }

    // ? 처리
    static void outData(int a, int b) {
        if(find(a) != find(b)) sb.append("UNKNOWN").append("\n");
        else sb.append(dist[b] - dist[a]).append("\n");
    }

    // 재귀적으로 부모를 따라가면서 가리킬 루트와 그 거리를 함께 갱신하는 메소드
    static int find(int x) {
        if(parent[x] == x) return x;

        int p = find(parent[x]);
        dist[x] += dist[parent[x]];
        return parent[x] = p;
    }

    // 정답 출력 메소드
    static void printAnswer() throws IOException {
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        while(init()) {
            run();
        }
        printAnswer();
    }
}