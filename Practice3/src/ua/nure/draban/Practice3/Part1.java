package ua.nure.draban.Practice3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static String convert1(String input) {
        Matcher matcher = Pattern.compile("(\\S+);(\\S+\\s\\S+);(\\S+@\\S+)").matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group(1))
                    .append(" ==> ")
                    .append(matcher.group(3))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static String convert2(String input) {
        Matcher matcher = Pattern.compile("(\\S+);(\\S+\\s\\S+);(\\S+@\\S+)").matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group(2))
                    .append(" (email: ")
                    .append(matcher.group(3))
                    .append(")")
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static String convert3(String input) {
        Matcher matcher = Pattern.compile("(\\S+);(\\S+\\s\\S+);(\\S+)@(\\S+)").matcher(input);
        Map<String, List<String>> map = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            List<String> list;
            if (map.get(matcher.group(4)) != null) {
                list = map.get(matcher.group(4));
            } else {
                list = new ArrayList<>();
            }
            list.add(matcher.group(1));
            map.put(matcher.group(4), list);
        }
        for (Map.Entry<String, List<String>> pair : map.entrySet()) {
            sb.append(pair.getKey()).append(" ==> ");
            for (String s : pair.getValue()) {
                sb.append(s).append(", ");
            }
            sb.delete(sb.length()-2, sb.length()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static String convert4(String input) {
        Matcher matcher = Pattern.compile("(\\S+);(\\S+\\s\\S+);(\\S+@\\S+)").matcher(input);
        StringBuilder sb = new StringBuilder("Login;Name;Email;Password").append(System.lineSeparator());
        while (matcher.find()) {
            sb.append(matcher.group(1)).append(";")
                    .append(matcher.group(2)).append(";")
                    .append(matcher.group(3)).append(";")
                    .append(new Random().nextInt(8999)+1000)
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
