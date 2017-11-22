package AISim;

import java.util.HashSet;
import java.util.Set;

public class Nim {

	public static void main(String[] a) {
		int number = 7;

		Set<String> c = cutOff("7-5", 0);

		for (String string : c) {
			System.out.println(string);
		}

	}

	public static Set<String> cutOff(String pattern, int level) {
		Set<String> c = new HashSet<String>();

		String[] nstring = pattern.split("-");
		for (String p : nstring) {
			int n = Integer.parseInt(p);

			for (int i = 1; i <= (n / 2); i++) {
				int n1 = i;
				int n2 = n - n1;
				if (n1 > n2) {
					int t = n2;
					n2 = n1;
					n1 = t;
				}
				if (n1 == n2)
					continue;
				if ((n1 + n2) == n) {
					c.add(n1 + "-" + n2);
					c.add(pattern.replace(p, n1 + "-" + n2));
				}
			}
		}

		return c;
	}
}
