import java.util.*;

class Solution {
    static int[][] dt = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static char[][] graph;
    static int col, row;
    
    // 지게차
    static void jigecha(String req) {
        char target = req.charAt(0);
        
        for(int i = 0; i < col; i++) {  
            for(int j = 0; j < row; j++) {
                if(graph[i][j] == target) {
                    bfs(target, i, j); // 접근 가능인지 체크
                }
            }
        }
        
        for(int i = 0; i < col; i++) {
            for(int j = 0; j < row; j++) {
                if(graph[i][j] == 'm') { // 마킹해둔 곳을 비운다
                    graph[i][j] = 'e';
                }
            }
        }
        
    }
    
    // 지게차 : 접근 가능 컨테이너인지 판단하기
    static void bfs(char target, int i, int j) {
        boolean canRemove = false;
        boolean[][] visited = new boolean[col][row];
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{i, j});

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int k = 0; k < 4; k++) {
                int nx = cur[0] + dt[k][0];
                int ny = cur[1] + dt[k][1];
                
                // 1. nxny 가 outOfBounds 라면 접근 가능한 컨테이너
                if(nx < 0 || ny < 0 || nx >= col || ny >= row) {
                    canRemove = true;
                    break;
                }
                
                // 2. nxny 가 이미 방문한 곳이라면 넘어가기
                if(visited[nx][ny]) continue;
                
                // 3. nxny 가 'e' 라면 visit true 처리 -> 탐색 진행
                if(graph[nx][ny] == 'e') {
                    visited[nx][ny] = true; 
                    q.add(new int[]{nx, ny});
                }
            }

            if(canRemove) { // 이번에 꺼낼 수 있는 자리는 마킹해두기
                graph[i][j] = 'm'; 
                return;
            }        
        }
    }
    
    // 크레인
    static void crane(String req) {
        char target = req.charAt(0);
        
        for(int i = 0; i < col; i++) {
            for(int j = 0; j < row; j++) {
                if(graph[i][j] == target) { // 꺼낸 자리는 "e" 로 채우기
                    graph[i][j] = 'e'; 
                }
            }
        }
    }
    
    // req 길이로 지게차 or 크레인 선택
    static void selectDevice(String req) {
        if(req.length() == 1) { // 지게차
            jigecha(req);
            return;
        }
        crane(req); // 크레인
    }
    
    
    public int solution(String[] storage, String[] requests) {
        // 초기화
        int answer = 0;
        col = storage.length;
        row = storage[0].length();
        graph = new char[col][row];
        for(int i = 0; i < col; i++) {
            graph[i] = storage[i].toCharArray();
        }
        
        // 요청 처리
        for(String target : requests) {
            selectDevice(target);
        }
        
        // 남은 컨테이너 수 계산
        for(int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if(graph[i][j] != 'e') { // not empty
                    answer++;
                }
            }
        }
        
        return answer;
    }
}