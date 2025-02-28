import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 배달
    static class Minsik {
        int x, y;
        int direction;
        int delivery; // 배달 완료 번호 : 0 or 1 or 2
        int minute; // 누적 배달 시간

        public Minsik(int x, int y, int direction, int delivery, int minute) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.delivery = delivery;
            this.minute = minute;
        }
    }

    static int n, m;
    static char[][] graph;
    static boolean[][][][] visited;

    static int[][] dt = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; 

    static int answer;
    static char C_CNT = '1';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new char[n + 1][m + 1];
        visited = new boolean[3][4][n + 1][m + 1];

        int startX = 0, startY = 0;
        for (int i = 1; i <= n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                graph[i][j] = chars[j - 1];

                if(graph[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if(graph[i][j] == 'C') {
                    graph[i][j] = C_CNT++;
                }
            }
        }

        answer = bfs(startX, startY);
        System.out.println(answer);
    }

    static int bfs(int startX, int startY) {
        Queue<Minsik> dq = new ArrayDeque<>();

        for(int i = 0; i < 4; i++) {
            visited[0][i][startX][startY] = true;
            int nx = startX + dt[i][0];
            int ny = startY + dt[i][1];

            if (isValid(nx, ny, i, 0)) {
                visited[0][i][nx][ny] = true;
                dq.add(new Minsik(nx, ny, i, 0, 1));
            }
        }

        while(!dq.isEmpty()) {
            Minsik node = dq.poll();

            int cx = node.x;
            int cy = node.y;
            int delivery = node.delivery;
            int direction = node.direction;
            int minute = node.minute;

            if((graph[cx][cy] == '1' || graph[cx][cy] == '2') 
                && delivery == 0) { // 배달반영
                node.delivery = graph[cx][cy] - '0';
                delivery = node.delivery;
            }

            if(deliveryOver(cx, cy, delivery)) { //배달종료
                return minute;
            }

            for(int i = 0; i < 4; i++) {
                if(direction == i) continue; 

                int nx = cx + dt[i][0];
                int ny = cy + dt[i][1];

                if(isValid(nx, ny, i, delivery)) {
                    visited[delivery][i][nx][ny] = true;
                    dq.add(new Minsik(nx, ny, i, delivery, minute + 1));
                }

            }
        }

        return -1;
    }

    static boolean isValid(int nx, int ny, int nextDirection, int curDelivery) {
        if(outOfBounds(nx, ny)) return false;
        if(graph[nx][ny] == '#') return false;
        if(visited[curDelivery][nextDirection][nx][ny]) return false;

        return true;
    }

    static boolean deliveryOver(int nx, int ny, int curDelivery) {
        if(curDelivery == 1 && graph[nx][ny] == '2') return true;
        if(curDelivery == 2 && graph[nx][ny] == '1') return true;
        return false;
    }

    static boolean outOfBounds(int x, int y) {
        return x > n || x < 1 || y > m || y < 1;
    }
}