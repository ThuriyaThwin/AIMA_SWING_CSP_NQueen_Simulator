package lab2.RiverCrossingProblem;

import aima.core.agent.Action;
import aima.core.search.framework.ResultFunction;

public class ResultFactory implements ResultFunction{
	@Override
	public Object result(Object s, Action a) {
		FarmerState state = (FarmerState)s;
		FarmerState resultState = null;
		
		if(a.equals(ActionFactory.FARMER_MOVE_ALONE)){
			resultState = state.moveAlone();
		}
		
		if(a.equals(ActionFactory.FARMER_MOVE_TIGER)){
			resultState = state.moveWithTiger();
		}
		if(a.equals(ActionFactory.FARMER_MOVE_BUFFALO)){
			resultState = state.moveWithBuffalo();
		}
		if(a.equals(ActionFactory.FARMER_MOVE_GRASS)){
			resultState = state.moveWithGrass();
		}		
		return resultState;
	}
}
