import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static final String WHITE = "WHITE"; //0
    static final String RED = "RED"; //1
    static final String BLUE = "BLUE"; //2

    static class Tile {
        String color;
        Deque<Horse> horses;

        public Tile(String color, Deque<Horse> horses) {
            this.color = color;
            this.horses = horses;
        }
    }

    static class Horse {
        int x, y;
        int number;
        int direction;

        public Horse(int x, int y, int number, int direction) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.direction = direction;
        }
    }


    // 0번 인덱스는 사용X, 우 좌 상 하 순서
    static int[][] dt = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    static int n, k;
    static Tile[][] grid;
    static Deque<Horse> horseDeque;
    static Deque<Horse> tempDeque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new Tile[n + 1][n + 1]; // 0행, 0열 사용 X

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                String color = getColorString(Integer.parseInt(st.nextToken()));
                grid[i][j] = new Tile(color, new ArrayDeque<>());
            }
        }

        horseDeque = new ArrayDeque<>();
        tempDeque = new ArrayDeque<>();
        for(int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            Horse horse = new Horse(x, y, i, direction);

            grid[x][y].horses.addLast(horse);

            horseDeque.addLast(horse);

            if(grid[x][y].horses.size() > 3) {
                System.out.println(0);
                return;
            }
        }

        for(int i = 1; i <= 1000; i++) {
            if(!move()) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }

    static String getColorString(int x) {
        if (x == 0) {
            return WHITE;
        } else if (x == 1) {
            return RED;
        } else {
            return BLUE;
        }
    }

    static int changeDircetion(int dir) {
        if(dir == 1) return 2;
        if(dir == 2) return 1;
        if(dir == 3) return 4;
        return 3;
    }

    // 조기종료 해야하면 false 리턴
    static boolean move() {

        boolean validMove = true;
        for(int i = 0; i < k; i++) {
            Horse horse = horseDeque.poll();

            // 말의 위치를 보고, 도착할 칸의 색에 맞게 처리
            int nx = horse.x + dt[horse.direction][0];
            int ny = horse.y + dt[horse.direction][1];

            // BLUE 처리
            if(nx < 1 || nx > n || ny < 1 || ny > n
                || grid[nx][ny].color == BLUE) {

                horse.direction = changeDircetion(horse.direction);
                nx = horse.x + dt[horse.direction][0];
                ny = horse.y + dt[horse.direction][1];

                // 방향을 돌렸는데, 그 칸이 또 그래프 바깥이거나 파란칸이라면 그냥 가만히 있는다
                if(nx < 1 || nx > n || ny < 1 || ny > n
                    || grid[nx][ny].color == BLUE) {
                    horseDeque.addLast(horse);
                    continue;
                }
            }

            // WHITE, RED 처리 : BLUE 를 한 번 하고와서 여기도 지나가야 하므로 else if XX
            if(grid[nx][ny].color == WHITE) {
                validMove = whiteOperation(nx, ny, horse);
            } else {
                validMove = redOperation(nx, ny, horse);
            }

            if(!validMove) return false;
            horseDeque.addLast(horse);
        }
        
        return true;
    }

    static boolean whiteOperation(int nx, int ny, Horse horse) {
        Deque<Horse> curDq = grid[horse.x][horse.y].horses;
        Deque<Horse> nextDq = grid[nx][ny].horses;

        // horse 를 옮기기전에 horse 위에 있는 말들을 먼저 데크에 옮겨담기
        while(curDq.size() >= 1 && curDq.peekLast() != horse) {
            tempDeque.addLast(curDq.pollLast());
        }

        // horse 를 다음 칸 말들의 맨 위로 이동
        curDq.pollLast();
        horse.x = nx;
        horse.y = ny;
        nextDq.addLast(horse);

        //임시 스택에 옮겨뒀던 horse 위에 말들도 다음 칸으로 이동
        while(!tempDeque.isEmpty()) {
            tempDeque.peekLast().x = nx;
            tempDeque.peekLast().y = ny;
            nextDq.addLast(tempDeque.pollLast());
        }

        if(nextDq.size() >= 4) {
            return false;
        }

        return true;
    }

    static boolean redOperation(int nx, int ny, Horse horse) {
        Deque<Horse> curDq = grid[horse.x][horse.y].horses;
        Deque<Horse> nextDq = grid[nx][ny].horses;

        while(curDq.size() >= 1 && curDq.peekLast() != horse) {
            tempDeque.addLast(curDq.pollLast());
        }

        while(!tempDeque.isEmpty()) {
            tempDeque.peekFirst().x = nx;
            tempDeque.peekFirst().y = ny;
            nextDq.addLast(tempDeque.pollFirst());
        }

        curDq.pollLast();
        horse.x = nx;
        horse.y = ny;
        nextDq.addLast(horse);

        if(nextDq.size() >= 4) {
            return false;
        }

        return true;
    }
}