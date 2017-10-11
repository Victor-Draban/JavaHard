package ua.nure.draban.Practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser implements Iterable<String> {

    private Matcher matcher;

	public Parser(String fileName, String encoding) {
        String str = "";
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encoding))) {
			String line = null;
            StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
            str = sb.toString();
            matcher = Pattern.compile("(\\w+[^.]*)+.", Pattern.UNICODE_CHARACTER_CLASS).matcher(str);
		} catch (IOException e) {
            System.out.println(e);
		}
	}

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return matcher.find();
            }

            @Override
            public String next() {
                return matcher.group();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
	}

}