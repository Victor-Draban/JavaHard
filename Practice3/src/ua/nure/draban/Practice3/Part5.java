package ua.nure.draban.Practice3;

public class Part5 {
    public static String decimal2Roman(int x) {
        int temp = x;
        StringBuilder sb = new StringBuilder();
        int depth;
        int index = 0;
        while (index < ARABIC_SIMBOLS.length) {
            depth = temp / ARABIC_SIMBOLS[index];
            for (int i = 0; i < depth; i++) {
                sb.append(ROMAN_SIMBOLS[index]);
            }
            temp -= depth * ARABIC_SIMBOLS[index];
            index++;
        }
        return sb.toString();
    }

    public static int roman2Decimal(String s) {
        int result = 0;
        int pred = 100;
        for (int i = 0; i < s.length(); i++) {
            String temp = String.valueOf(s.charAt(i));
            int index = -1;
            for (int j = 0; j < ROMAN_SIMBOLS.length; j++) {
                if (temp.equals(ROMAN_SIMBOLS[j])) {
                    index = j;
                }
            }
            if (ARABIC_SIMBOLS[index] > pred) {
                result += ARABIC_SIMBOLS[index] - 2*pred;
            } else {
                result += ARABIC_SIMBOLS[index];
            }
            pred = ARABIC_SIMBOLS[index];
        }
        return result;
    }

    private  static final String [] ROMAN_SIMBOLS = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int [] ARABIC_SIMBOLS = {100, 90, 50, 40, 10, 9, 5, 4, 1};
}
