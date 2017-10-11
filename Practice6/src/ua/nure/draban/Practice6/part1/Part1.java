package ua.nure.draban.Practice6.part1;

import java.util.Scanner;

public class Part1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        WordContainer wordContainer = new WordContainer();
		String line = "";
		while (!(line = scanner.next("\\w+")).equals("stop")) {
            wordContainer.add(new Word(line));
        }
        for (Word iter : wordContainer.getWordContainer()) {
            System.out.println(iter);
        }
    }

}
