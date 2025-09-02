import java.io.*;

public class Main {
    // 피보나치 함수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr0 = new int[41];
        int[] arr1 = new int[41];

        arr0[0] = 1;
        arr0[1] = 0;
        arr0[2] = 1;
        arr0[3] = 1;

        arr1[1] = 1;
        arr1[2] = 1;
        arr1[3] = 2;

        for(int i=4; i<41; i++) {
            arr0[i] = arr1[i-1];
            arr1[i] = arr0[i-1]+arr1[i-1];
        }

        int N = Integer.valueOf(br.readLine());
        for(int i=0; i<N; i++) {
            int F = Integer.valueOf(br.readLine());
//            bw.write(arr0[F]+" "+arr1[F]+"\n");
            System.out.println(arr0[F]+" "+arr1[F]);
        }
//        bw.flush();
    }
}