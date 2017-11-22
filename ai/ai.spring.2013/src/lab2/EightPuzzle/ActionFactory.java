package lab2.EightPuzzle;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.ActionsFunction;

public class ActionFactory implements ActionsFunction{

	public static Action MOVE_UP = new DynamicAction("GO UP");
	public static Action MOVE_DOWN = new DynamicAction("GO DOWN");
	public static Action MOVE_LEFT = new DynamicAction("GO LEFT");
	public static Action MOVE_RIGHT = new DynamicAction("GO RIGHT");
	
	
	public Set<Action> actions(Object s) {
		EightPuzzleState state = (EightPuzzleState)s;
		Set<Action> actionList = new LinkedHashSet<Action>();
		
		if(state.canGoUp()) actionList.add(MOVE_UP);
		if(state.canGoDown()) actionList.add(MOVE_DOWN);
		if(state.canGoLeft()) actionList.add(MOVE_LEFT);
		if(state.canGoRight()) actionList.add(MOVE_RIGHT);
		
		return actionList;
	}

}
