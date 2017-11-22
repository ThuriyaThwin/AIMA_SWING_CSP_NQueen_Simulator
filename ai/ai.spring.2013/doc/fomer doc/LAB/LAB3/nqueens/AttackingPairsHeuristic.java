package search.nqueens;

import aima.core.search.framework.HeuristicFunction;

/**
 * Estimates the distance to goal by the number of attacking pairs of queens on
 * the board.
 * 
 * @author R. Lunde
 */
public class AttackingPairsHeuristic implements HeuristicFunction {

	public double h(Object state) {
		NQueensState board = (NQueensState) state;
		return board.getNumberOfAttackingPairs();
	}
}