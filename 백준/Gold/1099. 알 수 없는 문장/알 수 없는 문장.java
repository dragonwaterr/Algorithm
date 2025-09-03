import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 알 수 없는 문장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, List<String>> map = new HashMap<>();

        String input = br.readLine();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            char[] seq = str.toCharArray();
            Arrays.sort(seq);
            String key = new String(seq);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        int[] dp = new int[input.length()+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= input.length(); i++) {  // 문자열 마지막 인덱스
            for (int j = 0; j < i; j++) { // 문자열 시작 인덱스
                String curStr = input.substring(j, i);

                char[] seq = curStr.toCharArray();
                Arrays.sort(seq);

                String sorted = new String(seq);

                if(map.containsKey(sorted)) { // curStr 을 이동시켜 만들 수 있는 단어 list 가 있다면 ?
                    int min = Integer.MAX_VALUE;
                    List<String> list = map.get(sorted);

                    for(String s : list) { // 최소 이동 값을 비교
                        int cnt = 0;
                        for (int k = 0; k < s.length(); k++) {
                            if(curStr.charAt(k) != s.charAt(k)) cnt++;
                        }
                        min = Math.min(min, cnt);
                    }

                    if(dp[j] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[j] + min);
                    }
                }
            }
        }

        if(dp[input.length()] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(dp[input.length()]);
    }
}
