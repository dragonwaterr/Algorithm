import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        ArrayList<PriorityQueue<Integer>> players = new ArrayList<>(12);
        
        for(int i = 0; i < 12; i++) {
            players.add(i, new PriorityQueue<>(Collections.reverseOrder()));
        }
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            players.get(pos).add(val);
        }
        
        int[] starting = new int[12];
        for(int i = 1; i < 12; i++) {
            int player = players.get(i).size() != 0 ? players.get(i).poll() : -1;
            starting[i] = player;
        }
        

        for(int i = 0; i < K; i++) {
            for(int j = 1; j < 12; j++) {
               starting[j]--;
               if(starting[j] > -1) {
                   players.get(j).add(starting[j]);
               }
               starting[j] = players.get(j).size() != 0 ? players.get(j).poll() : -1;
            }
        }
        
        int sum = 0;
        for(int j = 1; j < 12; j++) {
            if(starting[j] > -1) {
                sum += starting[j];
            } 
        }
        System.out.println(sum);
    }
}