package lab2.MissionariesAndCannibals;

import aima.core.search.framework.GoalTest;

public class GoalEvaluator implements GoalTest {
	
	int goalState[];
	public GoalEvaluator(int goalState[]) {
		this. goalState =  goalState;
	}
	
	public boolean isGoalState(Object state) {
		GameState s = (GameState) state;
		
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			if (s.m_state[i] != goalState[i]) {
				System.out.println("Check State: " + s.m_state[0] + "-" + s.m_state[1] + "-" + s.m_state[2] + "-" + s.m_state[3]);
				flag = false;
				break;
			}
		}
		
		return flag;
	}
}
