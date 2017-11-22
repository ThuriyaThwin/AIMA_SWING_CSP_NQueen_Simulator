package lab2.RiverCrossingProblem;

import aima.core.search.framework.GoalTest;

public class GoalEvaluator implements GoalTest {
	public boolean isGoalState(Object state) {
		FarmerState s = (FarmerState) state;
		int goalState[] = { 1, 1, 1, 1 };
		// Verify whether s.m_state == goalState:
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			if (s.m_state[i] != goalState[i]) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
