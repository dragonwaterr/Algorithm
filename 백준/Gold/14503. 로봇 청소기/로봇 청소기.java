import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 로봇 청소기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int answer = 0;
        // 1. 입력
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int vcm_y = Integer.parseInt(st.nextToken()); // 그래프의 현재 y좌표
        int vcm_x = Integer.parseInt(st.nextToken()); // 그래프의 현재 x좌표
        int direction = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][m]; // 0:청소되지 않은 빈칸, 1:벽, 2:청소한 빈칸(후처리)
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++)
                graph[i][j] = Integer.parseInt(st.nextToken());
        }

        // direction 변수가 나타내는 방향 : 0123 = 북동남서 
        // 회전 방향 : 반시계 = 0321 = 북서남동
        int[][] dd = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        // 2. 그래프 탐색
        while(true) {
            // 2-1. 현재 칸 체크 -> 안해도 되면 곧장, 청소 해야하면 청소하고 2-2로
            if(graph[vcm_y][vcm_x] == 0) {
                graph[vcm_y][vcm_x] = 2; // 청소 했으면 2로 처리
                answer++;
            }
            // 2-2. 주변 4칸에 먼지 하나도 없다면 현재 방향 기준으로

            // 주변 먼지 체크
            // 먼지가 있다면 flag 를 참으로
            boolean flag = graph[vcm_y+dd[0][0]][vcm_x+dd[0][1]] == 0;
            if(graph[vcm_y+dd[1][0]][vcm_x+dd[1][1]] == 0) flag = true;
            if(graph[vcm_y+dd[2][0]][vcm_x+dd[2][1]] == 0) flag = true;
            if(graph[vcm_y+dd[3][0]][vcm_x+dd[3][1]] == 0) flag = true;

            if(!flag) { // 주변에 먼지가 하나도 없다면
                int by = dd[(direction + 2) % 4][0]; // 후진할 y
                int bx = dd[(direction + 2) % 4][1]; // 후진할 x
                
                // ii) 뒤가 벽이면 청소한 먼지수 출력하고 종료
                if(graph[vcm_y+by][vcm_x+bx] == 1) { 
                    bw.write(String.valueOf(answer));
                    bw.flush();
                    return;
                }
                // i) 뒷방향 후진 할 수 있다면 후진하고 다시 2-1로.
                vcm_y += by;
                vcm_x += bx;
                continue;
            }
            
            // 2-3. 주변 4칸에 먼지 있다면 반시계 90도 회전하고
            // 0 -> 3 -> 2 -> 1 순서
            if(direction == 0) direction = 3;
            else direction -= 1;
            
            int dy = dd[direction][0]; // 청소기가 전진할 y
            int dx = dd[direction][1]; // 청소기가 전진할 x
            
            // 그 앞 칸이 더럽다면 전진하고 2-1번으로 돌아가고
            // 안더럽다면 그냥 2-1번으로 돌아가기
            if(graph[vcm_y+dy][vcm_x+dx] == 0) {
                vcm_y += dy;
                vcm_x += dx;
            }
        }
    }
}