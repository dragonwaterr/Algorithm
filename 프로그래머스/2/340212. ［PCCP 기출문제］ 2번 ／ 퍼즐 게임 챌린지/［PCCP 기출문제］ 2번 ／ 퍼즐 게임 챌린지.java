import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        // 숙련도는 항상 양의 정수
        int maxLevel = 0;
        for(int i = 0; i < diffs.length; i++) {
            if(maxLevel < diffs[i]) maxLevel = diffs[i];
        }
        
        int minLevel = 987654321;
        int left = 1;
        int right = maxLevel;
        
        while(left <= right) {
            int level = (left + right) / 2;
            
            // ====== result 구하기 시작 =======
            long total = 0; // 걸린시간
            if(diffs[0] - level > 0) { // 첫 퍼즐은 이전이 없음
                total += times[0] * (diffs[0] - level);  
            } else {
                total += times[0];
            }
            
            for(int i = 1; i < diffs.length; i++) { 
                int reps = diffs[i] - level;
                if(reps > 0) { 
                    total += ((times[i] + times[i-1]) * reps) + times[i];
                } else {
                    total += times[i];
                }
            }    
            // ====== result 구하기 끝 =======
            
            // 숙련도 조절
            if(total <= limit) { // 현재 숙련도가 높다
                minLevel = Math.min(minLevel, level);
                right = level - 1;
            } else { // 현재 숙련도가 낮다
                left = level + 1;
            }
        }
        return minLevel;
    }
}