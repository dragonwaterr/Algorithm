import java.io.*;

public class Main {
    // 공항
    static int[] emptyGate;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        int answer = 0;
        emptyGate = new int[g+1];
        for(int i = 1; i <= g; i++) {
            emptyGate[i] = i;
        }

        for(int i = 0; i < p; i++) {
            int range = Integer.parseInt(br.readLine());
            if(findEmptyGate(range) == -1) break; // 1번 게이트까지 찼다
            answer++;
        }
        System.out.println(answer);
    }

    static int findEmptyGate(int x) {
        if(x == 0) return -1;
        if(x == emptyGate[x]) {
            emptyGate[x]--; 
            return emptyGate[x];
        }
        return emptyGate[x] = findEmptyGate(emptyGate[x]);
    }
}