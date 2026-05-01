import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        int N = message.length();

        HashSet<String> publicSet = new HashSet<>();
        ArrayList<String> spoList = new ArrayList<>();
        
        boolean[] isSpoRange = new boolean[N];
        String[] arr = message.split(" ");
        
        
        for(int i = 0; i < spoiler_ranges.length; i++) {
            Arrays.fill(isSpoRange, spoiler_ranges[i][0], spoiler_ranges[i][1]+1, true);
        }
        
        int idx = 0;
        for(String word : arr) {
            
            int length = word.length();
            boolean inRange = false;
            
            for(int i = idx; i < idx + length; i++) {
                if(isSpoRange[i]) {
                    inRange = true;
                    break;
                }
            }
            
            if(inRange) {
                spoList.add(word);
            } else {
                publicSet.add(word);
            }
                
            idx += length + 1; // +1로 공백 제거
        }
        
        for(String toOpen : spoList) {
            if(publicSet.contains(toOpen)) continue; 
            publicSet.add(toOpen);
            answer++;
        }
        
        return answer;
    }
}