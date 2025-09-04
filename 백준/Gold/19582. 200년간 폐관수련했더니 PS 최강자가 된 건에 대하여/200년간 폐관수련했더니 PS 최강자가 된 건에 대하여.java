import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 200년간 폐관수련했더니 PS 최강자가 된 건에 대하여
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        int sum = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cut = Integer.parseInt(st.nextToken());
            int prize = Integer.parseInt(st.nextToken());

            if(sum <= cut) { 
                sum += prize;
                max = Math.max(max, prize);
                continue;
            }
            
            if(cnt == 1) { 
                System.out.println("Zzz");
                return;
            }
            
            cnt++;
            if(max <= prize) { 
                max = prize;
                continue;
            }
            if(sum - max > cut) { 
                continue;
            }
            sum -= max;
        }
        System.out.println("Kkeo-eok");
    }
}
