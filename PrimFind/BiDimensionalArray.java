import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;

final class BiDimensionalArray extends ArrayList<ArrayList<BigInteger>> {
    static final int MAX_SIZE = 2147483639;
    private int[] startIndex;
    private int size;
    int sizeInner;

    public final void addToInner(BigInteger element) {
        ArrayList<BigInteger> innerArray;
        if (this.size == 0) {
            this.add(innerArray = new ArrayList());
            ++this.size;
            this.sizeInner = 1;
            innerArray.add(element);
        } else {
            if (this.sizeInner++ == MAX_SIZE) {
                this.add(innerArray = new ArrayList());
                sizeInner = 1;
            } else {
                innerArray = this.get(this.size - 1);
            }

            innerArray.add(element);
        }

    }

    private BigInteger getInner(int index, int innerIndex) {
        return (BigInteger) ((ArrayList) get(index)).get(innerIndex);
    }


    private int sizeInner(int index) {
        return index < size - 1 ? MAX_SIZE : sizeInner;
    }

    final BigInteger lastElement() {
        return getInner(size - 1, sizeInner(size - 1) - 1);
    }

    final boolean testPrimality(BigInteger element) {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < sizeInner(i); ++j) {
                if (element.remainder(getInner(i, j)).equals(BigInteger.ZERO)) {
                    return false;
                }
            }
        }

        return true;
    }

    final boolean testPrimalityOdd(BigInteger element) {
        if (element.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            return false;
        } else {
            for (BigInteger possibleDivisor = BigInteger.valueOf(3); possibleDivisor.compareTo(element.divide(possibleDivisor)) < 0; possibleDivisor = possibleDivisor.add(BigInteger.valueOf(2))) {
                if (element.remainder(possibleDivisor).equals(BigInteger.ZERO)) {
                    return false;
                }
            }

            return true;
        }
    }

    public final void begin() {
        if (size == 0) {
            startIndex = new int[]{0, 0};
        } else {
            startIndex = new int[]{size - 1, sizeInner(size - 1)};
        }
    }

    public final void end() {
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("Primes.txt", true)));
            for (int i = startIndex[0]; i < size; ++i) {
                int sizeInner = sizeInner(i);
                int j;
                for (j = startIndex[1]; j < sizeInner; ++j) {
                    printWriter.print(getInner(i, j) + "\n");
                }
                printWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Couldn't establish connection to file or couldn't write a number.");
        }
    }
}