package ua.nure.draban.Practice3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static String convert(String input) {
        Matcher matcher = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS).matcher(input);
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (String s : list) {
            if (s.length() > max) {
                max = s.length();
            }
            if (s.length() < min) {
                min = s.length();
            }
        }
        Set<String> setMin = new LinkedHashSet<>();
        Set<String> setMax = new LinkedHashSet<>();
        for (String s : list) {
            if (s.length() == min) {
                setMin.add(s);
            } else if (s.length() == max) {
                setMax.add(s);
            }
        }
        sb.append("Min: ");
        for (String s : setMin) {
            sb.append(s).append(", ");
        }
        sb.delete(sb.length()-2, sb.length()).append(System.lineSeparator());
        sb.append("Max: ");
        for (String s : setMax) {
            sb.append(s).append(", ");
        }
        sb.delete(sb.length()-2, sb.length()).append(System.lineSeparator());
        return sb.toString();
    }
}
