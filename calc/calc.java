import java.util.Scanner;

public class calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println(calculate(scanner.nextLine()));
        }
    }

    static double calculate(String str) {
        if (!str.contains("+") && !str.contains("-") && !str.contains("*") && !str.contains("/")) {
            return str.isEmpty() ? 0 : Double.parseDouble(str);
        } else {
            if (str.contains("(") && str.lastIndexOf(")") > str.indexOf("(")) {
                str = str.replace(str.substring(str.indexOf("("), str.lastIndexOf(")") + 1),
                        String.valueOf(calculate(str.substring(str.indexOf("(") + 1, str.lastIndexOf(")")))));
            }

            if (str.contains("-")) {
                return calculate(str.substring(0, str.lastIndexOf("-"))) - calculate(str.substring(str.lastIndexOf("-") + 1));
            } else if (str.contains("+")) {
                return calculate(str.substring(0, str.lastIndexOf("+"))) + calculate(str.substring(str.lastIndexOf("+") + 1));
            } else if (str.contains("*")) {
                return calculate(str.substring(0, str.lastIndexOf("*"))) * calculate(str.substring(str.lastIndexOf("*") + 1));
            } else {
                return str.contains("/") ? calculate(str.substring(0, str.lastIndexOf("/"))) / calculate(str.substring(str.lastIndexOf("/") + 1)) : 0;
            }
        }
    }
}
