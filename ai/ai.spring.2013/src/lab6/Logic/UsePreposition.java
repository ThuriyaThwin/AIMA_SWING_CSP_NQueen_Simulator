package lab6.Logic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import aima.core.logic.propositional.algorithms.KnowledgeBase;
import aima.core.logic.propositional.algorithms.PLResolution;

public class UsePreposition {
	public static void main(final String[] args) {
		UsePreposition.resolution1();
	}

	public static void resolution1() {
		// Create a knowledge base
		KnowledgeBase kb = new KnowledgeBase();
		// Create a Resolution-based prover
		PLResolution prover = new PLResolution();

		List<String> setKB = UsePreposition.getKB(UsePreposition.readFile("res/lab6/resolution1.txt"));

		System.out.println("---------CNF --------------------");
		int count = 0;
		for (String k : setKB) {
			Set<String> cnf = UseCNFConverter.getCNF(k);
			for (String c : cnf)
				System.out.println(++count + ". " + UseCNFConverter.toNatural(c));
			kb.tell(k);
		}
		// Declare the sentence, called 'conclusion',
		// that you want to infer from the knowledge base
		String conclusion = "(H)";

		// Prove the conclusion
		boolean result = prover.plResolution(kb, conclusion);

		// Print the result:
		// Result : TRUE = found a CONTRADITION
		// Result : FALSE = COULD NOT find a CONTRADITION
		if (result)
			System.out.println("We CAN infer  " + conclusion + " from the knowledge base");
		else
			System.out.println("We CANNOT infer " + conclusion + " from the knowledge base");
	}

	public static List<String> getKB(final String data) {

		String list_data[] = data.split("\n");
		List<String> r = new ArrayList<String>();

		try {
			for (String strLine : list_data)
				try {
					String line = strLine.trim().replaceAll("  ", " ");
					if (line.startsWith("//"))
						continue;
					if (line.length() == 0)
						continue;

					line = UseCNFConverter.toEngine(line);
					System.out.println("TELL:   >> " + line);
					// UseCNFConverter.getCNF(line);
					r.add(line);
				} catch (Exception e) {
					e.printStackTrace();
				}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return r;
	}

	public static String readFile(final String file_data) {

		String r = "";
		try {
			FileInputStream fstream = new FileInputStream(file_data);

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;

			while ((strLine = br.readLine()) != null)
				r += "\n" + strLine;

			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return r;
	}

}
