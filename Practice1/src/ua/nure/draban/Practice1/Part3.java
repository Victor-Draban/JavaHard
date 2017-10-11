package ua.nure.draban.Practice1;

public class Part3 {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        System.out.println(mod(a, b));
    }

    private static int mod(int a, int b) {
        if (b == 0) {
            return a;
        } else if (a == 0) {
            return b;
        }
        return mod(b, a % b);
    }
}
