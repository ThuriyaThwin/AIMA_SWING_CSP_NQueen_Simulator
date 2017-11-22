package lab5.Csp.Sudoku;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CSP;
import aima.core.search.csp.ImprovedBacktrackingStrategy;
import aima.core.search.csp.ImprovedBacktrackingStrategy.Inference;
import aima.core.search.csp.ImprovedBacktrackingStrategy.Selection;

public class SodokuCSPSolver {
	public static void main(String[] args) {
		// Create a CSP Problem
		SodokuVariableCollection var = new SodokuVariableCollection("res/lab5/sodoku/sodoku.txt");
		CSP csp = new SodokuBoard(var);

		System.out.println("\n\n------->>  input  <<-----------");
		System.out.println(var.getString());

		//BacktrackingStrategy solver = new BacktrackingStrategy();

		ImprovedBacktrackingStrategy solver2 = new ImprovedBacktrackingStrategy();

		// Configure to use:
		// 1. Minimum Remaining Value
		// 2. Forward Checking
		solver2.setVariableSelection(Selection.MRV);
		solver2.setInference(Inference.FORWARD_CHECKING);

		System.out.println("Apply Backtracking Strategy: ImprovedBacktrackingStrategy");

		// Solve the Problem, and Get the result
		long start = System.currentTimeMillis();
		Assignment results = solver2.solve(csp);
		long finish = System.currentTimeMillis();

		long diff = finish - start;

		System.out.println("execute time " + (diff / 1000) + " second");

		// Print the output
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				SodokuVariable v = var.get(i, j);
				v.value = (Integer) results.getAssignment(v);
			}
		}

		System.out.println("\n------->>  result  <<-----------");
		System.out.println(var.getString());

		//System.out.println(results.toString());
	}
}
