package ua.nure.draban.Practice4;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {

    private static final String ENCODING = "Cp1251";

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, ENCODING);
        String line = "";
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if ("stop".equals(line)) {
                break;
            }
            String[] split = line.split(" ");
            Locale locale = new Locale(split[1]);
            ResourceBundle bundle = ResourceBundle.getBundle("resources", locale);
            System.out.println(bundle.getString(split[0]));
        }
	}

}