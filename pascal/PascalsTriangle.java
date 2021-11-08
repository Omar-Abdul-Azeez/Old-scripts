import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Scanner;

public class PascalsTriangle {
	public static void main(String[] args) {
		PascalsTriangleC Pascals = new PascalsTriangleC();
		Scanner scanner = new Scanner(System.in);
		int rows;
		Loop:
		while ((rows = scanner.nextInt()) > 0) {
			long startTime = System.nanoTime();
			for (int i = 0; i < rows; ++i) {
				Pascals.calcNextRow();
				if (Pascals.getRow() == null)
					break Loop;
				for (int j = 0; j < rows - i; ++j)
					System.out.print(" ");
				for (BigDecimal Pascal : Pascals.getRow())
					System.out.print(Pascal.toString().concat(" "));
				System.out.println();
			}
			System.out.println(System.nanoTime() - startTime);
			System.out.println((double) (System.nanoTime() - startTime) / 1000000000);
			System.out.println();
		}
		scanner.close();
	}
}