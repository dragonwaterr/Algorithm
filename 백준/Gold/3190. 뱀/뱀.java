import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    // 뱀
    static class Snake { // 뱀을 크기 1짜리 블럭단위로 나눠 정보를 저장
        int y_pos;
        int x_pos;
        int dir; // 방향
        Snake(int y_pos, int x_pos, int dir) {
            this.y_pos = y_pos;
            this.x_pos = x_pos;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n+2][n+2]; 
        for(int i = 0; i < n+1; i++) { // 외벽과 몸통은 2
            graph[0][i] = graph[n+1][i] = graph[i][0] = graph[i][n+1] = 2;
        }
        int k = Integer.parseInt(br.readLine());
        for(int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1; // 사과는 1
        }
        int L = Integer.parseInt(br.readLine());
        String[][] spin = new String[L+1][2];
        for(int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            spin[i][0] = st.nextToken();
            spin[i][1] = st.nextToken();
        }
        int answer = 0; // 출력
        int direction = 0; // 우(0) 로 시작
        int[][] dxy = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우하좌상(0123)
        int len = 1; // 뱀 길이
        int spinOrder = 0; // 회전 방향 정보를 arr로 담아서 순서 필요
        
        Queue<Snake> q = new ArrayDeque<>();
        q.add(new Snake(1, 1, 0)); // 크기 1짜리 뱀으로 시작

        while(true) { 
            answer++;
            // 머리의 방향 업데이트
            if(spin[spinOrder][0] != null && answer-1 == Integer.parseInt(spin[spinOrder][0])) {
                if(spin[spinOrder][1].equals("D")) direction = (direction + 1) % 4;
                else direction = (direction == 0) ? 3 : direction-1;
                spinOrder++;
                q.peek().dir = direction; // 머리의 방향이 바뀜
            }

            boolean flag = false; // 사과를 먹었을 때 꼬리 칸을 남기기 위한 플래그
            Snake prev = null; // 내 바로 앞 몸통의 정보
            int clen = len; // 반복문 기준으로 사용할 변수. len은 사과를 먹었을 때 변동이 생김

            for(int i = 0; i < clen; i++) {
                int ny = q.peek().y_pos + dxy[q.peek().dir][0];
                int nx = q.peek().x_pos + dxy[q.peek().dir][1];
                
                if(graph[ny][nx] == 1) { // 1. 머리가 사과를 먹는다
                    graph[ny][nx] = 0; 
                    flag = true;
                    len++;
                } else if(graph[ny][nx] == 2) { // 2. 벽이나 몸에 부딪힌다  
                    System.out.println(answer);
                    return;
                }

                Snake cur = q.poll();
                if(i == 0) q.add(new Snake(ny, nx, cur.dir));
                    // 회전을 하지 않는다면 머리가 따라갈 방향은 이전의 내방향
                else q.add(new Snake(ny, nx, prev.dir)); 
                    // 몸통이 따라갈 방향은 내 바로 앞 몸통의 방향
                graph[cur.y_pos][cur.x_pos] = 0;
                graph[ny][nx] = 2;

                prev = cur; // 이번 몸통의 정보를 prev에 저장한다 
                    // -> 뒷 몸통에 따라올 방향을 알려주기 위해
            }
            if(flag) {
                q.add(prev); // 뺐던 꼬리 다시 넣기
                graph[prev.y_pos][prev.x_pos] = 2;
            }
        }
    }
}