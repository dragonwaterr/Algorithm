import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Homework implements Comparable<Homework> {
        int deadline;
        int score;
        
        Homework(int deadline, int score) {
            this.deadline = deadline;
            this.score = score;
        }
        
        @Override
        public int compareTo(Homework o1) {
            return this.deadline > o1.deadline ?
                -1 : this.deadline == o1.deadline ?
                    this.score - o1.score : 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Homework[] homeworks = new Homework[n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            homeworks[i] = new Homework(deadline, score);
        }

        Arrays.sort(homeworks);
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int ptr = 0;
        int k = 1001;
        int answer = 0;
        
        while(--k > 0) {
            while(ptr < n && homeworks[ptr].deadline == k)
                pq.add(homeworks[ptr++].score);
            if(!pq.isEmpty())
                answer += pq.poll();
        }
        
        System.out.println(answer);
    }
}