package ua.nure.draban.Practice3;

import java.security.NoSuchAlgorithmException;

public class Demo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(Part1.convert1(Util.readFile("part1.txt")));

        System.out.println(Part2.convert(Util.readFile("part2.txt")));

        System.out.println(Part3.convert(Util.readFile("part3.txt")));

        System.out.println(Part4.hash("password", "SHA-256"));

        System.out.println(1 + " ====> " + Part5.decimal2Roman(1) + " ====> " + Part5.roman2Decimal(Part5.decimal2Roman(1)));
    }
}
