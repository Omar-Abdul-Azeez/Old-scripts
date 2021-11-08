import java.math.BigInteger;
import java.util.Scanner;

public class PrimeFactorization {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigInteger num;
        while((num = scanner.nextBigInteger()).compareTo(BigInteger.ONE) > 0) {
            String sumShit = "";
            long startTime = System.nanoTime();

            BigInteger power;
            for(power = BigInteger.ZERO; num.remainder(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0; power = power.add(BigInteger.ONE)) {
                num = num.divide(BigInteger.valueOf(2));
            }

            if (power.compareTo(BigInteger.ONE) == 0) {
                System.out.print(sumShit.concat("2"));
                sumShit = " * ";
            } else if (power.compareTo(BigInteger.ZERO) != 0) {
                System.out.print(sumShit.concat("2^" + power));
                sumShit = " * ";
            }

            for(BigInteger factor = BigInteger.valueOf(3); factor.compareTo(num.divide(factor)) < 1; factor = factor.add(BigInteger.valueOf(2))) {
                for(power = BigInteger.ZERO; num.remainder(factor).compareTo(BigInteger.ZERO) == 0; power = power.add(BigInteger.ONE)) {
                    num = num.divide(factor);
                }

                if (power.compareTo(BigInteger.ONE) == 0) {
                    System.out.print(sumShit.concat(factor.toString()));
                } else if (power.compareTo(BigInteger.ZERO) != 0) {
                    System.out.print(sumShit.concat(factor + "^" + power));
                }

                if (power.compareTo(BigInteger.ZERO)!=0 && !sumShit.equals(" * ")) {
                    sumShit = " * ";
                }
            }

            if (num.compareTo(BigInteger.ONE) != 0) {
                System.out.print(sumShit.concat(num.toString()));
            }

            System.out.println();
            System.out.println();
            long duration = System.nanoTime() - startTime;
            System.out.println((double)duration / 1.0E9D + " Seconds");
            System.out.println();
        }
        System.out.println("0 and 1 don't really have prime factors, nor does any negative number actually");
        System.out.println("And for that, BAKA, i'm quitting (just make sure to press any key to tell me when :3)");
        scanner.next();
        scanner.close();
    }
}
