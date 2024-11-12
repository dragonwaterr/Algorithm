import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    rightangle rg = new rightangle();
    Scanner sc = new Scanner(System.in);

    while(true) {
      for(int i = 0; i < 3; i++) {
        rg.angle[i] = sc.nextInt();
      }
      if(rg.angle[0] == 0) return;
      else rg.answer();
    }
  }
}

class rightangle {
  int [] angle = new int[3]; 
  rightangle() {} 

  void square(int[] angle) { 
    this.angle = angle;
    for(int i = 0; i < this.angle.length; i++) {
      this.angle[i] *= angle[i];
    }
    Arrays.sort(this.angle);
  }

  void answer() {
    square(this.angle);
    if(this.angle[0] + this.angle[1] == this.angle[2])
      System.out.println("right");
    else
      System.out.println("wrong");
  }
}