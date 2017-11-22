package lab3.EightPuzzle;

import aima.core.search.framework.GoalTest;

public class GoalEvaluator implements GoalTest {
	public boolean isGoalState(Object state) {
		PuzzleState s = (PuzzleState) state;
		boolean flag = true;
			for (int j = 0; j < 9; j++) {
//				if (s.m_state[j] != PuzzleState.m_goal_state[j]) {
//					flag = false;
//					break;
//				}
		}
		return flag;
	}
}
