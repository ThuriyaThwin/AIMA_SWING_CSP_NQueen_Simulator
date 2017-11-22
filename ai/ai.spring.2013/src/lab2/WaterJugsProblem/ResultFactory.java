package lab2.WaterJugsProblem;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.ResultFunction;

public class ResultFactory implements ResultFunction {
	@Override
	public Object result(Object s, Action a) {
		WaterJugState state = (WaterJugState) s;
		WaterJugState resultState = null;

		if (a.equals(ActionFactory._4LITRETO3LITRE)) {
			resultState = state.jug4to3();
		}
		if (a.equals(ActionFactory._3LITRETO4LITRE)) {
			resultState = state.jug3to4();
		}
		
		if (a.equals(ActionFactory._FILLIN3LITRE)) {
			resultState = state.fillin3();
		}
		if (a.equals(ActionFactory._FILLIN4LITRE)) {
			resultState = state.fillin4();
		}
		
		if (a.equals(ActionFactory._FILLOUT3LITRE)) {
			resultState = state.fillout3();
		}
		if (a.equals(ActionFactory._FILLOUT4LITRE)) {
			resultState = state.fillout4();
		}

		return resultState;
	}
}
