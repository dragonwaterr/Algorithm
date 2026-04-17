import java.io.*;
import java.util.*;

public class Main {

	static int[] town;
	static List<Integer>[] list;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		town = new int[n+1];
		dp = new int[n+1][2];
		for(int i=0; i<n+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		for(int i=1; i<n+1; i++) {
			town[i] = Integer.parseInt(st.nextToken());
		}
		
		list = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}

		System.out.println(Math.max(solve(1, -1, 1)+town[1], solve(1, -1, 0)));
		
	}
	static int solve(int pos, int prev, int flag) {
	
		int len =list[pos].size();
		
		if(dp[pos][flag] != -1) return dp[pos][flag];
		
		dp[pos][flag] =0;
		for(int i=0; i<len; i++) {
			int next = list[pos].get(i);
			if(next != prev) {
				if(flag==1) { 
					dp[pos][flag] += solve(next, pos, 0); 
				} else { 
					dp[pos][flag] += Math.max(solve(next, pos, 1)+town[next], solve(next, pos, 0));
				}
			}
			
		}
		return dp[pos][flag];
	}
}
