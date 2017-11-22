package lab2.EightPuzzle;

import aima.core.search.framework.GoalTest;

public class GoalEvaluator implements GoalTest {
	public boolean isGoalState(Object state) {
		EightPuzzleState s = (EightPuzzleState) state;
		boolean flag = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (s.m_state[i][j] != EightPuzzleState.m_state_goal[i][j]) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
}
