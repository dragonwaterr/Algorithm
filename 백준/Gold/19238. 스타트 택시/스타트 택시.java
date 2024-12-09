import java.io.IOException;
import java.util.*;

public class Main {
    // 스타트 택시
    private static int n, m, answer;
    private static Taxi carriedTaxi; // 손님을 태운 택시 참조
    private static int destination; // carriedTaxi 의 목적지
    private static int[][] graph;
    private static boolean[][] visit;
    private static int[] dx;
    private static int[] dy;

    private static PriorityQueue<Taxi> pq;
    private static ArrayDeque<Taxi> deque;
    private static Map<Integer, Integer> passengers;

    private static class Taxi {
        int fuel;
        int x, y;
        int travelDepth;
        public Taxi(int fuel, int x, int y, int travelDepth) {
            this.fuel = fuel;
            this.x = x;
            this.y = y;
            this.travelDepth = travelDepth;
        }
    }

    // 읽기 메소드
    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 47) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    // 초기화 메소드
    private static void init() throws IOException {
        answer = 0;
        deque = new ArrayDeque<>();
        pq = new PriorityQueue<>((o1, o2) -> getKey(o1.x, o1.y) - getKey(o2.x, o2.y));
        passengers = new HashMap<>();

        // 좌상우하
        dx = new int[]{0, -1, 0, 1};
        dy = new int[]{-1, 0, 1, 0};

        n = read();
        m = read();
        graph = new int[n][n];

        int fuel = read();

        // 승객 입력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int input = read();
                if (input == 1) graph[i][j] = -1;
                else graph[i][j] = getKey(i, j);
            }
        }

        // startTaxi
        deque.add(new Taxi(fuel, read() - 1, read() - 1, 0));

        // 승객과 목적지 위치 등록
        for (int i = 0; i < m; i++) {
            passengers.put(getKey(read() - 1, read() - 1), getKey(read() - 1, read() - 1));
        }

        // 사방이 막혀있어 도달할 수 없는 승객이 있다면 조기 종료
        passengers.forEach((key, value) -> {
            int[] passengerPos = getPos(key);
            for (int k = 0; k < 4; k++) {
                int nx = passengerPos[0] + dx[k];
                int ny = passengerPos[1] + dy[k];

                if (outOfBounds(nx, ny)) continue;
                if (graph[nx][ny] != -1) break;

                // 조기 종료 메소드 호출
                if (k == 3) terminate(); 
            }
        });
    }

    // 좌표가 그래프 범위 벗어나면 true 리턴
    private static boolean outOfBounds(int x, int y) {
        return x > n-1 || x < 0 || y > n-1 || y < 0;
    }

    // 숫자 두 개 인덱스를 하나로 변환해 리턴
    private static int getKey(int x, int y) {
        return (n * x) + y;
    }

    // key 를 (x, y) 로 반환
    private static int[] getPos(int key) {
        return new int[]{key / n, key % n};
    }

    // 처리 메소드
    private static void solution() {
        while(passengers.size() > 0) {
            findPassenger();
            answer = driveToDestination();
            if(answer == -1) terminate();
        }
    }

    // 가장 가까운 승객을 찾는다
    private static void findPassenger() {
        Taxi startTaxi = deque.peek();

        // 택시 시작점에 승객이 있는 경우는 여기서 처리하고 종료
        if(passengers.containsKey(graph[startTaxi.x][startTaxi.y])) {
            carriedTaxi = startTaxi;
            destination = passengers.get(graph[startTaxi.x][startTaxi.y]);

            passengers.remove(graph[startTaxi.x][startTaxi.y]);
            deque.clear();

            return;
        }

        int stopDepth = 987654321;
        boolean foundPassenger = false;

        visit = new boolean[n][n];
        visit[startTaxi.x][startTaxi.y] = true;

        // 승객을 찾았다면, 이번 depth 까지만 진행하고 나가서 목적지로 가기 bfs 실행
        while(!deque.isEmpty()) {
            Taxi curTaxi = deque.remove();

            // 다음 뎁스가 나왔다면 종료
            if(curTaxi.travelDepth == stopDepth) break;

            for (int k = 0; k < 4; k++) {
                int nx = curTaxi.x + dx[k];
                int ny = curTaxi.y + dy[k];

                if(outOfBounds(nx, ny)) continue;
                if(visit[nx][ny]) continue;

                int nextTaxiKey = graph[nx][ny];
                if(nextTaxiKey == -1) continue;

                if(passengers.containsKey(nextTaxiKey) && curTaxi.fuel > 0) {
                    foundPassenger = true;
                    stopDepth = curTaxi.travelDepth + 1;
                    pq.add(new Taxi(curTaxi.fuel-1, nx, ny, 0));
                }

                if(curTaxi.fuel == 1) continue;

                visit[nx][ny] = true;
                deque.add(new Taxi(curTaxi.fuel-1, nx, ny, curTaxi.travelDepth+1));
            }
        }

        // 남아있는 연료로 어떠한 승객에게도 도달하지 못했다면 종료
        if(!foundPassenger) terminate();

        carriedTaxi = pq.remove();
        
        int carriedPassengerPos = getKey(carriedTaxi.x, carriedTaxi.y);
        destination = passengers.get(carriedPassengerPos);
        passengers.remove(carriedPassengerPos);

        deque.clear();
        pq.clear();
    }

    // 승객이 가야하는 목적지로 ㄱㄱ
    private static int driveToDestination() {
        visit = new boolean[n][n];

        Taxi startTaxi = carriedTaxi;
        visit[startTaxi.x][startTaxi.y] = true;
        deque.add(startTaxi);

        // 승객을 찾았다면, 이번 depth 까지만 진행하고 나가서 목적지로 가기 bfs 실행
        while(!deque.isEmpty()) {
            Taxi curTaxi = deque.remove();

            for (int k = 0; k < 4; k++) {
                int nx = curTaxi.x + dx[k];
                int ny = curTaxi.y + dy[k];

                if(outOfBounds(nx, ny)) continue;
                if(visit[nx][ny]) continue;
                if(graph[nx][ny] == -1) continue;

                if(graph[nx][ny] == destination && curTaxi.fuel > 0) {
                    int[] arrived = new int[]{nx, ny};
                    deque.clear();
                    pq.clear();

                    // 다음 손님을 태우러 가기위한 택시만 남겨둔다
                    deque.add(new Taxi(2 * (curTaxi.travelDepth + 1) + curTaxi.fuel-1, arrived[0], arrived[1], 0));
                    return deque.peek().fuel;
                }

                if(curTaxi.fuel == 1) continue;

                visit[nx][ny] = true;
                deque.add(new Taxi(curTaxi.fuel-1, nx, ny, curTaxi.travelDepth+1));
            }
        }
        return -1;
    }
    
    // 조기 종료 메소드
    private static void terminate() {
        System.out.println(-1);
        System.exit(0);
    }

    // 정답 출력 메소드
    private static void printAnswer() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        printAnswer();
    }
}