package ua.nure.draban.Practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Part2 {
	private static final int MAX_PEOPLE = 10_000;

	public static void main(String[] args) {
		method(new ArrayList<>(), 3);
		System.out.println();
		method(new LinkedList<>(), 3);
	}

	public static void method(List<Integer> list, int k) {
		List<Integer> result = list;
		int index = -1;
		for (int i = 0; i < MAX_PEOPLE; i++) {
			result.add(i);
		}
		long start = System.currentTimeMillis();

		while (result.size() > 1) {
			Iterator<Integer> it = result.iterator();
			while (it.hasNext()) {
                Integer next = it.next();
                index++;
                if (index == k) {
                    it.remove();
                    index = -1;
                }
            }
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}
