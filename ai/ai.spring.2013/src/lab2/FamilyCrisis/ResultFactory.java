package lab2.FamilyCrisis;

import aima.core.agent.Action;
import aima.core.search.framework.ResultFunction;

public class ResultFactory implements ResultFunction {
	@Override
	public Object result(Object s, Action a) {
		GameState state = (GameState) s;
		GameState resultState = null;

		if (a.equals(ActionFactory.A_1)) {
			resultState =state.moveA_1_Alone();
		}
		if (a.equals(ActionFactory.A_3)) {
			resultState =state.moveA_3_Alone();
		}
		if (a.equals(ActionFactory.A_6)) {
			resultState =state.moveA_6_Alone();
		}
		if (a.equals(ActionFactory.A_8)) {
			resultState =state.moveA_8_Alone();
		}
		if (a.equals(ActionFactory.A_12)) {
			resultState =state.moveA_12_Alone();
		}
		if (a.equals(ActionFactory.A_1_3)) {
			resultState =state.moveA_1_With_3();
		}
		if (a.equals(ActionFactory.A_1_6)) {
			resultState =state.moveA_1_With_6();
		}
		if (a.equals(ActionFactory.A_1_8)) {
			resultState =state.moveA_1_With_8();
		}
		if (a.equals(ActionFactory.A_1_12)) {
			resultState =state.moveA_1_With_12();
		}
		if (a.equals(ActionFactory.A_3_6)) {
			resultState =state.moveA_3_With_6();
		}
		if (a.equals(ActionFactory.A_3_8)) {
			resultState =state.moveA_3_With_8();
		}
		if (a.equals(ActionFactory.A_3_12)) {
			resultState =state.moveA_3_With_12();
		}
		if (a.equals(ActionFactory.A_6_8)) {
			resultState =state.moveA_6_With_8();
		}
		if (a.equals(ActionFactory.A_6_12)) {
			resultState =state.moveA_6_With_12();
		}
		if (a.equals(ActionFactory.A_8_12)) {
			resultState =state.moveA_8_With_12();
		}

		return resultState;
	}
}
