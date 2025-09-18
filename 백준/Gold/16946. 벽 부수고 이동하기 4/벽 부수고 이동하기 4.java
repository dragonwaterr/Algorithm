import java.io.*;
import java.util.*;

public class Main {
    // 벽 부수고 이동하기 4
    static int n, m;
    static int[][] map;
    static int[][] blanksMap;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static ArrayList<int[]> blankPos;
    static ArrayList<int[]> walls; // 모든 벽들의 위치를 담는다
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;


    // 초기화 메소드
    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        blanksMap = new int[n][m]; // 얘도 0 - 1 ? 1을 벽인지 빈칸인지 헷갈리지 않겠어 ? 벽은 -1 로 저장

        walls = new ArrayList<>();
        blankPos = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            String inputStr = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = inputStr.charAt(j) - '0';
                if(map[i][j] == 1) { // 벽이라면
                    walls.add(new int[]{i, j}); // 벽은 리스트에 미리 추가해둔다
                    blanksMap[i][j] = -1; // 빈칸 맵에는 벽을 -1 로 저장한다
                } else { // 빈칸이라면
                    blankPos.add(new int[]{i, j});
                }
            }
        }
    }

    // 처리 메소드
    static void solution() {

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        ArrayList<Integer> groupSize = new ArrayList<>();
        int groupNumber = 1;

        for(int[] blank : blankPos) {
            // 이미 그룹이 형성되었다면 pass
            if(blanksMap[blank[0]][blank[1]] > 0) continue;
            blanksMap[blank[0]][blank[1]] = groupNumber;
            int size = 1;

            dq.add(blank);
            while(!dq.isEmpty()) {
                int[] cur = dq.remove();
                int cx = cur[0];
                int cy = cur[1];
                
                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];

                    if (outOfBounds(nx, ny)) continue;
                    if(blanksMap[nx][ny] != 0) continue;
                    
                    blanksMap[nx][ny] = groupNumber;
                    dq.add(new int[]{nx, ny});
                    size++;
                }
            }

            groupSize.add(size);
            groupNumber++;
        }

        HashSet<Integer> set = new HashSet<>();
        for(int[] wall : walls) {

            int cx = wall[0];
            int cy = wall[1];

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(outOfBounds(nx, ny)) continue;
                if(blanksMap[nx][ny] == -1) continue;
                set.add(blanksMap[nx][ny]);
            }

            for(int n : set) {
                map[cx][cy] += groupSize.get(n-1);
            }

            map[cx][cy] %= 10;
            set.clear();
        }
    }

    // 유효 범위만 탐색하도록 : 0, 0 ~ n-1, m-1
    static boolean outOfBounds(int x, int y) {
        return x > n-1 || x < 0 || y > m-1 || y < 0;
    }

    // 정답 출력 메소드
    static void printAnswer() throws IOException {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        printAnswer();
    }
}