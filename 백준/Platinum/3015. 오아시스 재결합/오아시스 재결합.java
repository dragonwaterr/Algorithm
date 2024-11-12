import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    // 오아시스 재결합
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> cntStack = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        
        // 첫번째 수 먼저 스택에 집어넣기
        stack.push(Integer.parseInt(br.readLine())); 
        cntStack.push(1);

        long relation = 0;
        int max = stack.peek();

        for(int i = 1; i < n; i++) {
            int cur = Integer.parseInt(br.readLine());

            // 나보다 작은거 뺄때는 카운트 스택을 볼 필요가 없음
            while(!stack.isEmpty() && cur > stack.peek()) {
                stack.pop();
                cntStack.pop();
                relation += 1;
            }

            if(max < cur) {
                max = cur;
            }

            // 바로 앞에 나보다 큰 사람이 있을때
            if(!stack.isEmpty() && cur < stack.peek()) {
                relation += 1;
                stack.push(cur);
                cntStack.push(1);
                continue;
            }

            // 앞에 나랑 똑같은 키의 사람이 있을 때
            if(!stack.isEmpty() && cur == stack.peek()) {
                // 연속된 같은 키 사람들 관계 추가
                int serialPeople = cntStack.peek();
                relation += serialPeople;
                cntStack.push(serialPeople+1);
                stack.push(cur);

                // 연속된 같은 키 사람들보다 앞에 보이는 더 큰 사람과의 관계 추가
                if(max > stack.peek()) {
                    relation += 1;
                }
                continue;
            }
            stack.push(cur);
            cntStack.push(1);
        }
        System.out.println(relation);
    }
}