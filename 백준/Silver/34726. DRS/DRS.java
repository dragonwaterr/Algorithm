import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static class Driver {
        String name;
        int distWithFront;
        boolean DRS;
        int pos;

        Driver(String name, int distWithFront) {
            this.name = name;
            this.distWithFront = distWithFront;
            this.DRS = false;
            this.pos = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        Driver[] drivers = new Driver[N];

        st = new StringTokenizer(br.readLine());
        drivers[0] = new Driver(st.nextToken(), Integer.parseInt(st.nextToken()));
        drivers[0].pos = T % 2 == 0 ? T/2 : (T+1)/2;


        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int distWithFront = Integer.parseInt(st.nextToken()) % T;
            drivers[i] = new Driver(name, distWithFront);

            int frontPos = drivers[i - 1].pos;
            if (frontPos - distWithFront >= 0) {
                drivers[i].pos = frontPos - distWithFront;
                continue;
            }
            drivers[i].pos = T - (distWithFront - frontPos);
        }

        Arrays.sort(drivers, ((o1, o2) -> o1.pos - o2.pos));


        for (int i = 0; i < N - 1; i++) {
            drivers[i].DRS = drivers[i + 1].pos - drivers[i].pos <= 1000;
        }

        if (drivers[N - 1].pos + 1000 < T) {
            drivers[N - 1].DRS = false;
        } else {
            drivers[N - 1].DRS = ((drivers[N - 1].pos + 1000) % T) >= drivers[0].pos;
        }

        HashSet<String> names = new HashSet<>();
        for (int i = 0; i < N; i++) {
            if (drivers[i].DRS) {
                names.add(drivers[i].name);
            }
        }
        if (names.isEmpty()) {
            System.out.println(-1);
            return;
        }

        String[] nameList = names.toArray(new String[0]);
        Arrays.sort(nameList);


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nameList.length; i++) {
            sb.append(nameList[i]).append(" ");
        }

        System.out.println(sb);
    }
}
