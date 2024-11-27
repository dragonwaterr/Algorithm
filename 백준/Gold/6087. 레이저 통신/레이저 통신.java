import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int row, col, mirror, dir;
        public Node(int row, int col, int mirror, int dir) {
            this.row = row;
            this.col = col;
            this.mirror = mirror;
            this.dir = dir; 
        }
    }

    static char[][] graph;
    static int[][][] mirrors; 

    static boolean isMovable(Node cur) {
        int row = cur.row;
        int col = cur.col;
        if(row == 0 || row == graph.length - 1 || col == 0 || col == graph[0].length - 1) return false; // 그래프를 벗어남
        if(graph[row][col] == '*') return false; // 벽
        return mirrors[row][col][cur.dir] > cur.mirror; // 저장된 최소 꺾기 이하로 꺾음
    }

    static int bfs(int[] start, int[] destination) {
        int[][] dt = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        ArrayDeque<Node> q = new ArrayDeque<>();
        
        for(int[][] outterArr : mirrors) 
            for(int[] innerArr : outterArr)
                Arrays.fill(innerArr, 987654321);
        

        Arrays.fill(mirrors[start[0]][start[1]], 0); // 출발점

        for(int i = 0; i < 4; i++) {
            Node cur = new Node(start[0] + dt[i][0], start[1] + dt[i][1], 0, i);
            if(isMovable(cur)) {
                mirrors[cur.row][cur.col][cur.dir] = cur.mirror;
                q.add(cur);
            }
        }

        int min = 987654321;
        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.row == destination[0] && cur.col == destination[1]) {
                min = Math.min(min, cur.mirror);
            }

            for(int i = 0; i < 4; i++) {
                Node next = new Node(cur.row + dt[i][0], cur.col + dt[i][1], cur.mirror, i);
                if(i != cur.dir) next.mirror++; // 이전과 다른 방향이면 꺾기 횟수 증가
                if(isMovable(next)) {
                    mirrors[next.row][next.col][next.dir] = next.mirror;
                    q.add(next);
                }
            }
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        graph = new char[h+2][w+2];
        mirrors = new int[h+2][w+2][4];

        boolean isStartPos = true;
        int[] start = new int[2];
        int[] destination = new int[2];

        for(int i = 1; i <= h; i++) {
            String line = br.readLine();
            for(int k = 1; k <= w; k++) {
                graph[i][k] = line.charAt(k-1);
            }

            for(int j = 1; j <= w; j++) {
                if(graph[i][j] == 'C') {
                    if(isStartPos) {
                        start[0] = i;
                        start[1] = j;
                        isStartPos = false;
                    } else {
                        destination[0] = i;
                        destination[1] = j;
                    }
                }
            }
        }

        bw.write(String.valueOf(bfs(destination, start)));
        bw.flush();
        br.close();
        bw.close();
    }
}