package lab2.RiverCrossingProblem;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.ActionsFunction;

public class ActionFactory implements ActionsFunction{

	public static Action FARMER_MOVE_ALONE = new DynamicAction("FARMER_MOVE_ALONE");
	public static Action FARMER_MOVE_TIGER = new DynamicAction("FARMER_MOVE_TIGER");
	public static Action FARMER_MOVE_BUFFALO = new DynamicAction("FARMER_MOVE_BUFFALO");
	public static Action FARMER_MOVE_GRASS = new DynamicAction("FARMER_MOVE_GRASS");
	
	
	public Set<Action> actions(Object s) {
		FarmerState state = (FarmerState)s;
		Set<Action> actionList = new LinkedHashSet<Action>();
		
		if(state.canFamerMoveAlone()) actionList.add(FARMER_MOVE_ALONE);
		if(state.canFamerMoveWithTiger()) actionList.add(FARMER_MOVE_TIGER);
		if(state.canFamerMoveWithBuffalo()) actionList.add(FARMER_MOVE_BUFFALO);
		if(state.canFamerMoveWithGrass()) actionList.add(FARMER_MOVE_GRASS);
		
		return actionList;
	}

}
