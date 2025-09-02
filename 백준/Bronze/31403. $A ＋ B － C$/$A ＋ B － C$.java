import java.io.*;

public class Main {
    // A + B - C
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        String c = br.readLine();
        
        int r1 = Integer.parseInt(a) + Integer.parseInt(b) - Integer.parseInt(c);
        int r2 = Integer.parseInt(a + b) - Integer.parseInt(c);
        System.out.println(r1 + "\n" + r2);
    }
}