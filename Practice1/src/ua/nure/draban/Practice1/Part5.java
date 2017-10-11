package ua.nure.draban.Practice1;

public class Part5 {
    public static void main(String[] args) {
        System.out.println("A ==> " + str2int("A") + " ==> A");
        System.out.println("B ==> " + str2int("B") + " ==> B");
        System.out.println("Z ==> " + str2int("Z") + " ==> Z");
        System.out.println("AA ==> " + str2int("AA") + " ==> AA");
        System.out.println("AZ ==> " + str2int("AZ") + " ==> AZ");
        System.out.println("BA ==> " + str2int("BA") + " ==> BA");
        System.out.println("ZZ ==> " + str2int("ZZ") + " ==> ZZ");
        System.out.println("AAA ==> " + str2int("AAA") + " ==> AAA");
    }

    public static void show() {
        System.out.println("A ==> " + int2str(1) + " ==> A");
        System.out.println("B ==> " + int2str(2) + " ==> B");
        System.out.println("Z ==> " + int2str(26) + " ==> Z");
        System.out.println("AA ==> " + int2str(27) + " ==> AA");
        System.out.println("AZ ==> " + int2str(52) + " ==> AZ");
        System.out.println("BA ==> " + int2str(53) + " ==> BA");
        System.out.println("ZZ ==> " + int2str(702) + " ==> ZZ");
        System.out.println("AAA ==> " + int2str(703) + " ==> AAA");
    }

    public static int str2int(String number) {
        int sum = 0;
        int index = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            sum += (number.charAt(i)-64)*(Math.pow(26, index));
            index++;
        }
        return sum;
    }

    public static String int2str(int number) {
        int num = number;
        char ch = 'A';
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int d = (num % 26 == 0) ? 26 : num % 26;
            num = (num - d) / 26;
            sb.append((char) (ch + d - 1));
        }
        return sb.reverse().toString();
    }

    public static String rightColumn(String number) {
        int temp = str2int(number);
        return int2str(temp+1);
    }
}
