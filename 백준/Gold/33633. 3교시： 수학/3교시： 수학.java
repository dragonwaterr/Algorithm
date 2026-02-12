import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        if(n == 1) {
            sb.append(1).append("\n").append(1);
            System.out.println(sb);
            return;
        }


        Queue<Node> dq = new ArrayDeque<>();
        dq.add(new Node(2, n-1));  // N번째가 1이므로 (N-1)번째는 2

        Set<Long> firstNumbers = new HashSet<>();

        HashMap<Integer, HashSet<Long>> visited = new HashMap<>();
        visited.put(n-1, new HashSet<>());
        visited.get(n-1).add(2L);

        while(!dq.isEmpty()) {
            Node cur = dq.poll();

            int nextTurn = cur.turn - 1;  // 역방향 탐색

            if(nextTurn == 0) {
                firstNumbers.add(cur.num);
                continue;
            }

            // 이전 값 후보 1: cur.num * 2 (이전이 짝수였던 경우)
            Node even = new Node(cur.num * 2, nextTurn);
            if(even.num != 1 || !visited.getOrDefault(nextTurn, new HashSet<>()).contains(even.num)) {
                visited.computeIfAbsent(nextTurn, k -> new HashSet<>());
                visited.get(nextTurn).add(even.num);
                dq.add(even);
            }

            // 이전 값 후보 2: (cur.num - 1) / 3 (이전이 홀수였던 경우)
            if((cur.num - 1) % 3 == 0) {
                long prev = (cur.num - 1) / 3;
                if(prev == 1) continue;
                if(prev % 2 == 1) {  // 홀수여야 함
                    Node odd = new Node(prev, nextTurn);
                    if(!visited.getOrDefault(nextTurn, new HashSet<>()).contains(odd.num)) {
                        visited.computeIfAbsent(nextTurn, k -> new HashSet<>());
                        visited.get(nextTurn).add(odd.num);
                        dq.add(odd);
                    }
                }
            }
        }

        int k = firstNumbers.size();
        long[] answer = new long[k];
        int idx = 0;
        for(long num : firstNumbers) {
            answer[idx++] = num;
        }
        Arrays.sort(answer);
        sb.append(k).append("\n");

        for (int i = 0; i < k; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);

    }

    static class Node {
        long num;
        int turn;
        Node(long num, int turn) {
            this.num = num;
            this.turn = turn;
        }
    }
}
