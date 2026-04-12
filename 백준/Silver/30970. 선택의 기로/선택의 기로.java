import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] miniature = new int[n][2];
        StringTokenizer st;
        for (int i = 0; i<n;i++){
            st = new StringTokenizer(br.readLine());
            miniature[i][0] = Integer.parseInt(st.nextToken());
            miniature[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] qSortedArr = Arrays.stream(miniature).sorted((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0] ;
        }).toArray(int[][]::new);

        int[][] pSortedArr = Arrays.stream(miniature).sorted((a, b) -> {
            if (a[1] == b[1]) return b[0] - a[0] ;
            return a[1] - b[1];
        }).toArray(int[][]::new);

        System.out.print(qSortedArr[0][0]+" "+qSortedArr[0][1]+" "+qSortedArr[1][0]+" "+qSortedArr[1][1]+"\n");
        System.out.print(pSortedArr[0][0]+" "+pSortedArr[0][1]+" "+pSortedArr[1][0]+" "+pSortedArr[1][1]);
    }
}