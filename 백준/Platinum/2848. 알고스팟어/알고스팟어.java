import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
    // 알고스팟어
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] words = new char[101][11]; 
        int[] length = new int[101]; // 단어의 길이
        boolean[] exist = new boolean[27]; // 알파벳 등장여부 체크
        int alph = 0; // 등장한 알파벳 종류수
        int n = Integer.parseInt(br.readLine());

        for(int i = 1; i <= n; i++) {
            String str = br.readLine();
            length[i] = str.length(); 
            for(int j = 1; j <= str.length(); j++) {
                char c = str.charAt(j-1);
                words[i][j] = c;
                if(!exist[c-96]) {
                    exist[c-96] = true;
                    alph++;
                }
            }
        }

        // 그래프 구성
        int[] indegree = new int[27]; // 1~26 번 : a-z
        ArrayList<Integer>[] edges = new ArrayList[27];
        for(int i = 0; i < 27; i++) {
            edges[i] = new ArrayList<>();
        }

        for(int i = 1; i < n; i++) {
            int ptr = 0;
            int size = Math.min(length[i], length[i+1]);
            
            while(++ptr <= size) {
                int from = words[i][ptr] - 96;
                int to = words[i+1][ptr] - 96;
                
                if(from != to) {
                    edges[from].add(to);
                    indegree[to]++;
                    break;
                }
                    
                if(ptr == size && length[i] > length[i+1]) {
                    System.out.println("!");
                    return;
                }
            }
        }

        // 단어 순서 정렬
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();

        int cnt = 0;
        boolean multiple = false;
        for(int i = 1; i <= 26; i++) {
            if(exist[i] && indegree[i] == 0) {
                queue.offer(i);
                cnt++;
            }
            if(cnt > 1) multiple = true;
        }

        while(!queue.isEmpty()) {
            cnt = 0;
            int cur = queue.poll();
            sb.append((char)(cur + 96));

            for(int to : edges[cur]) {
                indegree[to]--;

                if(indegree[to] == 0) {
                    queue.offer(to);
                    cnt++;
                }
            }

            if(cnt > 1) multiple = true;
        }


        if(sb.length() != alph) { 
            System.out.println("!");
            return;
        }
        if(multiple) {
            System.out.println("?");
            return;
        }
        System.out.println(sb);

    }
}