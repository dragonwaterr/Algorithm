import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 치즈버거 만들기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] burger = br.readLine().split(" ");
        int A = Integer.parseInt(burger[0]); // 패티
        int B = Integer.parseInt(burger[1]); // 치즈

        if(A <= B)
            System.out.println(2*A-1);
        else { // A > B
            System.out.println(2*B+1);
        }
    }
}