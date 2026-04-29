import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int peopleCnt = timelogs.length;
        
        int[] schedulesTime = new int[schedules.length];
        for(int i=0; i < schedulesTime.length; i++) {
            String temp = String.valueOf(schedules[i]);
            schedulesTime[i] = Integer.valueOf(temp.substring(0, temp.length() / 2)) * 60 + Integer.valueOf(temp.substring(temp.length() / 2));
        }
        
        boolean[] servive = new boolean[peopleCnt];
        Arrays.fill(servive,true);
        for(int i=0; i < 7;i++){
            int nowDay = (startday - 1 + i) % 7;
            if(nowDay < 5) {
                for(int j = 0; j < peopleCnt; j++){
                    if(servive[j]) {
                        String temp = String.valueOf(timelogs[j][i]);
                        int now= Integer.valueOf(temp.substring(0, temp.length() / 2)) * 60 + Integer.valueOf(temp.substring(temp.length() / 2));
                        if(now>schedulesTime[j] + 10) {
                            servive[j] = false;
                        }
                    }
                }
                
            }
        }
        
        for(boolean s: servive) {
            if(s) answer++;
        }
        return answer;
    }
}
