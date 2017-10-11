package ua.nure.draban.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    public static String convert(String input) {
        Matcher matcher = Pattern.compile("(\\S+)(\\s*)", Pattern.UNICODE_CHARACTER_CLASS).matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            String temp = matcher.group(1);
            if (temp.length() > 2) {
                sb.append(String.valueOf(temp.charAt(0)).toUpperCase()).append(temp.substring(1));
            } else {
                sb.append(temp);
            }
            sb.append(matcher.group(2));
        }
        return sb.toString();
    }
}
