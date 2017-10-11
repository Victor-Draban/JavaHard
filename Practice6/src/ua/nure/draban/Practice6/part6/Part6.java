package ua.nure.draban.Practice6.part6;

import ua.nure.draban.Practice6.part1.Word;
import ua.nure.draban.Practice6.part1.WordContainer;

import java.io.*;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
	
	public static void main(String[] args) {
        String file = null;
        TaskName task = null;
        if (args[0].equals("-i") || args[0].equals("--input")) {
            file = args[1];
        }
        if (args[2].equals("-t") || args[2].equals("--task")) {
            task = TaskName.valueOf(args[3].toUpperCase());
        }
        Objects.requireNonNull(file);
        Objects.requireNonNull(task);

//        WordContainer container = readFile(file, "Cp1251");
        chooseTask(file, TaskName.FREQUENCY);
        chooseTask(file, TaskName.LENGTH);
        chooseTask(file, TaskName.DUPLICATES);
    }

    private static WordContainer readFile(String file, String encoding) {
        WordContainer container = new WordContainer();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = Pattern.compile("(\\w+)", Pattern.UNICODE_CHARACTER_CLASS).matcher(line);
                while (matcher.find()) {
                    container.add(new Word(matcher.group()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return container;
    }

    private static WordContainer readFile(String file, String encoding, WordContainer container) {
        WordContainer resultContainer = container;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = Pattern.compile("(\\w+)", Pattern.UNICODE_CHARACTER_CLASS).matcher(line);
                while (matcher.find()) {
                    resultContainer.add(new Word(matcher.group()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return container;
    }

    private static void chooseTask(String file, TaskName task) {
        int count = 0;
        switch (task) {
            case FREQUENCY:
                    WordContainer frequencyContainer = readFile(file, "Cp1251");
                    Set<Word> resultFrequency = new TreeSet<>(new Comparator<Word>() {
                        @Override
                        public int compare(Word o1, Word o2) {
                            return -(o1.getWord().compareTo(o2.getWord()));
                        }
                    });
                    for (Word iter : frequencyContainer.getWordContainer()) {
                        resultFrequency.add(iter);
                        if (++count == 3) {
                            break;
                        }
                    }
                    for (Word iter : resultFrequency) {
                        System.out.println(iter.getWord() + " ==> " + iter.getFrequency());
                    }
                break;
            case LENGTH:
                WordContainer lengthContainer = readFile(file, "Cp1251");
                Set<Word> resultLength = new TreeSet<>(new Comparator<Word>() {
                    @Override
                    public int compare(Word o1, Word o2) {
                        return -(o1.getWord().length() - o2.getWord().length());
                    }
                });
                for (Word iter : lengthContainer.getWordContainer()) {
                    resultLength.add(iter);
                    if (++count == 3) {
                        break;
                    }
                }
                for (Word iter : resultLength) {
                    System.out.println(iter.getWord() + " ==> " + iter.getFrequency());
                }
                break;
            case DUPLICATES: WordContainer duplicateContainer = new WordContainer() {
                int count = 0;
                @Override
                public void add(Word word) {
					if (count < 3) {
						for (Word iter : getWordContainer()) {
							if (iter.getWord().equals(word.getWord())) {
								int frequency = iter.getFrequency();
								if (frequency < 2) {
									getWordContainer().remove(iter);
									getWordContainer().add(new Word(iter.getWord(), frequency + 1));
								} else {
									count++;
								}
								return;
							}
						}
						getWordContainer().add(word);
					}
                }
            };
                duplicateContainer = readFile(file, "Cp1251", duplicateContainer);
                for (Word iter : duplicateContainer.getWordContainer()) {
                    StringBuilder sb = new StringBuilder(iter.getWord());
                    System.out.println(sb.reverse().toString().toUpperCase());
                }
                break;
        }
    }
}
