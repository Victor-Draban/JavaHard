package ua.nure.draban.Practice6.part1;

public class Word implements Comparable<Word> {
	
	private String word;
	
	private int frequency;

	public Word(String word) {
		this.word = word;
		frequency = 1;
	}

    public Word(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
		return word;
	}

	public int getFrequency() {
		return frequency;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        if (frequency != word1.frequency) return false;
        return !(word != null ? !word.equals(word1.word) : word1.word != null);

    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + frequency;
        return result;
    }

    @Override
	public int compareTo(Word o) {
		int res = -(frequency - o.getFrequency());
		return (res == 0) ? word.compareTo(o.getWord()) : res;
	}

	@Override
	public String toString() {
		return word + " : " + frequency;
	}

}
