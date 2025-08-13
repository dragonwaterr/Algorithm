import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 방 번호
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int m;
        int n = Integer.parseInt(br.readLine());
        int[] cost = new int[n];
        int[] answer = new int[50];
        
        // {비용, [해당 비용이 드는 숫자 리스트]}
        HashMap<Integer, List<Integer>> numberMap = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) { // 유효한 비용은 리스트 생성
            int val = Integer.parseInt(st.nextToken());
            cost[i] = val;
            numberMap.computeIfAbsent(val, k -> new ArrayList<>());
            numberMap.get(val).add(i);
        }
        for (Integer key : numberMap.keySet()) { // 모든 카드 리스트를 내림차순 정렬
            List<Integer> list = numberMap.get(key);
            if(!list.isEmpty())  { 
                list.sort(Collections.reverseOrder());
            }
        }
        
        m = Integer.parseInt(br.readLine());
        
        int len = 0;
        int remain = m;
        int cheapest = Collections.min(numberMap.keySet()); 

        // 첫자리 0 방지 
        if(!numberMap.get(cheapest).isEmpty() && numberMap.get(cheapest).get(0) == 0) {
            
            int secondaryMinCost = m;
            int minNumber = 0;
            for (int i = 1; i < cost.length; i++) {
                if(cost[i] <= secondaryMinCost) {
                    secondaryMinCost = cost[i];
                    minNumber = i;
                }
            }
            answer[0] = minNumber;
            remain -= secondaryMinCost;
            len++;
        }

        while(true) {
            // 뒤에 숫자 붙이기엔 돈 모자를 때 -> 남는 돈으로 앞자리 키우기
            if(cheapest > remain) { 
                incFront(answer, cost, remain, len);
                break;
            }
            int opt = numberMap.get(cheapest).get(0); // 최저비용 중 가장 큰 숫자
            answer[len++] = opt;
            remain -= cost[opt];
        }

        for (int i = 0; i < len; i++) {
            sb.append(answer[i]);
        }

        System.out.println(sb);
    }

    // 남은 돈으로 앞자리부터 가능한 크게 만들기
    static void incFront(int[] answer, int[] cost, int remain, int len) {
        int idx = 0;
        for (int j = 0; j < len; j++) { 
            int k = answer[j];
            int affordableMaxNum = k;
           
            for (int i = k+1; i < cost.length; i++) {
                if(cost[i] - cost[k] <= remain) {
                    affordableMaxNum = i;
                }
            }
            remain -= cost[affordableMaxNum] - cost[k];
            answer[idx++] = affordableMaxNum;
        }
    }

}
