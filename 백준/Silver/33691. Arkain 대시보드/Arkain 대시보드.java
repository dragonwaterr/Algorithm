import java.util.*;
import java.io.*;

class Main {
    static class Container {
        boolean docked;
        String name;
        int priority;
        Container(String name, int priority) {
            this.docked = false;
            this.name = name;
            this.priority = priority;
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<String, Container> map = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            String name = br.readLine();
            
            if(map.containsKey(name)) {
                Container cont = map.get(name);
                cont.priority = i;
                continue;
            }
            map.put(name, new Container(name, i));
        }
        
        int d = Integer.parseInt(br.readLine());
        for(int i = 0; i < d; i++) {
            String name = br.readLine();
            map.get(name).docked = true;
        }
        
        ArrayList<Container> list = new ArrayList<>(map.values());
        
        list.sort((o1, o2) -> {
            if(o1.docked && o2.docked) {
                return o2.priority - o1.priority;
            } else if(o1.docked && !o2.docked) {
                return -1;
            } else if(!o1.docked && o2.docked) {
                return 1;
            } else {
                return o2.priority - o1.priority;
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for(Container c : list) {
            sb.append(c.name).append("\n");
        }
        System.out.println(sb);
    }
}