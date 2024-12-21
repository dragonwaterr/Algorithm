import java.io.*;
import java.util.*;

public class Main {
    // 열쇠
    private static Map<Character, Boolean> ownedKeyMap;
    private static ArrayDeque<int[]> queue;
    private static Set<int[]> entranceList;

    private static final int[] dx = {-1, 1, 0, 0}; // 상하좌우
    private static final int[] dy = {0, 0, -1, 1};

    private static int n, m, countDoc, wholeDocs, keyCount;
    private static char[][] graph;
    private static boolean[][][] visit;
    private static boolean[] keys;

    private static BufferedReader br;
    private static StringTokenizer st;

    // 초기화 메소드
    private static void init() throws IOException {
        queue = new ArrayDeque<>();
        entranceList = new HashSet<>();
        ownedKeyMap = new HashMap<>();

        // 문을 열 수 있는 키가 들어온다면 true 를 값으로 저장
        for(char c = 'A'; c <= 'Z'; c++) {
            ownedKeyMap.put(c, false);
        }

        wholeDocs = 0;
        countDoc = 0;
        keyCount = 0;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new char[n][m];
        visit = new boolean[n][m][27]; 

        // graph 입력받기
        for (int i = 0; i < n; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        // 문서 숫자와, Map 에 문을 key 로 넣는다
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = graph[i][j];
                if (ch == '$') {
                    wholeDocs++;
                }
            }
        }

        // 가지고 있는 열쇠들의 정보를 추가한다
        char[] initKeys = br.readLine().toCharArray();
        if(initKeys[0] != 48) { // '0' 입력이 아니라면
            for(char c : initKeys) { // 가지고 있는 열쇠에 대한 value 는 true 저장
                ownedKeyMap.put((char)(c-32), true);
                keyCount++;
            }
        }
    }

    // 처리 메소드
    private static int solution() {
        Loop1 : while(true) {
            entranceList.clear();

            // 들어갈 수 있는 입구를 다시 다 찾고 entrance 에 넣는다
            for (int i = 0; i < n; i++) {
                int cnt = i == 0 || i == n-1 ? 1 : m-1;

                for (int j = 0; j < m; j += cnt) {
                    char ch = graph[i][j];

                    // 처음 발견한 열쇠라면 입구를 처음부터 다시 찾기
                    if('a' <= ch && ch <= 'z') {
                        if(!ownedKeyMap.get((char)(ch-32))) {
                            ownedKeyMap.put((char)(ch-32), true);
                            keyCount++;
                            continue Loop1;
                        }
                        entranceList.add(new int[]{i, j});
                    } else if(ch == '.') {
                        entranceList.add(new int[]{i, j});
                    } else if(ch == '$') {
                        graph[i][j] = '.';
                        if(countDoc++ == wholeDocs) {
                            return countDoc;
                        }
                        entranceList.add(new int[]{i, j});
                    } else if('A' <= ch && ch <= 'Z' && ownedKeyMap.get(ch)) {
                        entranceList.add(new int[]{i, j});
                    }
                }
            }

            // 유효한 입구를 모두 큐에 넣는다
            for(int[] entrance : entranceList) {
                queue.add(entrance);
                visit[entrance[0]][entrance[1]][keyCount] = true;
            }

            // 이 루프가 정상적으로 종료된다면, 모든 경우를 다 탐색해본것
            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = current[0] + dx[i];
                    int ny = current[1] + dy[i];

                    if (nx > n-1 || nx < 0 || ny > m-1 || ny < 0 || graph[nx][ny] == '*') continue;
                    if (visit[nx][ny][keyCount]) continue;

                    visit[nx][ny][keyCount] = true;
                    char ch = graph[nx][ny];

                    // 열쇠
                    if ('a' <= ch && ch <= 'z') {
                        // 처음 얻은 열쇠라면 visit 초기화
                        if(!ownedKeyMap.get((char)(ch-32))) {
                            ownedKeyMap.put((char)(ch-32), true);
                            keyCount++;
                            queue.clear();
                            continue Loop1;
                        }
                        // 이미 있는 열쇠라면
                        else queue.offer(new int[]{nx, ny});
                    } 
                    // 빈 칸 + 열쇠 있는 문
                    else if (ch == '.' || ('A' <= ch && ch <= 'Z' && ownedKeyMap.get(ch))) { 
                        queue.offer(new int[]{nx, ny});
                    } 
                    // 문서 훔친다
                    else if(ch == '$') { 
                        graph[nx][ny] = '.';
                        queue.add(new int[]{nx, ny});
                        // 모든 문서를 다 찾았다면
                        if(countDoc++ == wholeDocs) return countDoc;
                    }
                }
            }
            return countDoc;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        int rep = Integer.parseInt(st.nextToken());
        while(rep --> 0) {
            init();
            sb.append(solution()).append("\n");
        }
        System.out.println(sb);
    }
}