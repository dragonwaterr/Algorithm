import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    // 전깃줄 - 2
    static int n, len;
    static int[] LIS;
    static int[][] wires;
    static int[] indexArray;
    static StringBuilder sb;

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        wires = new int[n+1][2];
        LIS = new int[n+1];
        indexArray = new int[n+1];

        for(int i = 1; i <= n; i++) {
            LIS[i] = Integer.MAX_VALUE;
        }

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            wires[i][0] = from;
            wires[i][1] = to;
        }

        sort(wires);
    }

    // 전기선 시작점 기준으로 오름차순 정렬
    static void sort(int[][] arr) {
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]); 
    }

    // 이진 탐색 메소드 : key 가 LIS 상에서 들어갈 위치를 리턴한다
    static int binarySearch(int[] LIS, int key) {
        int low = 0;
        int high = LIS.length-1;

        while(low <= high) {
            int mid = low + (high - low)/2;

            if(key < LIS[mid]) high = mid-1;
            else if(key > LIS[mid]) low = mid+1;
            else return mid; // key found
        }
        return low; // key not found;
    }

    // n == 최대 10만, O(n*logn) 필요. O(n^2) 불가능
    static void solution() {
        // lis 만들면서 값 갱신될때마다 cnt + sb 에 append
        sb = new StringBuilder();
        len = 0;
        for(int i = 1; i <= n; i++) {
            int index = binarySearch(LIS, wires[i][1]);
            LIS[index] = wires[i][1];
            indexArray[i] = index;

            if(index > len) len++; // 증가하는 수가 들어왔다면
        }

        int length = len;
        Stack<Integer> stack = new Stack<>();
        for(int i = n; i > 0; i--) {
            if(indexArray[i] == length) {
                length--;
            } else { // 뺄 전깃줄의 start 지점은 모두 스택에 push
                stack.push(wires[i][0]);
            }
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append("\n");
        }
    }

    static void printAnswer() {
        System.out.println(n - len);
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        printAnswer();
    }
}