import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int[][] graph = new int[5][5];
        for(int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        ArrayDeque<Node> dq = new ArrayDeque<>();
        dq.add(new Node(r, c, 0));
        
        boolean[][] visited = new boolean[5][5];
        visited[r][c] = true;
        
        while(!dq.isEmpty()) {
            Node cur = dq.remove();
            
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dt[i][0];
                int ny = cur.y + dt[i][1];
                // 걷기
                if(isValid(graph, nx, ny) && notVisited(visited, nx, ny)) {  
                    if(graph[nx][ny] == 1) {
                        System.out.println(cur.dist+1);
                        return;
                    } 
                    visited[nx][ny] = true;
                    dq.add(new Node(nx, ny, cur.dist+1));
                }
                
                // 뛰기 : nx, ny 가 걷기랑 다를 수 있으니 초기화 필요
                int[] next = getDest(graph, cur.x, cur.y, i); // 다음 칸 구하기
                nx = next[0];
                ny = next[1];
                if(isValid(graph, nx, ny) && notVisited(visited, nx, ny)) {  
                    if(graph[nx][ny] == 1) { 
                        System.out.println(cur.dist+1);
                        return;
                    } 
                    visited[nx][ny] = true;
                    dq.add(new Node(nx, ny, cur.dist+1));
                }
            }
        }
        System.out.println(-1);
    }
    static int[] getDest(int[][] graph, int cx, int cy, int direction) {
        int dx = dt[direction][0];
        int dy = dt[direction][1];
        
        while(true) {
            int nx = cx + dx;
            int ny = cy + dy;
            
            while(isValid(graph, nx, ny)) {
                cx += dx;
                cy += dy;
                if(graph[cx][cy] == 7) {
                    return new int[]{cx, cy};
                }
                nx = cx + dx;
                ny = cy + dy;
            }
            return new int[]{cx, cy};
        }
    }
    static boolean notVisited(boolean[][] visited, int x, int y) {
        return !visited[x][y];
    }
    static boolean isValid(int[][] graph, int x, int y) {
        if(x < 0 || x > 4 || y < 0 || y > 4) return false;
        return graph[x][y] != -1;
    }
    static int[][] dt = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static class Node {
        int x, y, dist;
        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}