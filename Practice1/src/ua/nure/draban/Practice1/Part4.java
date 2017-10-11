package ua.nure.draban.Practice1;

public class Part4 {
    public static void main(String[] args) {
        String temp = args[0];
        int sum = 0;
        for (int i = 0; i < temp.length(); i++) {
            int number = Integer.parseInt(String.valueOf(temp.charAt(i)));
            sum += number;
        }
        System.out.println(sum);
    }
}
