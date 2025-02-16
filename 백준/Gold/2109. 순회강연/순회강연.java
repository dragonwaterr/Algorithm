import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 순회강연
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());

        int n = Integer.parseInt(br.readLine());
        if(n == 0) {
            System.out.println(0);
            return;
        }

        int lastDay = 0;
        int[][] dNp = new int[n][2]; // 데드라인, 페이순으로 저장
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dNp[i][1] = Integer.parseInt(st.nextToken()); // 페이
            dNp[i][0] = Integer.parseInt(st.nextToken()); // 데드라인
            lastDay = Math.max(lastDay, dNp[i][0]);
        }

        Arrays.sort(dNp, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) { // 데드라인 늦은 순으로 정렬
                if(o1[0] == o2[0]) return 0;
                return o2[0] - o1[0];
            }
        });


        int ptr = 0;
        while(ptr < n && dNp[ptr][0] == lastDay) { // 데드라인이 lastDay 까지인 강의의 수
            ptr++;
        }

        int answer = 0;
        int start = 0;
        boolean[] visited = new boolean[n];
        while(lastDay > 0) {

            for(int i = start; i < n; i++) { // 가능한 애들까지만 추가
                if(lastDay > dNp[i][0]) {
                    start = i;
                    break;
                }
                if(!visited[i]) {
                    maxheap.add(dNp[i][1]);
                    visited[i] = true;
                }
            }

            if(!maxheap.isEmpty()) { // 들어온 것 중 최대 페이 고르기
                answer += maxheap.poll();
            }
            lastDay--;
        }

        System.out.println(answer);
    }
}