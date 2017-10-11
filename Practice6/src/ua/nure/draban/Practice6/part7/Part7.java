package ua.nure.draban.Practice6.part7;

import java.util.Iterator;

public class Part7 implements Iterable<Integer> {

	private int index;
	private int n;
	private int m;
	private boolean reverse;

	public Part7(int n, int m, boolean reverse) {
		this.n = n;
		this.m = m;
		this.reverse = reverse;
	}

	@Override
	public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                boolean result = (m - index >= n);
                if (reverse) {
                    result = (n + index <= m);
                }
                return result;
            }

            @Override
            public Integer next() {
                if (reverse) {
                    return n + index++;
                } else {
                    return m - index++;
                }
            }
        };
    }

	public static void main(String[] args) {
		Part7 range = new Part7(3, 10, false);
		for (Integer integer : range) {
			System.out.print(integer + " ");
		}
        System.out.println();
        Part7 range2 = new Part7(3, 10, true);
        for (Integer integer : range2) {
            System.out.print(integer + " ");
        }
	}

}
