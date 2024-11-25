import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    // LCS 2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();
        int[][] LCS = new int[arr2.length + 1][arr1.length + 1];

        for(int i = 1; i < arr1.length + 1; i++) {
            for(int j = 1; j < arr2.length + 1; j++) {
                if(arr1[i-1] == arr2[j-1]) {
                    LCS[j][i] = LCS[j-1][i-1] + 1;
                } else {
                    LCS[j][i] = Math.max(LCS[j-1][i], LCS[j][i-1]);
                }
            }
        }

        // + 문자열 출력하기
        int i = arr2.length;
        int j = arr1.length;
        
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
        
        while(i > 0 && j > 0) {
                if(LCS[i][j] == LCS[i-1][j]) i--;
                else if(LCS[i][j] == LCS[i][j-1]) j--;
                else {
                    st.push(arr1[j-1]);
                    i--;
                    j--;
                }
        }

        while(!st.isEmpty())
            sb.append(st.pop());

        System.out.println(LCS[arr2.length][arr1.length]);
        String str = sb.toString();
        if(str.length() != 0) System.out.println(str);
    }
}