package lab5.Csp.NQueen;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.BacktrackingStrategy;
import aima.core.search.csp.CSP;

public class NQueenCSPSolver {
	public static void main(String[] args) {
		// Create a CSP Problem
		NQueenVariableCollection var = new NQueenVariableCollection(20);
		CSP csp = new NQueenCSP(var, null);

		// Create a solver using Standard Backtracking Solver
		BacktrackingStrategy solver = new BacktrackingStrategy();

		// Solve the Problem, and Get the result
		Assignment results = solver.solve(csp);

		// Print the output
		System.out.println(results.toString());
		System.out.println("aaaaaaaaaaa");
		var.print();
	}
}
