import java.util.*;
import java.io.*;

class Main {
    static class Pal {
        int num;
        int accum;
        Pal(int num, int accum) {
            this.num = num;
            this.accum = accum;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        ArrayList<Pal> list = new ArrayList<>(); 
        for(int i = 0; i < 10; i++) {
            list.add(new Pal(i, i+1));
        }
        
        for(int i = 1; i < 9; i++) {
            int level = (int)Math.pow(10, i); 
            for(int j = 1; j <= 9; j++) {
                int prevNum = list.get(list.size()-9).num; 
                int num = level*j + prevNum;
                list.add(new Pal(num, list.get(list.size()-1).accum+1));
            }    
        }

        for(int i = 0; i < list.size(); i++) {
            int cur = list.get(i).num; 
            if(n < cur) { 
                System.out.println(list.get(i).accum-1);
                return;
            }
        }
        
        System.out.println(82);
    }
}