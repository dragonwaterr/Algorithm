import java.util.*;

class Solution {

    class Server {
        private int genTime;

        Server(int genTime) {
            this.genTime = genTime;
        }
        
        int getGenTime() {
            return genTime;
        }
    }
    
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        Queue<Server> q = new ArrayDeque<>(); // 서버 큐
        
        for(int i = 0; i < 24; i++) {
            
            int pp = players[i]; // 현재 사용자 수
            int curServers = q.size(); // 현재 증설된 서버 수
            int limit = m * (curServers + 1) - 1; // 현재 증설없이 수용 가능한 인원 
            
            while(limit < pp) { // 서버 증설이 필요하다면
                answer++;                
                pp -= m;
                q.add(new Server(i));
            }
            
            while(true) { // 이용시간 끝난 서버들은 반납
                
                if(q.size() == 0) break; // 더 반납할 서버 없으면 탈출
                
                Server curServer = q.peek();
                int curServerGenTime = curServer.getGenTime();
                
                if(i - curServerGenTime == k-1) { // 반납해야할 서버
                    q.poll();
                    continue;
                } 
                
                break; // 남은 서버들은 반납 필요 없으면 탈출
            }
        }
        
        return answer;
    }
    
}