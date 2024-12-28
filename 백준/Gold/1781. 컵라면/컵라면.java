import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // 컵라면 
    static class Quiz {
        int deadline;
        int cups;
        Quiz(int deadline, int cups) {
            this.deadline = deadline;
            this.cups = cups;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Quiz[] quizzes = new Quiz[n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cups = Integer.parseInt(st.nextToken());
            quizzes[i] = new Quiz(deadline, cups);
        }

        Arrays.sort(quizzes,
            (o1, o2) -> o1.deadline > o2.deadline ? -1
                : o1.deadline == o2.deadline ? o2.cups - o1.cups : 1);

        PriorityQueue<Quiz> pq = new PriorityQueue<>(
            (o1, o2) -> o2.cups > o1.cups ? 1
                 : o1.cups == o2.cups ? o1.deadline - o2.deadline : -1);

        int ptr = 0; 
        int k = n+1; // 역행할 시간
        int answer = 0;
        while(--k > 0) { 
            while(ptr < n && quizzes[ptr].deadline >= k)
                pq.add(quizzes[ptr++]);

            if(!pq.isEmpty())
                answer += pq.poll().cups;
        }
        System.out.println(answer);
    }
}