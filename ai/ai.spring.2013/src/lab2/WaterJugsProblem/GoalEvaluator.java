package lab2.WaterJugsProblem;

import aima.core.search.framework.GoalTest;

public class GoalEvaluator implements GoalTest {
	public boolean isGoalState(Object state) {
		WaterJugState s = (WaterJugState) state;
//System.out.println("Current state = " + s);
		if (s.m_state[WaterJugState.JUG3LITER] == 2) {
			System.out.println("Rearch result in Jug 3");
			return true;
		}
		if (s.m_state[WaterJugState.JUG4LITER] == 2) {
			System.out.println("Rearch result in Jug 4");
			return true;
		}

		return false;
	}
}
