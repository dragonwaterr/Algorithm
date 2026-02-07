import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = "0", b = "0";
        String num = br.readLine();
        
        int aLen = 1;
        
        while(num.length() >= aLen) { 
            
            int len = aLen;
            int idx = 0;
            a = b = num.substring(idx, idx + len);
            
            String next = a;
            
            while(true) { 
                String cur = next;
                String expected = itos(stoi(cur)+1);
                
                idx += len;                
                len = expected.length();                
                
                if(num.length() < idx + len) {
                    break;
                }
                
                next = num.substring(idx, idx + len);
                if(!next.equals(expected)) {
                    break;
                }
                
                b = next;
                
                if(idx + len == num.length()) { 
                    System.out.println(a + " " + b);
                    return;
                }
            }       
            aLen++;
        }
        System.out.println(a + " " + b);
    }
    
    static int stoi(String s) {
        return Integer.parseInt(s);
    }
    
    static String itos(int a) {
        return String.valueOf(a);
    }
}