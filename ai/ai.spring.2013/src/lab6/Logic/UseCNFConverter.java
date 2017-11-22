package lab6.Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import aima.core.logic.propositional.parsing.PEParser;
import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.visitors.CNFClauseGatherer;
import aima.core.logic.propositional.visitors.CNFTransformer;

public class UseCNFConverter {
	public static void main(final String[] args) {
		Set<String> a = UseCNFConverter.getCNF("(d(x)=>f(x))");
		System.out.println("My APP");
		for (String string : a)
			System.out.println("CNF = " + string);
	}

	public static Set<String> getCNF(final String s) {
		Set<String> r = new HashSet<String>();
		// Create Parser: to verify syntax
		PEParser parser = new PEParser();
		// Create Transformer: to convert to CNF sentences
		CNFTransformer transformer = new CNFTransformer();
		// Create Gatherer: to break into CNF clause
		CNFClauseGatherer gatherer = new CNFClauseGatherer();

		// PARSE
		Sentence pl_sentence = (Sentence) parser.parse(s);
		// Sentence pl_sentence = (Sentence)
		// parser.parse("( B => (( C OR E )  ))");
		// Sentence pl_sentence = (Sentence) parser.parse("((E AND F) => H)");
		// TRANSFORM
		Sentence cnf_sentence = transformer.transform(pl_sentence);

		// GATHER
		Set<Sentence> clauses = gatherer.getClausesFrom(cnf_sentence);

		// Print the whole sentence
		// System.out.println("CNF Sentence:");
		// System.out.println(cnf_sentence.toString());

		// Print each clause
		// System.out.println("CNF Clauses:");
		Iterator<Sentence> iter = clauses.iterator();
		while (iter.hasNext()) {
			Sentence sentence = iter.next();
			// System.out.println(sentence.toString());

			r.add(UseCNFConverter.filterSentence(sentence.toString()));
		}
		return r;
	}

	private static String filterSentence(final String string) {

		String s = UseCNFConverter.toNatural(string);
		s = s.replaceAll(" ", "");

		List<String> vColl = new ArrayList<String>();

		for (int mI = 0; mI < s.length(); mI++) {
			String a = String.valueOf(s.charAt(mI));
			if (a.equals("^"))
				continue;
			if (a.equals("V") || a.equals("v"))
				continue;

			if (a.equals("-")) {
				a = a + String.valueOf(s.charAt(mI + 1));
				mI++;
			}
			if (!vColl.contains(a))
				vColl.add(a);

		}

		Collections.sort(vColl);
		String r = "";
		for (String mString : vColl)
			r += mString + " v ";

		r = r.substring(0, r.length() - 3);

		return UseCNFConverter.toEngine(UseCNFConverter.parseAnd(r));
	}

	public static String toEngine(String line) {
		line = line.toUpperCase();

		line = line.replace("^", " AND ");
		line = line.replace("V", " OR ");
		line = line.replace("v", " OR ");
		line = line.replace("-", " NOT ");
		line = "( " + line + " )";
		line = line.trim();
		line = line.replace("  ", " ");
		line = line.replace("( (", "((");
		line = line.replace(") )", "))");

		line = line.replace("( ", "(");
		line = line.replace(" )", ")");
		return line;
	}

	public static String toNatural(String line) {
		line = line.toUpperCase();
		line = line.replace("AND", " ^ ");
		line = line.replace("OR", " v ");
		line = line.replace("NOT", " -");
		line = line.replace("(", " ");
		line = line.replace(")", " ");

		while (!line.replace("  ", " ").equals(line))
			line = line.replace("  ", " ");

		line = line.trim();

		return line;
	}

	public static String parseAnd(String andOp) {

		andOp = andOp.replaceAll(" ", "");

		Stack<String> s = new Stack<String>();

		String lastP = "";
		for (int i = 0; i < andOp.length(); i++) {

			if (String.valueOf(andOp.charAt(i)).equals("^"))
				if (s.size() == 3) {
					String e1 = s.pop();
					String c = s.pop();
					String e2 = s.pop();
					// System.out.println("size = " + s.size());
					s.push("(" + e2 + c + e1 + ")");
					// /System.out.println("push nest " + "(" + e2 + c + e1 + ")");
				}

			if (String.valueOf(andOp.charAt(i)).equals("-")) {
				lastP = String.valueOf(andOp.charAt(i)) + String.valueOf(andOp.charAt(i + 1));
				i++;
				lastP = "(" + lastP + ")";
			}
			else
				lastP = String.valueOf(andOp.charAt(i));

			s.push(lastP);
			// System.out.println("push " + lastP);
		}
		String r = andOp;
		if (s.size() == 3) {
			String e1 = s.pop();
			String c = s.pop();
			String e2 = s.pop();
			r = e2 + c + e1;
		}
		// System.out.println("result : " + r);
		return r;
	}
}
