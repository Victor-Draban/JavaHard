package ua.nure.draban.Practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String ENCODING = "Cp1251";

	public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, ENCODING))) {
            String line = null;
            while (!"stop".equals(line = reader.readLine())) {
                if (line == null) {
                    break;
                }
                String regexp = null;
                switch (line) {
                    case "char":
                        regexp = "\\b\\w{1}\\b";
                        break;
                    case "String":
                        regexp = "[^\\W\\d]{2,}";
                        break;
                    case "int":
                        regexp = "(?<=\\s)\\d+(?=\\s)";
                        break;
                    case "double":
                        regexp = "\\d*\\.\\d*";
                        break;
                }
                try (BufferedReader readFile = new BufferedReader(new InputStreamReader(new FileInputStream("part3.txt"), ENCODING))) {
                    while ((line = readFile.readLine()) != null) {
                        Matcher matcher = Pattern.compile(regexp, Pattern.UNICODE_CHARACTER_CLASS).matcher(line);
                        while (matcher.find()) {
                            System.out.print(matcher.group() + " ");
                        }
                        System.out.println();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}