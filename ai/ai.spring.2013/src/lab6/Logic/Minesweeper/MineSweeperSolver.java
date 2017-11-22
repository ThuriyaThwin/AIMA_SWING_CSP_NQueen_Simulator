package lab6.Logic.Minesweeper;

import java.util.List;

import aima.core.logic.propositional.algorithms.KnowledgeBase;
import aima.core.logic.propositional.algorithms.PLResolution;

public class MineSweeperSolver {
	public static void main(final String[] args) {
		//MineBoard mineBoard = new MineBoard(4, 4, 2, 2);
		MineBoard mineBoard = new MineBoard("res/lab6/MineMatrix3.txt", true);

		MineSweeperSolver mineSolver = new MineSweeperSolver(mineBoard);
		mineBoard.getHtmlLogic();
		mineSolver.solver();
	}

	public MineBoard	mine;

	public MineSweeperSolver(final MineBoard m) {
		mine = m;
	}

	public String solver() {
		StringBuilder sb = new StringBuilder("<html><H2>AIMA Result</H2><table border=\"0\">");

		sb.append("<tr>");
		sb.append("<td bgcolor=#81DAF5><H3>   Cell </H3></td>");
		sb.append("<td bgcolor=#81DAF5><H3> Has mine </H3></td>");
		sb.append("</tr>");

		// Create a knowledge base
		KnowledgeBase kb = new KnowledgeBase();
		// Create a Resolution-based prover
		PLResolution prover = new PLResolution();

		List<String> setKB = mine.getCNF();
		for (String k : setKB) {
			System.out.println("CNF = " + k);
			kb.tell(k);
		}

		for (String mString : mine.hidden_mine) {
			String conclusion = "(" + mString + ")";

			// Prove the conclusion
			boolean result = prover.plResolution(kb, conclusion);

			// Print the result:
			// Result : TRUE = found a CONTRADITION
			// Result : FALSE = COULD NOT find a CONTRADITION

			sb.append("<tr>");
			sb.append("<td bgcolor=#CEECF5 > <H4>" + mString + "</H4></td>");
			sb.append("<td bgcolor=#CEECF5 ><H4> " + (result == true ? "Yes" : "No") + "</H4></td>");
			sb.append("</tr>");

			if (result)
				System.out.println("We CAN infer " + conclusion + " from the knowledge base");
			else
				System.out.println("We CANNOT infer " + conclusion + " from the knowledge base");

		}

		sb.append("</table></html>");
		return sb.toString();
	}
}
