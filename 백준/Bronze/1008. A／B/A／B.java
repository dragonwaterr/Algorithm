import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int a = scan.nextInt();
		int b = scan.nextInt();
		
        double result = (double)a / b;
		System.out.println(result);
		
		scan.close();
	}
}