import java.util.*;
import java.io.*;

class Main {
    
    static class Path {
        char c;
        int index;
        char result;
        Path(char c, int index) {
            this.c = c;
            this.index = index;
            this.result = c;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        while(n-- > 0) {
            String str = br.readLine();
            parentheses(sb, str);
            sb.append("\n");
        }
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    static void parentheses(StringBuilder sb, String str) {
        int point = 0;
        
        if(str.equals("")) {
            sb.append(point).append("\n").append("").append("\n");
            return;
        }
        
        Stack<Path> stack = new Stack<>();
        Stack<Path> resultStack = new Stack<>();
        
        // 1차 처리
        int len = str.length(); 
        Path lastLetter = new Path(str.charAt(len-1), len-1);
        stack.push(lastLetter);
        resultStack.push(lastLetter);
        
        for(int i = len-2; i >= 0; i--) {
            Path cur = new Path(str.charAt(i), i); 
            resultStack.push(cur);
            
            if(stack.isEmpty()) {
                stack.push(cur);
                continue;
            }
            
            Path peek = stack.peek(); 
            if(peek.c == 'U') {
                if(cur.c == 'C') {
                    cur.result = '(';
                    peek.result = ')';
                    
                    stack.pop();
                    point++;
                    continue;
                }
                
                stack.push(cur);
                
                continue;
            }
            
            // stack.peek().c == 'C'
            stack.push(cur);
        }
        
        // stack 으로 2차 처리
        int remainSize = stack.size();
        for(int i = 0; i < remainSize/2; i++) {
            Path p1 = stack.pop();
            Path p2 = stack.pop();
            
            p1.result = '(';
            p2.result = ')';
            
            //CC
            if(p1.c == 'C' && p2.c == 'C') {
                point += 2;
                continue;
            }
            //UU
            if(p1.c == 'U' && p2.c == 'U') {
                point += 2;
                continue;
            }
            //UC
            point += 3;
        }
        
        sb.append(point).append("\n");
        
        for(int i = 0; i < len; i++) {
            Path p = resultStack.pop();
            sb.append(p.result);
        }
    }
}