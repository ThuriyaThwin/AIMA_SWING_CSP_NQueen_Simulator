package lab2.MissionariesAndCannibals;

import aima.core.agent.Action;
import aima.core.search.framework.ResultFunction;

public class ResultFactory implements ResultFunction{
	@Override
	public Object result(Object s, Action a) {
		GameState state = (GameState)s;
		GameState resultState = null;
		
		if(a.equals(ActionFactory.MISSIONARY_WITH_CANNIBAL)){
			resultState = state.moveMissionaryAndCannibal();
		}
		
		if(a.equals(ActionFactory.MISSIONARY_WITH_FELLOW)){
			resultState = state.moveMissionaryWithFellow();
		}
		
		if(a.equals(ActionFactory.CANNIBALS_WITH_FELLOW)){
			resultState = state.moveCannibalsWithFellow();
		}
		
		if(a.equals(ActionFactory.MISSIONARY_MOVE_ALONE)){
			resultState = state.moveMissionaryAlone();
		}
		if(a.equals(ActionFactory.CANNIBAL_MOVE_ALONE)){
			resultState = state.moveCannibalAlone();
		}
		
		
		return resultState;
	}
}
