import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    // 구슬 탈출 3
    static int n, m;
    static int answer = 0;
    static String answerString = "";
    static boolean redIsInHole = false;
    static boolean blueIsInHole = false;
    static int[] dx = {-1, 0, 1, 0}; // 상좌하우 : 위쪽부터 반시계 회전
    static int[] dy = {0, -1, 0, 1};
    static String[] dir = {"U", "L", "D", "R"};
    static int[] redTempPos;
    static int[] blueTempPos;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        init();
        run();
        printAnswer();
    }

    // 초기화 메소드
    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];

        for(int i = 0; i < n; i++) {
            String inputLine = br.readLine();
            for(int j = 0; j < m; j++) {
                char c = inputLine.charAt(j);
                switch (c) {
                    case '#':
                        graph[i][j] = 1;
                        break;
                    case 'R':
                        redTempPos = new int[]{i, j, 0}; // redPos 에는 cnt 를 같이 심는다
                        break;
                    case 'B':
                        blueTempPos = new int[]{i, j};
                        break;
                    case 'O':
                        graph[i][j] = 2;
                        break;
                    default: // '.' -> graph[i][j] = 0;
                }
            }
        }
        br.close();
    }

    // 처리 메소드
    static void run() {
        answer = bfs();
    }

    // 구슬을 이동시킨다
    static int bfs() {
        ArrayDeque<int[]> redDeque = new ArrayDeque<>();
        ArrayDeque<int[]> blueDeque = new ArrayDeque<>();        
        ArrayDeque<String> redTrace = new ArrayDeque<>();
        
        redDeque.add(redTempPos.clone());
        blueDeque.add(blueTempPos.clone());
        redTrace.add("");

        while(!redDeque.isEmpty()) {
            int[] redPos = redDeque.remove();
            int[] bluePos = blueDeque.remove();
            String traces = redTrace.remove();

            // 10번 만에 빨간 구슬만 구멍에 넣지 못했다면 종료
            if(redPos[2] == 10) return -1;

            // 기울임 방향 = 상좌하우 : 위쪽부터 반시계 회전
            for(int i = 0; i < 4; i++) {
                // 기울임 방향을 보고 먼저 이동할 구슬 정하기
                String marbleMovingFisrt = getFirstMarble(redPos, bluePos, i);
                String curDirectionString = dir[i];

                if(marbleMovingFisrt.equals("red")) { // red 먼저 이동
                    redTempPos = move(redPos.clone(), bluePos.clone(), dx[i], dy[i], "red");
                    blueTempPos = move(bluePos.clone(), redTempPos.clone(), dx[i], dy[i], "blue");
                } else { // blue 먼저 이동
                    blueTempPos = move(bluePos.clone(), redPos.clone(), dx[i], dy[i], "blue");
                    redTempPos = move(redPos.clone(), blueTempPos.clone(), dx[i], dy[i], "red");
                }

                // 파란 구슬이 구멍에 들어갔다면 넘어가기
                if(blueIsInHole) {
                    blueIsInHole = false;
                    redIsInHole = false;
                    continue;
                }

                // 빨간 구슬만 구멍에 들어갔다면 종료
                if(redIsInHole) {
                    answerString = traces + curDirectionString;
                    return redTempPos[2]+1;
                }

                // 둘 중 뭐라도 하나 움직였다면 adq 에 넣는다
                if(redPos[0] != redTempPos[0] || redPos[1] != redTempPos[1]
                    || bluePos[0] != blueTempPos[0] || bluePos[1] != blueTempPos[1]) {
                    redTempPos[2]++;
                    redDeque.add(redTempPos);
                    blueDeque.add(blueTempPos);
                    redTrace.add(traces + curDirectionString);
                }
            }
        }
        return -1;
    }

    // color 에 해당하는 구슬의 이동 결과를 리턴한다
    static int[] move(int[] cur, int[] opp, int dx, int dy, String color) {

        while(true) {
            int nx = cur[0] + dx;
            int ny = cur[1] + dy;

            if(graph[nx][ny] == 0) { // 빈 공간 or 다른 구슬
                if(opp[0] == nx && opp[1] == ny) { // 가려는 곳에 다른 구슬이 있다면
                    return cur;
                } else { // 그냥 빈 공간이라면
                    cur[0] = nx;
                    cur[1] = ny;
                }

            } else if(graph[nx][ny] == 1) { // 벽
                return cur;
            } else { // 구멍
                cur[0] = nx;
                cur[1] = ny;

                if(color.equals("red")) redIsInHole = true;
                else blueIsInHole = true;
                return cur;
            }
        }
    }

    // 먼저 이동할 구슬의 색깔을 String 으로 리턴한다
    static String getFirstMarble(int[] red, int[] blue, int dir) {
        if(dir == 0) { // 상
            if(red[0] <= blue[0]) return "red";
            else return "blue";
        }
        if(dir == 1) { // 좌
            if(red[1] <= blue[1]) return "red";
            else return "blue";
        }
        if(dir == 2) { // 하
            if(red[0] >= blue[0]) return "red";
            else return "blue";
        }
        // 우
        if(red[1] >= blue[1]) return "red";
        else return "blue";
    }

    // 정답 출력 메소드
    static void printAnswer() {
        System.out.println(answer);
        if(answer != -1) System.out.println(answerString);
    }
}