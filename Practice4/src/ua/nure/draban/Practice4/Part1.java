package ua.nure.draban.Practice4;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

	private static final String FILE_NAME = "part1.txt";

	private static final String ENCODING = "Cp1251";

	public static void main(String[] args) {
        System.out.println(part1(FILE_NAME, ENCODING));
    }

    public static String part1(String fileName, String encoding) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encoding))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = Pattern.compile("(\\W*)(\\w+)(\\W*)", Pattern.UNICODE_CHARACTER_CLASS).matcher(line);
                while (matcher.find()) {
                    if (matcher.group(1) != null) {
                        sb.append(matcher.group(1));
                    }
                    String temp = matcher.group(2);
                    if (temp.length() > 3) {
                        sb.append(temp.toUpperCase());
                    } else {
                        sb.append(temp);
                    }
                    sb.append(matcher.group(3));
                }
                sb.append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return sb.delete(sb.length()-1, sb.length()).toString();
    }

}