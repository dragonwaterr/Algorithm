import java.io.*;
import java.util.StringTokenizer;

public class Main {
    
    public static int[] arr;
    public static long[] tree;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        arr = new int[N+1];
        for(int i = 1; i<=N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        
        int k = (int) Math.ceil(Math.log(N)/Math.log(2)) + 1;
        int size = (int) Math.pow(2, k);
        
        tree = new long[size];
        
        init(1,N,1);
        
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i<t; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(a == 1) {
            	int c = Integer.parseInt(st.nextToken());
                update(1,N,b,1,c);
            }
            if(a == 2)
            	sb.append(find(1,N,1,b)).append('\n');
        }
        
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
    
    public static long init(int s, int e, int node) {
        
        if(s==e)
            return tree[node] = arr[s];
        
        int mid = (s+e) >>> 1;
        return tree[node] = init(s,mid,node*2) + init(mid+1,e,node*2+1);
    }
    
    
    public static void update(int s, int e, int idx, int node, long value) {
        if(idx<s || idx>e)
            return;
        
        tree[node] += value;
        
        if(s==e)
            return;
        
        int mid = (s+e) >>> 1;
        
        update(s,mid,idx,node*2,value);
        update(mid+1,e,idx,node*2+1,value);
    }
    
    public static int find(int s, int e, int node, long value) {
    	if(s==e)
    		return s;
    	int mid = (s+e) >>> 1;
    	if(tree[node*2]>=value)
    		return find(s,mid,node*2,value);
    	else
    		return find(mid+1,e,node*2+1,value-tree[node*2]);
    }

}
