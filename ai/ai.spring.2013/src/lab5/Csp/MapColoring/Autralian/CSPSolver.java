package lab5.Csp.MapColoring.Autralian;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.BacktrackingStrategy;
import aima.core.search.csp.CSP;
import aima.core.search.csp.ImprovedBacktrackingStrategy;
import aima.core.search.csp.ImprovedBacktrackingStrategy.Inference;
import aima.core.search.csp.ImprovedBacktrackingStrategy.Selection;

public class CSPSolver {
	public static void main(String[] args) {
		// Create a CSP Problem
		CSP csp = new AustralianMap();

		// Create a solver using Standard Backtracking Solver
		BacktrackingStrategy solver = new BacktrackingStrategy();

		ImprovedBacktrackingStrategy solver2 = new ImprovedBacktrackingStrategy();

		// Configure to use:
		// 1. Minimum Remaining Value
		// 2. Forward Checking
		solver2.setVariableSelection(Selection.MRV);
		solver2.setInference(Inference.FORWARD_CHECKING);

		// Solve the Problem, and Get the result
		Assignment results = solver2.solve(csp);

		// Print the output
		System.out.println(results.toString());
	}
}
