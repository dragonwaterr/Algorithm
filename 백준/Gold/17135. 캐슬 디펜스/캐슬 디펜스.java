import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 캐슬 디펜스
    static final int[][] dt = {{0 ,-1}, {-1, 0}, {0, 1}}; // 좌상우 만 필요

    static int vision, answer, n, m, totalEnemies;
    static int[][] map;
    static boolean[][][] visited;

    static List<Integer> archers;
    static HashSet<Integer> enemies;

    static class Arrow {
        int x, y, dist;
        Arrow(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        vision = Integer.parseInt(st.nextToken());
        
        map = new int[n+1][m];
        archers = new ArrayList<>();
        answer = 0;
        totalEnemies = 0;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) totalEnemies++;
            }
        }

        comb(0, 0);
        System.out.println(answer);
    }

    static void comb(int depth, int start) {
        if(depth == 3) {
            int score = bfs();
            renewAnswer(score);
            return;
        }
        for(int i = start; i < m-2+depth; i++) {
            archers.add(i);
            comb(depth+1, i+1);
            archers.remove(depth);
        }
    }

    static int bfs() {
        Queue<Arrow> q1 = new ArrayDeque<>();
        Queue<Arrow> q2 = new ArrayDeque<>();
        Queue<Arrow> q3 = new ArrayDeque<>();

        int[][] board = new int[n+1][m];
        for (int i = 0; i < n+1; i++) {
            board[i] = map[i].clone();
        }

        int score = 0;
        int curEnemies = totalEnemies; 
        while(true) {
            enemies = new HashSet<>();
            visited = new boolean[3][n+1][m];

            q1.add(new Arrow(n, archers.get(0), 0));
            q2.add(new Arrow(n, archers.get(1), 0));
            q3.add(new Arrow(n, archers.get(2), 0));

            findEnemy(0, q1, board);
            findEnemy(1, q2, board);
            findEnemy(2, q3, board);

            score += enemies.size();
            curEnemies -= removeDeads(board); 
            moveEnemies(board);
            curEnemies -= removeInCastle(board); 

            if(curEnemies == 0) {
                break;
            }

            q1.clear();
            q2.clear();
            q3.clear();
        }
        return score;
    }

    static void moveEnemies(int[][] board) {
        for (int i = n; i >= 1; i--) {
            board[i] = board[i-1].clone();
        }
        Arrays.fill(board[0], 0);
    }

    static int removeDeads(int[][] board) {
        for(Integer num : enemies) {
            int[] pos = toPos(num);
            board[pos[0]][pos[1]] = 0;
        }
        return enemies.size();
    }

    static int removeInCastle(int[][] board) {
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if(board[n][i] == 1) {
                cnt++;
                board[n][i] = 0;
            }
        }
        return cnt;
    }

    static void renewAnswer(int score) {
        answer = Math.max(answer, score);
    }

    static void findEnemy(int num, Queue<Arrow> q, int[][] board) {
        while(!q.isEmpty() && q.peek().dist < vision) {
            Arrow cur = q.poll(); 

            for (int i = 0; i < 3; i++) {
                int nx = cur.x + dt[i][0];
                int ny = cur.y + dt[i][1];

                if(nx < 0 || nx > n || ny < 0 || ny > m-1) continue;
                if(visited[num][nx][ny]) continue;
                visited[num][nx][ny] = true;

                if(board[nx][ny] == 1) {
                    enemies.add(toNum(nx, ny));
                    return;
                }

                q.add(new Arrow(nx, ny, cur.dist + 1));
            }
        }
    }

    static int toNum(int x, int y) { 
        return (x * 100) + y;
    }

    static int[] toPos(int num) {
        return new int[]{num/100, num%100};
    }
}