import java.io.*;
import java.util.StringTokenizer;
  
class Solution {
    // 나무 높이 (SWEA)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  
        int tc = Integer.parseInt(br.readLine());
  
        for (int i = 0; i < tc; i++) {
            bw.write("#"+(i+1)+" ");
              
            int n = Integer.parseInt(br.readLine());
            int[] trees = new int[n];
            int maxH = 0;
  
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int h = Integer.parseInt(st.nextToken());
                maxH = Math.max(maxH, h);
                trees[j] = h;
            }
  
            int oddDiffTrees = 0; // 1cm 를 한 번은 받아야 하는 나무 수
            int totalDiff = 0;
            for (int j = 0; j < n; j++) {
                int curDiff = maxH - trees[j];
                totalDiff += curDiff;
                if (curDiff % 2 == 1) {
                    oddDiffTrees++;
                    trees[j]++; // 차이의 짝수화
                }
            }
            totalDiff -= oddDiffTrees;
  
            int days = 2 * oddDiffTrees - 1; // maxH 와의 차이가 홀수인 나무들에 모두 1cm 를 줬을 때의 최소 days
            int usableH = days - 1; // days 동안 사용할 수 있었던 2cm 들의 합
  
            if(usableH >= totalDiff) {
                bw.write(days + "\n");
                continue;
            }
  
            int toGrowH = totalDiff - usableH; // 더 키워야 하는 총 높이
  
            // days 는 이제 짝수일, i 는 1부터 시작하는 자연수.
            // toGrowH <= i + (i+1)/2 를 만족하는 가장작은 i 찾아 days 에 추가
            int extraDays = (int) Math.ceil((2.0 * toGrowH - 1.0) / 3.0);
            days += extraDays;
            bw.write(days + "\n");
        }
        bw.flush();
    }
}