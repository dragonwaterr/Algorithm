import java.util.*;

class Solution {
    
    static int[][] seq;
    static int[] cor;
    static int answer;
    static int n;
    
    static HashSet<Integer> secretCode;
    
    // 1~n에서 5개를 선택하는 모든 조합 생성
    static void comb(int start, int depth) {
        // 5개를 다 선택했으면 검증
        if(depth == 5) {
            if(compareSet()) {
                answer++;
            }
            return;
        }
        
        // start부터 n까지 하나씩 선택
        for(int i = start; i <= n; i++) {
            secretCode.add(i);
            comb(i + 1, depth + 1);
            secretCode.remove(i);
        }
    }
    
    // 현재 선택된 secretCode가 모든 쿼리 조건을 만족하는지 확인
    static boolean compareSet() {
        for(int depth = 0; depth < seq.length; depth++) {
            int resp = 0;
            for(int i = 0; i < 5; i++) {
                if(secretCode.contains(seq[depth][i])) {
                    resp++;
                }
            }
            
            if(resp != cor[depth]) { // 틀린 경우
                return false;
            }  
        }
        return true;
    }
    
    public int solution(int n, int[][] q, int[] ans) {
        Solution.n = n;
        answer = 0;
        seq = new int[q.length][5];
        cor = new int[ans.length];
        
        secretCode = new HashSet<>();
        
        for(int i = 0; i < q.length; i++) {
            cor[i] = ans[i];
            for(int j = 0; j < 5; j++) {
                seq[i][j] = q[i][j];
            }
        }
        
        comb(1, 0); // 1부터 시작해서 5개 선택
        return answer;
    }
}