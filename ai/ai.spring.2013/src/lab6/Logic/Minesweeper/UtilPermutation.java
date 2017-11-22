package lab6.Logic.Minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class PermutationIterator<T> implements Iterator<List<T>> {

	private int				current	= 0;
	private final long		last;
	private final List<T>	lilio;

	public PermutationIterator(final List<T> llo) {
		lilio = llo;
		long product = 1;
		for (long p = 1; p <= llo.size(); ++p)
			product *= p;
		last = product;
	}

	@Override
	public boolean hasNext() {
		return current != last;
	}

	@Override
	public List<T> next() {
		++current;
		return get(current - 1, lilio);
	}

	@Override
	public void remove() {
		++current;
	}

	private List<T> get(final int code, final List<T> li) {
		int len = li.size();
		int pos = code % len;
		if (len > 1) {
			List<T> rest = get(code / len, li.subList(1, li.size()));
			List<T> a = rest.subList(0, pos);
			List<T> res = new ArrayList<T>();
			res.addAll(a);
			res.add(li.get(0));
			res.addAll(rest.subList(pos, rest.size()));
			return res;
		}
		return li;
	}
}

class XPermutation {
	public static List<String> getPermutation(String v, int k) {
		List<String> g = new ArrayList<String>();
		List<String> la = new ArrayList<String>();

		for (int i = 0; i < v.length(); i++) {
			la.add(String.valueOf(v.charAt(i)));
		}

		PermutationIterable<String> pi = new PermutationIterable<String>(la);
		Set<String> setList = new HashSet<String>();
		for (List<String> lc : pi)
			setList.add(show(lc, k));

		for (String string : setList) {
			g.add(string);
		}

		Collections.sort(g);

		return g;
	}

	public static String show(List<String> lo, int k) {

		List<String> newList = new ArrayList<String>();
		String r = "";
		for (int i = 0; i < lo.size(); i++) {
			String a = lo.get(i);
			if (i >= (k)) {
				a = "-" + lo.get(i);
			}
			newList.add(a);
		}
		Collections.sort(newList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (o1.startsWith("-")) {
					o1 = o1.substring(1);
					o2 = o2.substring(1);

				}
				return o1.compareTo(o2);
			}
		});
		for (Object o : newList)
			r += (o + " ^ ");

		if (r.endsWith(" ^ ")) {
			r = r.substring(0, r.length() - 3);
		}
		return r;
	}
}

class PermutationIterable<T> implements Iterable<List<T>> {

	private final List<T>	lilio;

	public PermutationIterable(List<T> llo) {
		lilio = llo;
	}

	@Override
	public Iterator<List<T>> iterator() {
		return new PermutationIterator<T>(lilio);
	}
}
