import java.util.Scanner;

public class PI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt(), j = 1;
        double pi = 0;
        while (j < i) {
            pi += 1. / j;
            j += 4;
        }
        j=3;
        while (j < i) {
            pi -= 1. / j;
            j += 4;
        }
        System.out.println(pi*4);
    }
}
