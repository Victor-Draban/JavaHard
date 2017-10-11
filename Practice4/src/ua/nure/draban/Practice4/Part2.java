package ua.nure.draban.Practice4;

import java.io.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static final String ENCODING = "Cp1251";

	private static final String RAW_DATA = "part2.txt";

	private static final String SORTED_DATA = "part2_sorted.txt";

	private static final int N = 10;

	private static final int MAX = 50;

	public static void main(String[] args) {
        String input = "input ==> ";
        String output = "output ==> ";
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(RAW_DATA), ENCODING);) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                int nextInt = new Random().nextInt(MAX);
                sb.append(nextInt).append(" ");
            }
            writer.write(sb.toString());
            input += sb.toString();
        } catch (IOException e) {
            System.out.println(e);
        }

        int[] arr = new int[N];
        int index = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(RAW_DATA), ENCODING))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = Pattern.compile("\\d+").matcher(line);
                while (matcher.find()) {
                    arr[index++] = Integer.parseInt(matcher.group());
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(SORTED_DATA), ENCODING);) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(arr[i]).append(" ");
            }
            writer.write(sb.toString());
            output += sb.toString();
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(input);
        System.out.println(output);
    }
}