package search.newpuzzle;

import aima.core.agent.Action;
import aima.core.search.framework.ResultFunction;

public class ResultFactory implements ResultFunction{
	@Override
	public Object result(Object s, Action a) {
		PuzzleState state = (PuzzleState)s;
		PuzzleState resultState = null;
		
		if(a.equals(ActionFactory.MOVE_LEFT)){
			resultState = state.moveLeft();
		}
		
		if(a.equals(ActionFactory.MOVE_RIGHT)){
			resultState = state.moveRight();
		}
		if(a.equals(ActionFactory.MOVE_UP)){
			resultState = state.moveUp();
		}
		if(a.equals(ActionFactory.MOVE_DOWN)){
			resultState = state.moveDown();
		}		
		return resultState;
	}
}
