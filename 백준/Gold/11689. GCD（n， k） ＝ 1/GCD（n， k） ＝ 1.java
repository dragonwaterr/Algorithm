import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
public class Main {
    // GCD(n, k) = 1
    static long euler_phi(long n) {
        HashSet<Long> prime_nums = new HashSet<>();
        double prime = n;
        long p = 2;
        while(Math.sqrt(n) >= p) {
            while(n % p == 0) {
                n /= p;
                prime_nums.add(p);
            } p++;
        }
        if(prime_nums.size() == 0) return n-1;
        if(n != 1) prime_nums.add(n);
        for(double a : prime_nums) prime  *= (a-1) / a;
        return (long) prime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       
        long n = Long.parseLong (br.readLine());
        if(n == 1) System.out.println(1);
        else System.out.println(euler_phi(n));
    }
}