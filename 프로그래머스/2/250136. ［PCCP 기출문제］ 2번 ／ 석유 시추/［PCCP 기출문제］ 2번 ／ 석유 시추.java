import java.util.*;

class Solution {
    
    public int solution(int[][] land) {
        int max = 0;
        int row = land.length;
        int col = land[0].length;
        
        // parent 배열 초기화
        int[] parent = new int[row * col];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        
        boolean[][] visit = new boolean[row][col];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        
        int[] colCounts = new int[col]; // 열 마다 캘 수 있는 석유 총량 저장
        Queue<int[]> q = new LinkedList<>(); // bfs 를 위한 큐
        Set<Integer> set = new HashSet<>();
        
        // 먼저 bfs 로 석유 다 탐색하고 또 시추 로직을 수행했는데, 이걸 동시에 해야 시간초과 안날듯
        for(int j = 0; j < col; j++) {
            for(int i = 0; i < row; i++) { 
                
                // 석유 칸이 아님
                if(land[i][j] == 0) continue;
                
                // 석유 칸인데 한번도 확인한적이 없는 칸 
                if(!visit[i][j]) {
                    
                    // 이 석유 덩어리의 루트 인덱스
                    int rootIndex = stretch(i, j, col); 
                    
                    int count = 0;
                    q.add(new int[]{i, j});
                    visit[i][j] = true; 
                    
                    // 같은 덩어리에 속하는 모든 칸 조사, 크기 구하기
                    while(!q.isEmpty()) {
                        int[] cur = q.remove();
                        int cx = cur[0];
                        int cy = cur[1];
                        count++;
                        
                        int cIndex = stretch(cx, cy, col);
                        int cParent = find(parent, cIndex);
                        
                        if(rootIndex != cParent) {
                            union(parent, rootIndex, cParent);
                        }
                        
                        for(int k = 0; k < 4; k++) {
                            int nx = cx + dx[k];
                            int ny = cy + dy[k];

                            if(outOfBounds(nx, ny, row, col)) continue;
                            if(visit[nx][ny]) continue;
                            if(land[nx][ny] == 0) continue;

                            q.add(new int[]{nx, ny});
                            visit[nx][ny] = true;
                        }
                    }
                    
                    // 덩어리 조사 끝나면 land 의 루트 위치에 덩어리 크기저장
                    land[i][j] = count;
                    // 루트를 set 에 추가
                    set.add(stretch(i, j, col));
                    continue;
                }
                
                // 이미 조사한적이 있는 덩어리를 만나면 그 덩어리의 루트를 set 에 추가
                int index = find(parent, stretch(i, j, col)); 
                set.add(index);
            }
            
            // 하나의 열 조사가 끝나면 만났던 모든 덩어리의 크기를 colCounts 에 누적합
            for(Integer root : set) {
                int[] pos = deStretch(col, root);
                colCounts[j] += land[pos[0]][pos[1]];
            }
            // 다음 열 조사를 위해 set 비우기
            set.clear();
        }
        
        // 모든 열 조사 끝났다면 최대값 구하기
        for(Integer count : colCounts) {
            max = count > max ? count : max;
        }
        
        return max;
    }
    
    // x 의 루트찾기
    static int find(int[] parent, int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }
    
    // a 로 b 의 루트를 병합
    static void union(int[] parent, int a, int b) {
        int pb = find(parent, b);
        parent[pb] = parent[a];
    }
    
    // 한 줄로 만들기
    static int stretch(int row, int col, int m) {
        return (row * m) + col;
    }
    
    // 다시 2차원 좌표로 원복
    static int[] deStretch(int col, int index) {
        return new int[]{index / col, index % col};
    }
    
    // 다음 볼 칸이 탐색 범위를 벗어나면 true 를 리턴하는 메소드
    static boolean outOfBounds(int nrow, int ncol, int row, int col) {
        return nrow > row-1 || nrow < 0 || ncol < 0 || ncol > col-1;
    }
}