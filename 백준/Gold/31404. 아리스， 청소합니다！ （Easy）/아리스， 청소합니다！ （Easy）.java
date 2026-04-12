import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int sd = Integer.parseInt(st.nextToken());
        
        Node node = new Node(sx, sy, sd);
        
        int[][] ruleA = new int[H][W];
        int[][] ruleB = new int[H][W];
        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) ruleA[i][j] = line.charAt(j) - '0';
        }
        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) ruleB[i][j] = line.charAt(j) - '0';
        }
        
        int[][] dust = new int[H][W];
        
        int cnt = 0;
        int dist = 0;
        
        HashMap<Integer, Integer> visitMap = new HashMap<>();
        int[][] ref = ruleB;
        
        while(true) {
            boolean clear = false;
            
            int cx = node.x;
            int cy = node.y;
            int cd = node.d;
            
            if(cx > H-1 || cx < 0 || cy > W-1 || cy < 0) break;
            
            ref = ruleB;
            if(dust[cx][cy] == 0) {
                dust[cx][cy] = 1;
                clear = true;
                ref = ruleA;
                visitMap.clear();
                dist += cnt;
                cnt = 0;
            }
            
            if(!clear) {
                int key = transKey(H, W, cx, cy, cd);
                visitMap.put(key, visitMap.getOrDefault(key , 0) + 1);
                if(visitMap.get(key) == 2) break;
            }
            
            cd = getDir(cd, ref[cx][cy]);
            node.x = cx + dt[cd][0];
            node.y = cy + dt[cd][1];
            node.d = cd;
            cnt++;
        }
        System.out.println(dist+1);
    }
    
    static int[][] dt = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    static int transKey(int H, int W, int x, int y, int d) {
        return d * (H * W) + (W * x) + y;
    }
    
    static int getDir(int curDir, int angle) {
        return (4 + curDir + angle) % 4;
    }
    
    static class Node {
        int x, y, d;
        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}