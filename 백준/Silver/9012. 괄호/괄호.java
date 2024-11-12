import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();
        
        // 반복횟수를 입력받는다.
        for(int i=0; i<T; i++) {
            
            // 스택이 빌 때 생기는 오류에 대한 예외처리다. L19, L52~53
            // 1. 괄호의 갯수는 맞지만 '())('처럼 순서가 맞지 않는경우
            // 2. '(' 갯수 < ')' 갯수인 경우
            try {
                Stack st = new Stack();
                String vps = sc.nextLine();
                
                // boolean 변수 flag는 ')'가 먼저 들어왔을때 
                // NO 출력후 YES의 중복출력을 막기위함이다.
                boolean flag = true;
                
                for(int j=0; j<vps.length(); j++) {
                    char ch = vps.charAt(j);
                    
                    // 입력받은 첫글자가 ')'인 경우는 바로 NO출력한다.
                    if(vps.charAt(0) == ')') {
                        System.out.println("NO");
                        flag = false;
                        break;
                    }
                    // '(' 가 들어오면 push ')'가 들어오면 pop
                    if(vps.charAt(j) == '(') {
                        st.push(ch+"");
                    }
                    else if(vps.charAt(j) == ')') {
                        st.pop();
                    }
                }
                // ')'가 문자열에 먼저 들어온 경우, NO 출력후 YES도 출력하는 중복출력을 막기위해 
                // flag가 true인지도 확인하고 YES를 출력한다.
                if(flag && st.isEmpty()) {
                    System.out.println("YES");
                } else if(!(st.isEmpty())) {
                    System.out.println("NO");
                }
            } catch (EmptyStackException e) {
                System.out.println("NO");
            }
            }
    }
}
