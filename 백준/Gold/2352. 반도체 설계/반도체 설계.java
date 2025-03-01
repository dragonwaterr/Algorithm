import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 1987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N]; 
        int[] LIS = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();

        int i = 0;
        while (st.hasMoreTokens()) { 
            arr1[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        LIS[0] = arr1[0];
        for (i = 1; i <= N; i++) {
            LIS[i] = MAX;
        }

        int index;
        for (i = 1; i < N; i++) {
            index = binarySearch(LIS, arr1[i]);
            LIS[index] = arr1[i];
        }

        i = 0;
        int cnt = 0;
        while (LIS[i] != MAX) {
            cnt++;
            i++;
        }

        bw.write(cnt + "\n");
        bw.flush();
    }

    static int binarySearch(int[] lis, int key) {
        int low = 0;
        int high = lis.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; 

            if (key > lis[mid]) 
                low = mid + 1;
            else if (key < lis[mid])
                high = mid - 1;
            else
                return mid;
        }
        return low; 
    }
    
}