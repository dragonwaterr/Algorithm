import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 호텔
    static class City {
        int cost;
        int people;

        City(int cost, int people) {
            this.cost = cost;
            this.people = people;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        
        City[] cities = new City[n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            cities[i] = new City(cost, people);
        }

        int[] dp = new int[c + 1];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        for (City city : cities) { 
            int cost = city.cost;
            int people = city.people;

            for (int i = 1; i <= c; i++) { 
                if(i <= people) { 
                    dp[i] = Math.min(dp[i], dp[0] + cost);   
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - people] + cost);
            }
        }

        System.out.println(dp[c]);
    }
}
