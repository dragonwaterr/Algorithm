import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int maxX = 1, maxY = 1;
    static int[][] room;
    static int[][] dt = {{-1, 0}, {1, 0}, {0, 1}, {0, 0}, {0, -1}};
    
    static ClassRoom[] baddiest = new ClassRoom[5];
    
    static class ClassRoom {
        int col;
        ClassRoom(int col) {
            this.col = col;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        
        room = new int[5][N+1];
        
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine()); 
            int query = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            
            if(query == 1) {
                int col = Integer.parseInt(st.nextToken());
                bad(row, col);
            } else if(baddiest[row] == null) {
                sb.append(1).append("\n");
            } else {
                sb.append(baddiest[row].col).append("\n");
            }
        }
        
        int max = 0;
        for(int i = 4; i >= 1; i--) {
            int j = baddiest[i] == null ? 1 : baddiest[i].col;
            int curVal = room[i][j];
            if(max <= curVal) {
                max = curVal;
                maxX = i;
                maxY = j;
            } 
        }
        sb.append(maxX).append(" ").append(maxY);
        System.out.println(sb);
    }   
    
    // 공사
    static void bad(int cx, int cy) {
        
        for(int i = 0; i < 5; i++) {
            int nx = cx + dt[i][0];
            int ny = cy + dt[i][1];
            
            if(nx < 1 || nx > 4 || ny < 1 || ny > N) continue;
            
            room[nx][ny]++;
            renewBaddiest(nx, ny);
        }
    } 
    
    // x층의 오량반 업데이트
    static void renewBaddiest(int x, int y) {
        if(baddiest[x] == null) {
            baddiest[x] = new ClassRoom(y);
            return;
        }
        
        ClassRoom cur = baddiest[x];
        if(room[x][cur.col] > room[x][y]) 
            return;
        if(room[x][cur.col] == room[x][y]) {
            cur.col = cur.col > y ? y : cur.col;
            return;
        }
        baddiest[x].col = y;
    }
}