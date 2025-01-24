import java.io.*;
import java.util.ArrayList;

public class Main {
    // 찾기
    static int[] table;
    static ArrayList<Integer> idx = new ArrayList<>();
    static void makeTable(char[] pattern) {
        table = new int[pattern.length];
        int j = 0;
        for(int i = 1; i < pattern.length; i++) {
            while(j > 0 && pattern[i] != pattern[j])
                j = table[j-1];
            if(pattern[i] == pattern[j])
                table[i] = ++j;
        }
    }
    static void kmp(char[] text, char[] pattern) {
        int j = 0;
        for(int i = 0; i < text.length; i++) {
            while(j > 0 && pattern[j] != text[i]) 
                j = table[j-1];
            
            if(pattern[j] == text[i]) {
                if(j == pattern.length-1) {
                    idx.add(i-pattern.length+2);
                    j = table[j];
                    continue;
                } 
                j++;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] text = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        makeTable(pattern);
        kmp(text, pattern);

        bw.write(idx.size()+"\n");
        for(Integer i : idx)
            bw.write(i + " ");
        bw.flush();
    }
}