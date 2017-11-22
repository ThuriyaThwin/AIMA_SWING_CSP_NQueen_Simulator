package lab2.WaterJugsProblem;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.ActionsFunction;

public class ActionFactory implements ActionsFunction {

	public static Action	_4LITRETO3LITRE	= new DynamicAction("FILL 4 LITRE JUG TO 3 LITRE JUG");
	public static Action	_3LITRETO4LITRE	= new DynamicAction("FILL 3 LITRE JUG TO 4 LITRE JUG");
	public static Action	_FILLIN3LITRE	= new DynamicAction("FILL IN 3 LITRE JUG");
	public static Action	_FILLIN4LITRE	= new DynamicAction("FILL IN 4 LITRE JUG");
	public static Action	_FILLOUT3LITRE	= new DynamicAction("FILL OUT 3 LITRE JUG");
	public static Action	_FILLOUT4LITRE	= new DynamicAction("FILL OUT 4 LITRE JUG");

	public Set<Action> actions(Object s) {
		WaterJugState state = (WaterJugState) s;
		Set<Action> actionList = new LinkedHashSet<Action>();

		if (state.canJug4toJug3())
			actionList.add(_4LITRETO3LITRE);
		if (state.canJug3toJug4())
			actionList.add(_3LITRETO4LITRE);
		
		if (state.canFillInJug3Liter())
			actionList.add(_FILLIN3LITRE);
		if (state.canFillInJug4Liter())
			actionList.add(_FILLIN4LITRE);
		
		if (state.canFillOutJug3Liter())
			actionList.add(_FILLOUT3LITRE);
		if (state.canFillOutJug4Liter())
			actionList.add(_FILLOUT4LITRE);
		return actionList;
	}
}