import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class GasStation implements Comparable<GasStation> {
        int dist;
        int fuel;
        GasStation(int dist, int fuel) {
            this.dist = dist;
            this.fuel = fuel;
        }
        @Override
        public int compareTo(GasStation o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<GasStation> distPq = new PriorityQueue<>();
        PriorityQueue<Integer> fuelPq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            distPq.add(new GasStation(d, f));
        }

        st = new StringTokenizer(br.readLine());
        int dest = Integer.parseInt(st.nextToken());
        int curFuel = Integer.parseInt(st.nextToken());
        distPq.add(new GasStation(dest, 0));

        int curPos = 0;
        int cnt = 0;
        while (!distPq.isEmpty()) {
            
            if(dest <= curPos + curFuel) // 마을까지 한번에 갈 수 있다면
                break;
            
            if(distPq.peek().dist > curPos + curFuel) { // 다음 젤가까운 주유소로도 못간다면
                if(!fuelPq.isEmpty()) { //  1. 주유소 하나 들렀다치고 다시 본다
                    curFuel += fuelPq.poll();
                    cnt++;
                } else { //  2. 먹을 연료가 없다 ? GG
                    cnt = -1;
                    break;
                }
            } else { // 이번 주유소를 갈 수 있다면 후보로 fuelPq 에 넣고, 거기까지 이동한다
               curFuel -= distPq.peek().dist - curPos;
               curPos = distPq.peek().dist;
               fuelPq.add(distPq.poll().fuel);
            }
        }
        System.out.println(cnt);
    }
}