import java.io.*;
import java.util.*;

// 큐 + 마지막에 비내림차순 정렬 시켜서 key 로 val 조회하기

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int answer = 987654321;
        
        int M = Integer.parseInt(br.readLine());
        Edge[] edges = new Edge[M];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, cost);
        }
        
        HashMap<String, Integer> map = new HashMap<>();
        String s = trans(arr, N);
        if(isAsc(s)) {
            System.out.println(0);
            return;
        }
        map.put(s, 0);
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(arr);
        
        while(!q.isEmpty()) {
            int[] cur = q.remove();
            int val = map.get(trans(cur, N)); 
            
            for(int i = 0; i < M; i++) { 
                int[] cp = cur.clone(); 
                Edge e = edges[i];
                int tmp = cp[e.from];
                cp[e.from] = cp[e.to];
                cp[e.to] = tmp;
                
                String str = trans(cp, N);
                
                int sum = val + e.cost;
                if(map.get(str) == null || map.get(str) > sum) {
                    map.put(str, sum);
                    q.add(cp);
                }
            }
        }
        
        char[] cArr = s.toCharArray();
        Arrays.sort(cArr);
        String sorted = new String(cArr);
        
        if(isAsc(sorted) && map.get(sorted) != null) {
            answer = Math.min(answer, map.get(sorted));
        }
        
        if(answer == 987654321) {
            System.out.println(-1);
            return;
        } 
        System.out.println(answer);
    }
    
    static class Edge {
        int from, to, cost;
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    
    static String trans(int[] a, int N) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append((char)(a[i] + 64)); // A ~ J 로 이뤄진 길이 N 문자열
        }
        return sb.toString();
    }
    
    static boolean isAsc(String str) {
        for(int i = 1; i < str.length(); i++) {
            if(str.charAt(i-1) > str.charAt(i)) return false;
        }
        return true;
    }
}