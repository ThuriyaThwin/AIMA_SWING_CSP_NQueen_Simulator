package lab2.NQueensPuzzle;

import lab2.NQueensPuzzle.ActionFactory.QueenAction;
import aima.core.agent.Action;
import aima.core.search.framework.ResultFunction;

public class ResultFactory implements ResultFunction {
	@Override
	public Object result(Object s, Action a) {
		NQueenState state = (NQueenState) s;

		QueenAction qa = (QueenAction) a;

		NQueenState resultState = state.move(qa.queen, qa.move);

		return resultState;
	}
}
