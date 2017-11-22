package lab3.EightPuzzle;

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
		PuzzleState state = (PuzzleState)s;
		Set<Action> actionList = new LinkedHashSet<Action>();
		
		if(state.canMoveUp()) actionList.add(MOVE_UP);
		if(state.canMoveDown()) actionList.add(MOVE_DOWN);
		if(state.canMoveLeft()) actionList.add(MOVE_LEFT);
		if(state.canMoveRight()) actionList.add(MOVE_RIGHT);
		
		return actionList;
	}

}
