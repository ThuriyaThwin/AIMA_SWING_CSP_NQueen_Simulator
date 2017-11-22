package lab2.EightPuzzle;

import aima.core.agent.Action;
import aima.core.search.framework.ResultFunction;

public class ResultFactory implements ResultFunction{
	@Override
	public Object result(Object s, Action a) {
		EightPuzzleState state = (EightPuzzleState)s;
		EightPuzzleState resultState = null;
		
		if(a.equals(ActionFactory.MOVE_UP)) {
			System.out.println("try move up");
			resultState = state.moveUp();
		}
		
		if(a.equals(ActionFactory.MOVE_DOWN)){
			System.out.println("try move down");
			resultState = state.moveDown();
		}
		
		if(a.equals(ActionFactory.MOVE_LEFT)){
			System.out.println("try move right");
			resultState = state.moveLeft();
		}
		
		if(a.equals(ActionFactory.MOVE_RIGHT)){
			System.out.println("try move right");
			resultState = state.moveRight();
		}
		
		return resultState;
	}
}
