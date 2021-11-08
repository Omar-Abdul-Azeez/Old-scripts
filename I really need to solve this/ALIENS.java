public class ALIENS {
    public static void main(String[] args) {
        System.out.println(equalizeArray(new int[]{37, 32, 97, 35, 76, 62}));
    }

    static int equalizeArray(int[] arr) {
        int max = 1, n = arr.length;
        int[][] x = new int[n][2];
        for (int i = 0, num; i < n; i++) {
            num = arr[i];
            for (int j = 0; j < n; j++) {
                if (x[j][1] == 0) {
                    x[j][0] = num;
                    x[j][1] = 1;
                    break;
                } else if (x[j][0] == num) {
                    max = max < ++x[j][1] ? x[j][1] : max;
                    break;
                }
            }
        }
        return n - max;
    }
}
class Planet {
    private int x;
    private boolean reverse;

    int calc(int time) {
        return reverse ? (int) ((float) time / (x + 1) - time / (x + 1)) * 10 : x;
    }

    static int calc(Planet[] planets) {
        return 5;
    }
}