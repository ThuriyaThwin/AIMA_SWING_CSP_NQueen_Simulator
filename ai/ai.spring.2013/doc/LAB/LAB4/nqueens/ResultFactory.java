package search.nqueens;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.nqueens.QueenAction;
import aima.core.search.framework.ResultFunction;

public class ResultFactory implements ResultFunction{
	@Override
	public Object result(Object s, Action a) {
		NQueensState state = (NQueensState)s;
		NQueensState newState = new NQueensState(state.getSize());
		newState.setBoard(state.getQueenPositions());
		if(a instanceof QueenAction){
			QueenAction qa = (QueenAction) a;
			if (qa.getName() == QueenAction.MOVE_QUEEN)
				newState.moveQueenTo(qa.getLocation());
			
			s = newState;
		}
			// if action is not understood or is a NoOp
			// the result will be the current state.
		return s;
	}
}
