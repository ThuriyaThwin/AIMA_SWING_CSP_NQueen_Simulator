package lab5.Csp.MapColoring.Vietnam;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.BacktrackingStrategy;
import aima.core.search.csp.CSP;

public class CSPSolver {
	public static void main(String[] args) {
		// Create a CSP Problem
		CSP csp = new VietnamMap();

		// Create a solver using Standard Backtracking Solver
		BacktrackingStrategy solver = new BacktrackingStrategy();

		// Solve the Problem, and Get the result
		Assignment results = solver.solve(csp);

		// Print the output
		System.out.println(results.toString());
	}
}
