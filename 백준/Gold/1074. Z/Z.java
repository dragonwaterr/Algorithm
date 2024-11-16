import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()); //행
        int c = Integer.parseInt(st.nextToken()); //열
        int size = (int) Math.pow(2, N); //한 변의 사이즈

        find(size, r, c);
        System.out.println(count);
    }

    static void find(int size, int r, int c) {
        if(size == 1)
            return;

        // (r, c)가 2사분면에 속한다면
        if(r < size/2 && c < size/2) {
            find(size/2, r, c);
        }
        // (r, c)가 1사분면에 속한다면
        else if(r < size/2 && c >= size/2) {
            count += size * size / 4;
            find(size/2, r, c - size/2);
        }
        // (r, c)가 3사분면에 속한다면
        else if(r >= size/2 && c < size/2) {
            count += (size * size / 4) * 2;
            find(size/2, r - size/2, c);
        }
        else { // (r, c)가 4사분면에 속한다면
            count += (size * size / 4) * 3;
            find(size/2, r - size/2, c - size/2);
        }
    }
}