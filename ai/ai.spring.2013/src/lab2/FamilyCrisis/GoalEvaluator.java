package lab2.FamilyCrisis;

import aima.core.search.framework.GoalTest;

public class GoalEvaluator implements GoalTest {

	int	goalState[];

	public GoalEvaluator(int goalState[]) {
		this.goalState = goalState;
	}

	public boolean isGoalState(Object state) {
		GameState s = (GameState) state;

		boolean flag = true;
		for (int i = 2; i <= 6; i++) {
			
			if (s.m_state[i] != goalState[i]) {
				flag = false;

				break;
			}
		}

		return flag;
	}
}
