import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class PrimeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BiDimensionalArray bigIntPrimes = new BiDimensionalArray();
        boolean ready = false;
        System.out.println("Enter the number of primes needed? (yes for.. yes and anything else to enter the search range)");
        System.out.println("NOTE: Don't forget to exit the loop");
        boolean range = !scanner.nextLine().toLowerCase().equals("yes");
        System.out.println("Switch to use previously found primes? (yes for.. yes and anything else to use only odd)");
        System.out.println("NOTE: DON'T FUCKING FORGET TO EXIT THE LOOP");
        boolean switch_ = scanner.nextLine().toLowerCase().equals("yes");

        try (Scanner scannerReader = new Scanner(new File("Primes.txt"))) {
            if (switch_)
                while (scannerReader.hasNextBigInteger()) {
                    bigIntPrimes.addToInner(scannerReader.nextBigInteger());
                }
            else {
                BigInteger n = BigInteger.valueOf(2);
                while (scannerReader.hasNextBigInteger()) {
                    n = scannerReader.nextBigInteger();
                }
                bigIntPrimes.addToInner(n);
            }
            ready = true;
        } catch (FileNotFoundException e) {
            try {
                Files.createFile(Paths.get("Primes.txt"));
                ready = true;
            } catch (IOException ee) {
                System.out.println("Couldn't create the file.");
            }
        }
        System.gc();
        System.out.println("Please type a negative number to exit the loop and write the file.");
        bigIntPrimes.begin();
        if (bigIntPrimes.size() == 0) {
            bigIntPrimes.addToInner(BigInteger.valueOf(2));
            bigIntPrimes.addToInner(BigInteger.valueOf(3));
        }
        BigInteger possiblePrime = bigIntPrimes.lastElement();
        BigInteger count;
        while ((count = scanner.nextBigInteger()).compareTo(BigInteger.ZERO) >= 0 && ready) {
            long startTime;
            for (startTime = System.nanoTime(); !count.equals(BigInteger.ZERO); count = range ? count.subtract(BigInteger.ONE) : count) {
                possiblePrime = possiblePrime.add(BigInteger.valueOf(2));
                if (switch_ && possiblePrime.compareTo(BigInteger.valueOf(2147483647)) > 0) {
                    if (!bigIntPrimes.testPrimality(possiblePrime)) continue;
                } else if (!bigIntPrimes.testPrimalityOdd(possiblePrime)) continue;
                bigIntPrimes.addToInner(possiblePrime);
                if (!range)
                    count = count.subtract(BigInteger.ONE);
            }
            if (switch_)
                System.out.println("Total number of primes: " + ((bigIntPrimes.size() - 1) * BiDimensionalArray.MAX_SIZE + bigIntPrimes.sizeInner));
            long duration = System.nanoTime() - startTime;
            System.out.println();
            System.out.println((double) duration / 1.0E9 + " Seconds.");
            System.out.println();
        }
        bigIntPrimes.end();
        System.out.println("File has been written. You may press any key to exit.");
        scanner.next();
        scanner.close();
    }
}
