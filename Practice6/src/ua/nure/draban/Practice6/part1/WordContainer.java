package ua.nure.draban.Practice6.part1;

import java.util.Set;
import java.util.TreeSet;

public class WordContainer {
    private Set<Word> wordContainer;

    public void add(Word word) {
        for (Word iter : wordContainer) {
            if (iter.getWord().equals(word.getWord())) {
                int frequency = iter.getFrequency();
                wordContainer.remove(iter);
                wordContainer.add(new Word(iter.getWord(), frequency + 1));
                return;
            }
        }
        wordContainer.add(word);
    }

    public WordContainer() {
        wordContainer = new TreeSet<>();
    }

    public WordContainer(Set<Word> wordContainer) {
        this.wordContainer = wordContainer;
    }

    public Set<Word> getWordContainer() {
        return wordContainer;
    }
}
