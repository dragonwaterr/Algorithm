
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            int m = Integer.parseInt(br.readLine());

            if(m > 4249) {
                sb.append(-1).append("\n");
                continue;
            }

            int extraHour = m / 250;
            int extraMin = (m % 250) / 50; 

            int hour = 6 + extraHour;
            String resultHour = hour < 10 ? "0"+hour : String.valueOf(hour);
            sb.append(resultHour).append(":");
            String resultMin = "";
            switch (extraMin) {
                case 0: resultMin = "06";
                    break;
                case 1: resultMin = "18";
                    break;
                case 2: resultMin = "30";
                    break;
                case 3: resultMin = "42";
                    break;
                case 4: resultMin = "54";
                    break;
                default:
            }
            sb.append(resultMin).append("\n");
        }
        System.out.println(sb);
    }
}
