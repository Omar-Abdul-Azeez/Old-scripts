import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class NumeralSystemConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("From base: ");
            BigInteger fromBase = scanner.nextBigInteger();
            System.out.print("To base: ");
            BigInteger toBase = scanner.nextBigInteger();
            scanner.nextLine();
            System.out.println("The number: (put a space between digits)");
            String[] numArray = scanner.nextLine().split(" ");
            if (fromBase.compareTo(BigInteger.ONE) < 0 || toBase.compareTo(BigInteger.ONE) < 0) {
                System.out.println("*Hmm intensifies*");
                continue;
            }

            ArrayList<BigInteger> numBefore = new ArrayList<>();
            StringBuilder numAfter = new StringBuilder();
            BigInteger numDecimal = BigInteger.ZERO;
            long startTime = System.nanoTime();

            for (int i = 0; i < numArray.length; ++i) {
                numArray[i] = numArray[i].trim();
                if (numArray[i].isEmpty()) {
                    System.out.println("Please.. don't empty digit me because..");
                    System.out.println("Me no magician that can guess your number.");
                    continue;
                }

                numBefore.add(new BigInteger(numArray[i]));
                if (((BigInteger) numBefore.get(i)).compareTo(fromBase.subtract(BigInteger.ONE)) > 0) {
                    System.out.println("Converting From Base " + fromBase + " Yet Having At Least A Single Digit With A Value Bigger Than " + fromBase.subtract(BigInteger.ONE) + ". Ah How Convenient");
                    System.out.println("Look man, if your number is in base 3 for eg. you can only have the digits 0,1,2.");
                    System.out.println("And if it's base " + fromBase + ". Then the largest digit you can have is " + fromBase.subtract(BigInteger.ONE) + ". See the pattern?");
                }

                numDecimal = numDecimal.add(((BigInteger) numBefore.get(i)).multiply(fromBase.pow(numArray.length - i - 1)));
            }

            while (numDecimal.compareTo(BigInteger.ZERO) > 0) {
                numAfter.insert(0, numDecimal.remainder(toBase) + " ");
                numDecimal = numDecimal.divide(toBase);
            }

            System.out.println(numAfter.subSequence(0, numAfter.length() - 1));
            long duration = System.nanoTime() - startTime;
            System.out.println();
            System.out.println((double) duration / 1.0E9D + " Seconds");
            System.out.println();
        }
    }
}