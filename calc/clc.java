import com.sun.javaws.exceptions.InvalidArgumentException;

import java.math.BigInteger;
import java.util.Scanner;

public class clc {
    public static void main(String[] args) {
        int x, y;
        char op;
        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        y = sc.nextInt();
        op = sc.next().charAt(0);
        Calculator calculator = new Calculator(x, y, op);
        System.out.println(calculator.calculate());
    }
}

class Calculator {
    int x, y;
    char op;

    Calculator(int x, int y, char op) {
        this.x = x;
        this.y = y;
        this.op = op;
    }

    int calculate() {
        switch (op) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                if (y == 0) throw new ArithmeticException("Division by 0");
                return x / y;
        }
        throw new IllegalArgumentException("Give a proper operator");
    }
}