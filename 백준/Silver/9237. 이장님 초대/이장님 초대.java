import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer arr[] = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, Comparator.reverseOrder());

        for (int i = 0; i < N; i++) {
            arr[i] = (i+1)+(arr[i]);
        }

        Arrays.sort(arr,Comparator.reverseOrder());
        System.out.println(arr[0]+1);
    }
}