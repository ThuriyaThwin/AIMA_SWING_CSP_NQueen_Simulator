package lab2.NQueensPuzzle;

import aima.core.search.framework.GoalTest;

public class GoalEvaluator implements GoalTest {
	@Override
	public boolean isGoalState(Object state) {
		NQueenState s = (NQueenState) state;
		for (int i = 0; i < s.board.length; i++) {
			System.out.print(" - " + s.board[i]);
		}
		System.out.println();

		for (int i = 0; i < s.board.length; i++) {
			for (int j = 0; j < s.board.length; j++) {
				if (!checkValidPair(i, s.board[i], j, s.board[j]))
					return false;
			}
		}
		return true;
	}

	public static boolean checkValidPair(int queen1, int at1, int queen2, int at2) {
		if (at1 == at2)
			return false;
		if (queen1 == queen2)
			return false;

		if (Math.abs(queen1 - queen2) == Math.abs(at1 - at1)) { return false; }

		return true;
	}

}
